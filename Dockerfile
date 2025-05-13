# Stage 1: Build with Gradle
FROM gradle:8.5-jdk17 AS builder
WORKDIR /app

# Copy Gradle wrapper and configuration first (to leverage Docker cache)
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# Pre-download dependencies
RUN ./gradlew dependencies

# Copy the rest of the project
COPY src src

# Build the JAR (skip tests if needed)
RUN ./gradlew clean build -x test

# Stage 2: Run with lightweight JRE
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy the built JAR from the builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose port (match your app's configured port)
EXPOSE 8080

# Run the Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
