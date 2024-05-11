package itis.solopov.controller;

import itis.solopov.dto.UpdateUserRequestDto;
import itis.solopov.model.User;
import itis.solopov.service.UserService;
import itis.solopov.service.exception.WrongPasswordException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api", produces = "application/json")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable String id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("user/update")
    public ResponseEntity<Boolean> updateUser(@RequestBody UpdateUserRequestDto requestDto) throws WrongPasswordException {
        return ResponseEntity.ok(userService.updateUser(requestDto));
    }
}
