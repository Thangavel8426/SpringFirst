# Use OpenJDK 17 as base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Make mvnw executable
RUN chmod +x mvnw

# Download dependencies
RUN ./mvnw dependency:go-offline -B

# Copy source code
COPY src src

# Build the application
RUN ./mvnw clean package -DskipTests

# Create a new stage for runtime
FROM openjdk:17-jre-slim

# Set working directory
WORKDIR /app

# Copy the built jar from the build stage
COPY --from=0 /app/target/lab-experiment-logbook-0.0.1-SNAPSHOT.jar app.jar

# Create uploads directory
RUN mkdir -p uploads

# Expose port
EXPOSE 8080

# Set environment variables
ENV SPRING_PROFILES_ACTIVE=production
ENV SERVER_PORT=8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"] 