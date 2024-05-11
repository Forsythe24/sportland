package itis.solopov.service;

import io.jsonwebtoken.Claims;
import itis.solopov.dto.CreateUserRequestDto;
import itis.solopov.dto.JwtRequest;
import itis.solopov.dto.JwtResponse;
import itis.solopov.dto.VerifyCredentialsRequestDto;
import itis.solopov.filter.JwtProvider;
import itis.solopov.model.Role;
import itis.solopov.model.User;
import itis.solopov.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.message.AuthException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class AuthService {


    private final UserRepository userRepository;
    private final Map<String, String> refreshStorage = new HashMap<>();
    private final JwtProvider jwtProvider;
    private final BCryptPasswordEncoder passwordEncoder;

    private final BCryptPasswordEncoder encoder;

    public AuthService(UserRepository userRepository, JwtProvider jwtProvider, BCryptPasswordEncoder passwordEncoder, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
        this.encoder = encoder;
    }


    public JwtResponse login(JwtRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException(request.getEmail()));
        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            String accessToken = jwtProvider.generateAccessToken(user);
            String refreshToken = jwtProvider.generateRefreshToken(user);
            refreshStorage.put(user.getEmail(), refreshToken);
            return new JwtResponse(accessToken, refreshToken);
        }
        return null;
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

    public User create(CreateUserRequestDto dto) {
        User user = new User();

        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setEmail(dto.getEmail());
        user.setGender(dto.getGender());
        user.setAge(dto.getAge());

        Set<Role> roles = new HashSet<>();

        roles.add(Role.USER);

        user.setRoles(roles);
//        user.setSport();

        return userRepository.save(user);
    }

    public Boolean verifyCredentials(VerifyCredentialsRequestDto dto) {
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException(dto.getEmail()));
//        System.out.println(encoder.matches(dto.getPassword(), user.getPassword()));
        return encoder.matches(dto.getPassword(), user.getPassword());
    }
}