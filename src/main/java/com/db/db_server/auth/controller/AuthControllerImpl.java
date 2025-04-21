package com.db.db_server.auth.controller;

import com.db.db_server.auth.dto.JoinDto;
import com.db.db_server.auth.dto.LoginDto;
import com.db.db_server.auth.dto.TokenDto;
import com.db.db_server.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {

    private final AuthService authService;

    @Override
    public ResponseEntity<TokenDto> login(LoginDto loginDto) {
        return ResponseEntity.ok(authService.login(loginDto));
    }

    @Override
    public ResponseEntity<String> signUp(JoinDto joinDto) {
        return ResponseEntity.ok(authService.signUp(joinDto));
    }

}
