package itis.solopov.service;

import itis.solopov.config.MailConfig;
import itis.solopov.dto.CreateUserRequestDto;
import itis.solopov.dto.LogInUserRequestDto;
import itis.solopov.model.Role;
import itis.solopov.model.User;
import itis.solopov.repository.UserRepository;
import itis.solopov.service.exception.WrongPasswordException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public UserService(UserRepository userRepository, MailConfig mailConfig, JavaMailSender javaMailSender, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    public User logIn(LogInUserRequestDto dto) throws WrongPasswordException {
        User user = userRepository.findByEmail(dto.email).orElse(null);
        if (user != null ) {
            if (encoder.matches(dto.password, user.password)) {
                return user;
            } else {
                throw new WrongPasswordException("Wrong password");
            }
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }

    public User create(CreateUserRequestDto dto) throws RoleNotFoundException {
        User user = new User();
        user.setName(dto.name);
        user.setPassword(encoder.encode(dto.password));
        user.setEmail(dto.email);
        user.setGender(dto.gender);
        user.setAge(dto.age);

        Set<Role> roles = new HashSet<>();

        roles.add(Role.USER);
//
//        Role role = roleRepository.findByName("USER").orElse(null);
//
//        if (role != null ) {
//            roles.add(role);
//
//        } else {
//            throw new RoleNotFoundException("Role not found");
//        }

        user.setRoles(roles);

        return userRepository.save(user);
    }
}
