package com.db.db_server.auth.service;

import com.db.db_server.auth.dto.JoinDto;
import com.db.db_server.auth.dto.LoginDto;
import com.db.db_server.auth.dto.TokenDto;
import com.db.db_server.auth.entity.UserEntity;
import com.db.db_server.auth.exception.AuthenticationExceptions.UserNotFoundException;
import com.db.db_server.auth.repository.AuthRepository;
import com.db.db_server.core.component.AuthValidator;
import com.db.db_server.core.component.JwtTokenProvider;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements  AuthService{
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthRepository authRepository;
    private final AuthValidator authValidator;

    @Transactional
    public TokenDto login(LoginDto loginDto){
        // 1. 이메일로 사용자 조회
        UserEntity user = authRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(UserNotFoundException::new);
        // 2. 사용자 활성화 여부 확인
        authValidator.validateUserActive(user);
        // 3. 비밀번호 확인
        authValidator.validateUserPassword(user, loginDto.getPassword());
        String token = jwtTokenProvider.generateToken(user.getEmail());
        // 4. 로그인 성공
        return new TokenDto(token);
    }

    @Transactional
    public String signUp(JoinDto joinDto) {
        // 1. 이메일 포멧 확인
        authValidator.validateUserEmailFormat(joinDto.getEmail());
        // 1.1 비밀번호 포멧 확인
//        authValidator.validateUserPasswordFormat(joinDto.getPassword());
        // 2. 이메일 중복 확인
        authValidator.checkEmailDuplicate(joinDto.getEmail());

        // 비밀번호를 암호화하는 로직을 추가
        String rawPassword = joinDto.getPassword();
        String encodedPassword = authValidator.encodePassword(rawPassword);

        UserEntity userEntity = joinDto.toEntity(encodedPassword);

        authRepository.save(userEntity);

        return "Sign Up Success";
    }

}
