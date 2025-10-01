 # FROM openjdk:17
 # EXPOSE 1111
 # ADD target/app.jar app.jar
 # ENTRYPOINT [ "java","-jar","/app.jar" ]

# Step 1: Build stage (Maven + JDK)
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copy all project files
COPY . .

# Build the Spring Boot jar
RUN mvn clean package -DskipTests

# Step 2: Run stage (Slim JDK)
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copy the generated jar from build stage
COPY --from=build /app/target/*.jar app.jar

# Expose default Spring Boot port
EXPOSE 1111

# Start the app
ENTRYPOINT ["java","-jar","app.jar"]
