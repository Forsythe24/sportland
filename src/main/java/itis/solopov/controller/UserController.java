package itis.solopov.controller;

import itis.solopov.dto.CreateUserRequestDto;
import itis.solopov.dto.JwtResponse;
import itis.solopov.dto.LogInUserRequestDto;
import itis.solopov.model.User;
import itis.solopov.service.UserService;
import itis.solopov.service.exception.WrongPasswordException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;

@RestController
@RequestMapping(produces = "application/json")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/sign_up")
    public ResponseEntity<User> create(@RequestBody CreateUserRequestDto requestDto) throws RoleNotFoundException {
        return ResponseEntity.ok(userService.create(requestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<User> logIn(@RequestBody LogInUserRequestDto requestDto) throws WrongPasswordException {
        return ResponseEntity.ok(userService.logIn(requestDto));
    }
}
