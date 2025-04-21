# 1. Gradle 빌드 환경용 임시 이미지
FROM gradle:8.5-jdk17 AS build
WORKDIR /app

# 2. 프로젝트 소스 복사
COPY . .

# 3. 프로젝트 빌드 (jar 파일 생성)
RUN gradle build -x test

# 4. 실행용 경량 이미지
FROM openjdk:17-jdk-slim
WORKDIR /app

# 5. 빌드 단계에서 만든 jar 복사
COPY --from=build /app/build/libs/*.jar app.jar

# 6. 실행 명령
ENTRYPOINT ["java", "-jar", "app.jar"]
