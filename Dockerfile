# Stage 1: Build the application
FROM maven:3.8-openjdk-25-slim AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Create the production image
FROM eclipse-temurin:25-jre-alpine
WORKDIR /app
COPY --from=build /app/target/travel-website-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]