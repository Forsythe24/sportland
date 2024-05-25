package itis.solopov.service;

import io.jsonwebtoken.Claims;
import itis.solopov.dto.CreateUserRequestDto;
import itis.solopov.dto.JwtRequest;
import itis.solopov.dto.JwtResponse;
import itis.solopov.dto.SendNewPasswordRequestDto;
import itis.solopov.filter.JwtProvider;
import itis.solopov.model.Role;
import itis.solopov.model.User;
import itis.solopov.repository.RoleRepository;
import itis.solopov.repository.UserRepository;
import itis.solopov.service.exception.UserAlreadyExistsException;
import itis.solopov.service.exception.UserNotFoundException;
import itis.solopov.service.exception.WrongPasswordException;
import itis.solopov.util.Constants;
import lombok.SneakyThrows;
import okhttp3.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import javax.security.auth.message.AuthException;
import javax.transaction.Transactional;
import java.security.SecureRandom;
import java.util.*;

@Service
public class AuthService {


    private final UserRepository userRepository;
    private final Map<String, String> refreshStorage = new HashMap<>();
    private final JwtProvider jwtProvider;
    private final BCryptPasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder encoder;

    private final OkHttpClient client;

    public AuthService(UserRepository userRepository, JwtProvider jwtProvider, BCryptPasswordEncoder passwordEncoder, RoleRepository roleRepository, BCryptPasswordEncoder encoder, OkHttpClient client) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.client = client;
    }


    public JwtResponse login(JwtRequest request) {
        User user = userRepository.findByEmail(request.getUsername())
                .orElseThrow(() -> new UserNotFoundException(String.format(Constants.USER_NOT_FOUND_EXCEPTION_EMAIL_TEMPLATE, request.getUsername())));

        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            String accessToken = jwtProvider.generateAccessToken(user);
            String refreshToken = jwtProvider.generateRefreshToken(user);
            refreshStorage.put(user.getEmail(), refreshToken);
            return new JwtResponse(accessToken, refreshToken);
        }
        throw new WrongPasswordException("Wrong password for " + request.getUsername());
    }

    public JwtResponse refresh(String refreshToken) throws AuthException {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            String username = claims.getSubject();
            String savedRefresh = refreshStorage.get(username);
            if (savedRefresh != null && savedRefresh.equals(refreshToken)) {
                User user = userRepository.findByEmail(username)
                        .orElseThrow(() -> new AuthException(username));
                String accessToken = jwtProvider.generateAccessToken(user);
                String newRefreshToken = jwtProvider.generateRefreshToken(user);
                refreshStorage.put(user.getEmail(), newRefreshToken);
                return new JwtResponse(accessToken, newRefreshToken);
            }
        }
        throw new AuthException("Invalid refresh token");
    }

    public JwtResponse token(String refreshToken) throws AuthException {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            Claims claims = jwtProvider.getRefreshClaims(refreshToken);
            String email = claims.getSubject();
            String savedRefresh = refreshStorage.get(email);
            if (savedRefresh != null && savedRefresh.equals(refreshToken)) {
                User user = userRepository.findByEmail(email)
                        .orElseThrow(() -> new AuthException(email));
                String accessToken = jwtProvider.generateAccessToken(user);
                return new JwtResponse(accessToken, null);
            }
        }
        throw new AuthException("Invalid refresh token");
    }

    @SneakyThrows
    public User create(CreateUserRequestDto dto) {
        User user = new User();

        String uniqueID = UUID.randomUUID().toString();

        user.setId(uniqueID);
        user.setName(dto.getName());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setEmail(dto.getEmail());
        user.setGender(dto.getGender());
        user.setAge(dto.getAge());

        Set<Role> roles = new HashSet<>();

        Role role = roleRepository.findByName("USER").orElse(null);

        if (role != null ) {
            roles.add(role);

        } else {
            throw new RoleNotFoundException("Role not found");
        }
        user.setRoles(roles);

        try {
            return userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            throw new UserAlreadyExistsException("User with such email already exists");
        }
    }

    @Transactional
    @SneakyThrows
    public ResponseBody sendNewPasswordOnEmail(SendNewPasswordRequestDto dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new UserNotFoundException(String.format(Constants.USER_NOT_FOUND_EXCEPTION_EMAIL_TEMPLATE, dto.getEmail())));

        MediaType mediaType = MediaType.parse("application/json");

        char[] possibleCharacters = (Constants.PASSWORD_CHAR_SET).toCharArray();
        String newPassword = RandomStringUtils.random( 12, 0, possibleCharacters.length-1, false, false, possibleCharacters, new SecureRandom());

        RequestBody body = RequestBody.create(mediaType, String.format(Constants.NEW_PASSWORD_LETTER_TEMPLATE, user.getEmail(), user.getName(), newPassword));

        Request request = new Request.Builder()
                .url("https://" + Constants.API_HOST + "/")
                .post(body)
                .addHeader("x-rapidapi-key", Constants.API_KEY)
                .addHeader("x-rapidapi-host", Constants.API_HOST)
                .addHeader("Content-Type", Constants.CONTENT_TYPE_APPLICATION_JSON)
                .build();

        userRepository.updatePassword(encoder.encode(newPassword), dto.getEmail());
        return client.newCall(request).execute().body();
    }
}