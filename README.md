# db_api_server

Controller -> Service -> Dto -> Repository -> Model

🔵 전체 흐름 요약

회원가입/로그인 경로 (/api/v1/auth/**) 는 인증 없이 접근 가능하게 permitAll().

그 외 모든 요청은 JWT 토큰을 통해 인증 필요 (authenticated()).

요청이 들어올 때마다 JwtAuthenticationFilter가 먼저 동작해서 토큰을 검증.

토큰이 유효하면 SecurityContextHolder에 Authentication 객체를 세팅.

인증 실패나 권한 거부(Exception Handling)는 커스텀 핸들러로 처리 (CustomAccessDeniedHandler, CustomAuthenticationHandler).

