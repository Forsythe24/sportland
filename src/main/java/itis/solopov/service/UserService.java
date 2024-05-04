package itis.solopov.service;

import itis.solopov.dto.CreateUserRequestDto;
import itis.solopov.dto.UserDto;

import javax.mail.MessagingException;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface UserService {

    List<UserDto> findAllByName(String name);

    UserDto create(CreateUserRequestDto dto, String url);

    boolean verify(String code);

    void sendVerificationCode(String mail, String name, String code, String baseUrl) throws MessagingException, UnsupportedEncodingException;
}
