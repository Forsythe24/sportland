package itis.solopov.service.impl;

import itis.solopov.config.MailConfig;
import itis.solopov.dto.CreateUserRequestDto;
import itis.solopov.dto.UserDto;
import itis.solopov.model.User;
import itis.solopov.repository.UserRepository;
import itis.solopov.service.UserService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MailConfig mailConfig;
    private final JavaMailSender javaMailSender;
    private final BCryptPasswordEncoder encoder;

    public UserServiceImpl(UserRepository userRepository, MailConfig mailConfig, JavaMailSender javaMailSender, BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.mailConfig = mailConfig;
        this.javaMailSender = javaMailSender;
        this.encoder = encoder;
    }


    @Override
    public List<UserDto> findAllByName(String name) {
        return userRepository.findAllByName(name)
                .stream().map(u -> new UserDto(u.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto create(CreateUserRequestDto dto, String url) {
        User user = new User();
        user.setName(dto.name);
        user.setUsername(dto.email);
        user.setPassword(encoder.encode(dto.password));
//        sendVerificationCode(dto.email, user.name, RandomString.make(128), url);
        return UserDto.fromEntity(userRepository.save(user));
    }

    @Override
    public boolean verify(String code) {
        Optional<User> user = userRepository.findByVerificationCode(code);
        if (user.isPresent()) {
            User u = user.get();
            u.setVerificationCode(null);
            u.setEnabled(true);
            userRepository.save(u);
            return true;
        }
        return false;
    }

    @Override
    public void sendVerificationCode(String mail, String name, String code, String baseUrl) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        String content = mailConfig.getContent();

        try {
            helper.setFrom(mailConfig.getFrom(), mailConfig.getSender());
            helper.setTo(mail);
            helper.setSubject(mailConfig.getSubject());
            content = content.replace("{name}", name);
            content = content.replace("{url}", baseUrl + "/verification?code=" + code);
            helper.setText(content);

            javaMailSender.send(mimeMessage);

        } catch (MessagingException | UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
