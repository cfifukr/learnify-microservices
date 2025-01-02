FROM openjdk:11-jre-slim

WORKDIR /app

COPY target/course-service-1.0.0.jar course-service.jar

ENTRYPOINT ["java", "-jar", "course-service.jar"]

EXPOSE 8080