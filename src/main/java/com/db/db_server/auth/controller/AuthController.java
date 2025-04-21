package com.db.db_server.auth.controller;

import com.db.db_server.auth.dto.JoinDto;
import com.db.db_server.auth.dto.LoginDto;
import com.db.db_server.auth.dto.TokenDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/auth")
public interface AuthController {

    @PostMapping("/login")
    ResponseEntity<TokenDto> login(@RequestBody LoginDto loginDto);

    @PostMapping
    ResponseEntity<String> signUp(@RequestBody JoinDto joinDto);
}
