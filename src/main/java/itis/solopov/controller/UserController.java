package itis.solopov.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User")
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

    @Operation(description = "Get user by their id")
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String id) {
        return ResponseEntity.ok(userService.getUserById(id));
    }

    @Operation(description = "Update user providing their data")
    @PostMapping("update")
    public ResponseEntity<Boolean> updateUser(@RequestBody @Validated UpdateUserRequestDto requestDto) throws WrongPasswordException {
        return ResponseEntity.ok(userService.updateUser(requestDto));
    }

    @Operation(description = "Verify user's credentials")
    @PostMapping("verify")
    public ResponseEntity<Boolean> verifyCredentials(@RequestBody VerifyCredentialsRequestDto requestDto) {
        Authentication auth = authManager.extractAuthentication();
        requestDto.setEmail((String) auth.getCredentials());
        return ResponseEntity.ok(userService.verifyCredentials(requestDto));
    }

    @Operation(description = "Get authorized user")
    @GetMapping("current")
    public ResponseEntity<UserDto> getUser() {
        JwtAuthentication auth = (JwtAuthentication) authManager.extractAuthentication();
        return ResponseEntity.ok(userService.getUserById(auth.getId()));
    }

    @Operation(description = "Delete authorized user")
    @GetMapping("delete")
    public ResponseEntity<Boolean> deleteUser() {
        JwtAuthentication auth = (JwtAuthentication) authManager.extractAuthentication();
        return ResponseEntity.ok(userService.deleteUserById(auth.getId()));
    }

    @Operation(description = "Update user's password providing new password")
    @PostMapping("update_password")
    public ResponseEntity<Boolean> updatePassword(@RequestBody @Validated UpdatePasswordRequestDto requestDto) {
        Authentication auth = authManager.extractAuthentication();
        requestDto.setEmail((String) auth.getCredentials());
        return ResponseEntity.ok(userService.updatePassword(requestDto));
    }
}
