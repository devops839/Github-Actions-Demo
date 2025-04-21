# Stage 1: Build the application with Maven
FROM maven:3.8.6-openjdk-17 AS builder 
WORKDIR /app 
COPY pom.xml . 
COPY src ./src 
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image

FROM openjdk:17-jdk-slim
WORKDIR /app COPY --from=builder /app/target/travel-website-0.0.1-SNAPSHOT.jar /app/app.jar 
EXPOSE 8080 
CMD ["java", "-jar", "/app/app.jar"]