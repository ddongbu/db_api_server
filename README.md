# db_api_server

Controller -> Service -> Dto -> Repository -> Model

🔵 전체 흐름 요약

회원가입/로그인 경로 (/api/v1/auth/**) 는 인증 없이 접근 가능하게 permitAll().

그 외 모든 요청은 JWT 토큰을 통해 인증 필요 (authenticated()).

요청이 들어올 때마다 JwtAuthenticationFilter가 먼저 동작해서 토큰을 검증.

토큰이 유효하면 SecurityContextHolder에 Authentication 객체를 세팅.

인증 실패나 권한 거부(Exception Handling)는 커스텀 핸들러로 처리 (CustomAccessDeniedHandler, CustomAuthenticationHandler).

@Component는 Spring이 자동으로 Bean으로 등록해준다.
@Configuration은 Spring Security 설정을 위한 클래스임을 나타낸다.
@EnableWebSecurity는 Spring Security를 활성화하는 어노테이션이다.

별다른 의존성 주입이 없는 핸들러들은 그냥 @Component로 등록하는게 깔끔하다.
만약 핸들러 안에 다른 서비스등을 주입해야 한다면 -> 여전히 @Component로 등록하되,
@Autowired를 통해 의존성 주입을 해주면 된다.