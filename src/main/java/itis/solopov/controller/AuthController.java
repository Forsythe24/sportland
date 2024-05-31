package itis.solopov.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import itis.solopov.dto.*;
import itis.solopov.model.User;
import itis.solopov.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.message.AuthException;

@Tag(name = "Auth", description = "Authentication, Authorization & Registration")
@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(description = "Sign in by providing credentials (email, password)")
    @PostMapping("login")
    public ResponseEntity<JwtResponse> login(@RequestBody @Validated JwtRequest jwtRequest) {
        return ResponseEntity.ok(authService.login(jwtRequest));
    }

    @PostMapping("token")
    public ResponseEntity<JwtResponse> token(@RequestBody RefreshJwtRequest jwtRequest) throws AuthException {
        return ResponseEntity.ok(authService.token(jwtRequest.getToken()));
    }

    @PostMapping("refresh")
    public ResponseEntity<JwtResponse> refresh(@RequestBody RefreshJwtRequest jwtRequest) throws AuthException {
       return ResponseEntity.ok(authService.refresh(jwtRequest.getToken()));
    }

    @Operation(description = "Sign up by providing their main information")
    @PostMapping("sign_up")
    public ResponseEntity<User> create(@RequestBody @Validated CreateUserRequestDto requestDto) {
        return ResponseEntity.ok(authService.create(requestDto));
    }


    @Operation(description = "Send new password in case user forgot his current one")
    @PostMapping("send_new_password")
    public ResponseEntity<okhttp3.ResponseBody> sendNewPasswordOnEmail(@RequestBody SendNewPasswordRequestDto requestDto) {
        return ResponseEntity.ok(authService.sendNewPasswordOnEmail(requestDto));
    }
}
