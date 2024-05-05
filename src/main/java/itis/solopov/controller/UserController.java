package itis.solopov.controller;

import itis.solopov.dto.CreateUserRequestDto;
import itis.solopov.dto.LogInUserRequestDto;
import itis.solopov.model.User;
import itis.solopov.service.impl.UserService;
import itis.solopov.service.impl.exception.WrongPasswordException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public @ResponseBody User create(@RequestBody CreateUserRequestDto requestDto) throws RoleNotFoundException {
        return userService.create(requestDto);
    }

    @PostMapping("/login")
    public @ResponseBody User logIn(@RequestBody LogInUserRequestDto requestDto) throws WrongPasswordException {
        return userService.logIn(requestDto);
    }


}
