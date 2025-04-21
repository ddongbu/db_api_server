package com.db.db_server.auth.service;

import com.db.db_server.auth.dto.JoinDto;
import com.db.db_server.auth.dto.LoginDto;
import com.db.db_server.auth.dto.TokenDto;

public interface AuthService {
    TokenDto login(LoginDto loginDto);
    String signUp(JoinDto joinDto);
}
