package itis.solopov.controller;

import itis.solopov.AuthManager;
import itis.solopov.dto.UpdatePasswordRequestDto;
import itis.solopov.dto.UpdateUserRequestDto;
import itis.solopov.dto.UserDto;
import itis.solopov.dto.VerifyCredentialsRequestDto;
import itis.solopov.filter.JwtAuthentication;
import itis.solopov.service.UserService;
import itis.solopov.service.exception.WrongPasswordException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
@RequestMapping(path = "api/user", produces = "application/json")
public class UserController {

    private final UserService userService;
    private final AuthManager authManager;

    public UserController(UserService userService, AuthManager authManager) {
        this.userService = userService;
        this.authManager = authManager;
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PostMapping("update")
    public ResponseEntity<Boolean> updateUser(@RequestBody UpdateUserRequestDto requestDto) throws WrongPasswordException {
        return ResponseEntity.ok(userService.updateUser(requestDto));
    }

    @PostMapping("verify")
    public ResponseEntity<Boolean> verifyCredentials(@RequestBody VerifyCredentialsRequestDto requestDto) {
        Authentication auth = authManager.extractAuthentication();
        requestDto.setEmail((String) auth.getCredentials());
        return ResponseEntity.ok(userService.verifyCredentials(requestDto));
    }

    @GetMapping("current")
    public ResponseEntity<UserDto> getUser() {
        JwtAuthentication auth = (JwtAuthentication) authManager.extractAuthentication();
        return ResponseEntity.ok(userService.getUserById(auth.getId()));
    }

    @PostMapping("update_password")
    public ResponseEntity<Boolean> updatePassword(@RequestBody UpdatePasswordRequestDto requestDto) {
        Authentication auth = authManager.extractAuthentication();
        requestDto.setEmail((String) auth.getCredentials());
        return ResponseEntity.ok(userService.updatePassword(requestDto));
    }
}
