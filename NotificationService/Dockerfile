FROM openjdk:17-jdk-slim
WORKDIR /app
COPY build/libs/NotificationService-0.0.1-SNAPSHOT.jar NotificationService.jar
ENTRYPOINT ["java", "-jar", "NotificationService.jar"]
