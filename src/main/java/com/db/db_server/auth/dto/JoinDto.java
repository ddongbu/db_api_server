package com.db.db_server.auth.dto;

import com.db.db_server.auth.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JoinDto {
    private String email;
    private String password;

    // 엔티티로 보낼때 사용하는 빌더패턴
    public UserEntity toEntity(String password) {
        return UserEntity.builder()
                .email(email)
                .password(password)
                .build();
    }


}
