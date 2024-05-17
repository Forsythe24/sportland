package itis.solopov.controller;

import itis.solopov.AuthManager;
import itis.solopov.dto.UpdatePasswordRequestDto;
import itis.solopov.dto.UpdateUserRequestDto;
import itis.solopov.dto.UserDto;
import itis.solopov.dto.VerifyCredentialsRequestDto;
import itis.solopov.filter.JwtAuthentication;
import itis.solopov.service.AuthService;
import itis.solopov.service.UserService;
import itis.solopov.service.exception.WrongPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/user", produces = "application/json")
public class UserController {

    private final UserService userService;
    private final AuthService authService;
    private final AuthManager authManager;

    public UserController(UserService userService, AuthService authService, AuthManager authManager) {
        this.userService = userService;
        this.authService = authService;
        this.authManager = authManager;
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String id) {
        if (authManager.isAuthorized()) {
            return ResponseEntity.ok(userService.getUserById(id));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("update")
    public ResponseEntity<Boolean> updateUser(@RequestBody UpdateUserRequestDto requestDto) throws WrongPasswordException {
        if (authManager.isAuthorized()) {
            return ResponseEntity.ok(userService.updateUser(requestDto));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("verify")
    public ResponseEntity<Boolean> verifyCredentials(@RequestBody VerifyCredentialsRequestDto requestDto) {
        Authentication auth = authManager.extractAuthentication();
        if (authManager.isAuthorized()) {
            requestDto.setEmail((String) auth.getCredentials());
            return ResponseEntity.ok(userService.verifyCredentials(requestDto));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("current")
    public ResponseEntity<UserDto> getUser() {
        JwtAuthentication auth = (JwtAuthentication) authManager.extractAuthentication();
        if (authManager.isAuthorized()) {
            return ResponseEntity.ok(userService.getUserById(auth.getId()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("update_password")
    public ResponseEntity<Boolean> updatePassword(@RequestBody UpdatePasswordRequestDto requestDto) {
        Authentication auth = authManager.extractAuthentication();
        if (authManager.isAuthorized()) {
            requestDto.setEmail((String) auth.getCredentials());
            return ResponseEntity.ok(userService.updatePassword(requestDto));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
