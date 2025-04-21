package com.db.db_server.core.component;

import com.db.db_server.auth.entity.UserEntity;
import com.db.db_server.auth.exception.AuthenticationExceptions.*;
import com.db.db_server.auth.repository.AuthRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class AuthValidator {

    private final AuthRepository authRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    // 이메일로 사용자 조회
    public void validateUserActive(UserEntity user){
        if (!user.getIsActive()){
            throw new UserNotActiveException();
        }
    }
    // 비밀번호 확인
    public void validateUserPassword(UserEntity user, String rawPassword){
        if (!passwordEncoder.matches(rawPassword, user.getPassword())){
            throw new UserPasswordMismatchException();
        }
    }
    // 비밀번호 암호화
    public String encodePassword(String rawPassword){
        return passwordEncoder.encode(rawPassword);
    }

    // 이메일이 null이거나 비어있는지 확인
    // 이메일 형식이 유효한지 확인
    public void validateUserEmailFormat(String email){
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        if (email == null || email.isEmpty() || !email.matches(emailRegex)){
            throw new UserEmailFormatException();
        }
//        // 이메일 유효성 추가 검사 ( 가짜 이메일 필터링)
//        String[] fakeEmailDomains = {"example.com", "test.com", "fake.com"};
//        for (String domain : fakeEmailDomains) {
//            if (email.endsWith(domain)) {
//                throw new UserEmailFormatException();
//            }
//        }
    }
    // 이메일이 이미 존재하는지 확인
    public void checkEmailDuplicate(String email){
        if (authRepository.existsByEmail(email)){
            throw new UserEmailDuplicateException();
        }
    }
    // 비밀번호가 null이거나 비어있는지 확인
    public void validateUserPasswordFormat(String password){
        String passwordRegex = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$";
        if (password == null || password.isEmpty() || !password.matches(passwordRegex)){
            throw new UserPasswordFormatException();
        }
    }
}
