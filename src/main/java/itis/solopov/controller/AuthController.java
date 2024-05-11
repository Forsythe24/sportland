package itis.solopov.controller;

import itis.solopov.dto.*;
import itis.solopov.model.User;
import itis.solopov.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.management.relation.RoleNotFoundException;
import javax.security.auth.message.AuthException;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest jwtRequest) {
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

    @PostMapping("sign_up")
    public ResponseEntity<User> create(@RequestBody CreateUserRequestDto requestDto) throws RoleNotFoundException {
        System.out.println(requestDto);
        return ResponseEntity.ok(authService.create(requestDto));
    }

    @PostMapping("verify")
    public ResponseEntity<Boolean> verifyCredentials(@RequestBody VerifyCredentialsRequestDto requestDto) {
        System.out.println(requestDto.getPassword());
        System.out.println(requestDto.getEmail());
        return ResponseEntity.ok(authService.verifyCredentials(requestDto));
    }
}
