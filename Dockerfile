# Use a base image with Java
FROM openjdk:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy the pom.xml file into the container
COPY pom.xml .

# Download dependencies (optional step)
RUN ./mvnw dependency:go-offline

# Copy the entire source code into the container
COPY src /app/src

# Build the Spring Boot application
RUN ./mvnw clean package -DskipTests

# Expose the port the application will run on (default Spring Boot port)
EXPOSE 8080

# Run the Spring Boot application
CMD ["java", "-jar", "target/travel-website-0.0.1-SNAPSHOT.jar"]
