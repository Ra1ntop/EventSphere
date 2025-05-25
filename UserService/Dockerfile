FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build/libs/UserService-0.0.1-SNAPSHOT.jar UserService.jar
ENTRYPOINT ["java", "-jar", "UserService.jar"]
