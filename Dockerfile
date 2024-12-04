# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the application JAR file to the container
COPY target/tic-tac-toe-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot application uses
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]
