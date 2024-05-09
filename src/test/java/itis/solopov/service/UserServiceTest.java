//package itis.solopov.service;
//
//import itis.solopov.config.MailConfig;
//import itis.solopov.dto.CreateUserRequestDto;
//import itis.solopov.dto.UserDto;
//import itis.solopov.model.User;
//import itis.solopov.repository.UserRepository;
//import itis.solopov.service.UserService;
//import org.assertj.core.internal.bytebuddy.utility.RandomString;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//
//import java.nio.charset.StandardCharsets;
//import java.util.List;
//import java.util.Optional;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.*;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@WebMvcTest
//@ExtendWith(SpringExtension.class)
//public class UserServiceTest {
//
//    private static final String DEFAULT_USER_NAME = "User";
//    private static final String DEFAULT_USER_EMAIL = "user@gmail.com";
//    private static final String DEFAULT_USER_PASSWORD = "password";
//    public static final String BASE_URL = "https://localohost:8080";
//
//    private static UserService userService;
//
//    @MockBean
//    private UserRepository userRepository;
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private MailConfig mailConfig;
//
//    @Autowired
//    private JavaMailSender javaMailSender;
//
//    @Autowired
//    private BCryptPasswordEncoder encoder;
//
//    private static final String VERIFICATION_CODE = "000000";
//
//    @BeforeEach
//    public void initializeService() {
//        ((JavaMailSenderImpl) javaMailSender).setDefaultEncoding(StandardCharsets.UTF_8.name());
//        userService = new UserService(
//                userRepository,
//                mailConfig,
//                javaMailSender,
//                encoder
//        );
//    }
//    @Test
//    public void testFindAllByName() {
//        User user = new User();
//        user.setName(DEFAULT_USER_NAME);
//        given(userRepository.findAllByName(DEFAULT_USER_NAME)).willReturn(List.of(user));
//
//        userService.findAllByName(DEFAULT_USER_NAME);
//
//        verify(userRepository, times(1)).findAllByName(DEFAULT_USER_NAME);
//        verifyNoMoreInteractions(userRepository);
//    }
//
//
//
//    @Test
//    public void testVerifyUserSuccess() {
//        String code = RandomString.make(128);
//
//        User user = new User();
//
//        given(userRepository.findByVerificationCode(code)).willReturn(Optional.of(user));
//        given(userRepository.save(any(User.class))).willReturn(user);
//
//        Assertions.assertEquals(true, userService.verify(code));
//        verify(userRepository, times(1)).findByVerificationCode(code);
//        verify(userRepository, times(1)).save(any(User.class));
//        verifyNoMoreInteractions(userRepository);
//    }
//
//    @Test
//    public void testVerifyUserFailure() {
//        User user = new User();
//
//        String code = RandomString.make(128);
//
//        given(userRepository.findByVerificationCode(code)).willReturn(Optional.empty());
//        given(userRepository.save(any(User.class))).willReturn(user);
//
//        Assertions.assertFalse(userService.verify(code));
//        verify(userRepository, times(1)).findByVerificationCode(code);
//        verify(userRepository, times(0)).save(any(User.class));
//        verifyNoMoreInteractions(userRepository);
//    }
//
//    @Test
//    public void testCreateUser() {
//        CreateUserRequestDto createUserRequestDto = new CreateUserRequestDto();
//        createUserRequestDto.setEmail(DEFAULT_USER_NAME);
//        createUserRequestDto.setName(DEFAULT_USER_EMAIL);
//        createUserRequestDto.setPassword(DEFAULT_USER_PASSWORD);
//
//        UserDto user = userService.create(createUserRequestDto, BASE_URL);
//        UserDto userDto = new UserDto(DEFAULT_USER_NAME);
//
//        assertEquals(user, userDto);
//        verify(userRepository, times(1)).save(any(User.class));
//        verifyNoMoreInteractions(userRepository);
//    }
//
//}
