 # FROM openjdk:17
 # EXPOSE 1111
 # ADD target/app.jar app.jar
 # ENTRYPOINT [ "java","-jar","/app.jar" ]

# Step 1: Build stage
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app
COPY . .
RUN ./mvn clean package

# Step 2: Run stage
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
