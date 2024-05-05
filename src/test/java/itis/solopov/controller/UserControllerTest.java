//package itis.solopov.controller;
//
//import itis.solopov.dto.CreateUserRequestDto;
//import itis.solopov.dto.UserDto;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.context.WebApplicationContext;
//
//
//import java.util.Arrays;
//import java.util.Collections;
//
//import static org.hamcrest.Matchers.hasSize;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.*;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest
//@ExtendWith(SpringExtension.class)
//public class UserControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private WebApplicationContext context;
//
//    @MockBean
//    private UserService userService;
//
//    private static final String VERIFICATION_CODE = "000000";
//    private static final String DEFAULT_USER_NAME = "User";
//    private static final String DEFAULT_USER_EMAIL = "user@gmail.com";
//    private static final String DEFAULT_USER_PASSWORD = "user@gmail.com";
//    public static final String BASE_URL = "https://localohost:8080";
//
//
//    @BeforeEach
//    public void setUp() {
//        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
//    }
//
//    @Test
//    public void testGetUser() throws Exception {
//        UserDto userDto = new UserDto(DEFAULT_USER_NAME);
//        given(userService.findAllByName(DEFAULT_USER_NAME)).willReturn(Arrays.asList(userDto));
//
//        mockMvc.perform(get("/users?name=" + DEFAULT_USER_NAME)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .with(user("user").roles("USER")))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$", hasSize(1)))
//                .andExpect(jsonPath("$[0].name").value(DEFAULT_USER_NAME));
//
//        verify(userService, times(1)).findAllByName(DEFAULT_USER_NAME);
//        verifyNoMoreInteractions(userService);
//    }
//
//    @Test
//    public void testGetSignUp() throws Exception {
//
//        mockMvc.perform(get("/signup").with(user("user").roles("ANONYMOUS")))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
//                .andExpect(view().name("sign_up"));
//
//    }
//
//    @Test
//    public void testCreateUser() throws Exception {
//        CreateUserRequestDto createUserRequestDto = new CreateUserRequestDto();
//        createUserRequestDto.setEmail(DEFAULT_USER_EMAIL);
//        createUserRequestDto.setName(DEFAULT_USER_NAME);
//        createUserRequestDto.setPassword(DEFAULT_USER_PASSWORD);
//        given(userService.create(createUserRequestDto, BASE_URL)).willReturn(new UserDto(DEFAULT_USER_NAME));
//
//        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//        params.put("name", Collections.singletonList(DEFAULT_USER_NAME));
//        params.put("email", Collections.singletonList(DEFAULT_USER_EMAIL));
//        params.put("password", Collections.singletonList(DEFAULT_USER_PASSWORD));
//
//        mockMvc.perform(post("/user")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .queryParams(params)
//                        .with(user(DEFAULT_USER_NAME).roles("ANONYMOUS")))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
//                .andExpect(view().name("sign_up_success"));
//
//    }
//
//    @Test
//    public void testVerificationSuccess() throws Exception {
//        given(userService.verify(VERIFICATION_CODE)).willReturn(true);
//
//        mockMvc.perform(get("/verification?code=" + VERIFICATION_CODE)
//                        .with(user("user").roles("USER")))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
//                .andExpect(view().name("verification_success"));
//
//        verify(userService, times(1)).verify(VERIFICATION_CODE);
//        verifyNoMoreInteractions(userService);
//    }
//
//    @Test
//    public void testVerificationFailed() throws Exception {
//        given(userService.verify(VERIFICATION_CODE)).willReturn(false);
//
//        mockMvc.perform(get("/verification?code=" + VERIFICATION_CODE)
//                        .with(user("user").roles("AN0NYMOUS")))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_HTML))
//                .andExpect(view().name("verification_failed"));
//
//        verify(userService, times(1)).verify(VERIFICATION_CODE);
//        verifyNoMoreInteractions(userService);
//    }
//}
