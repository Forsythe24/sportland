package itis.solopov.controller;

import itis.solopov.AuthManager;
import itis.solopov.dto.*;
import itis.solopov.model.User;
import itis.solopov.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.message.AuthException;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final AuthService authService;
    private final AuthManager authManager;

    public AuthController(AuthService authService, AuthManager authManager) {
        this.authService = authService;
        this.authManager = authManager;
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
        System.out.println(jwtRequest.getToken());
       return ResponseEntity.ok(authService.refresh(jwtRequest.getToken()));
    }

    @PostMapping("sign_up")
    public ResponseEntity<User> create(@RequestBody CreateUserRequestDto requestDto) {
        return ResponseEntity.ok(authService.create(requestDto));
    }

    @PostMapping("send_new_password")
    public ResponseEntity<okhttp3.ResponseBody> sendNewPasswordOnEmail(@RequestBody SendNewPasswordRequestDto requestDto) {
//        try {
            return ResponseEntity.ok(authService.sendNewPasswordOnEmail(requestDto));
//        } catch (Exception ex) {
//
//        }
    }
}
