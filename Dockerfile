# ==============================
# Build Stage (Maven + JDK)
# ==============================
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Set working directory
WORKDIR /app

# Copy all project files
COPY . .

# Build the project and skip tests
RUN mvn clean package -DskipTests

# ==============================
# Run Stage (Slim JDK)
# ==============================
FROM openjdk:21-jdk-slim

# Set working directory
WORKDIR /app

# Copy JAR from build stage
COPY --from=build /app/target/hotel-booking-system-0.0.1-SNAPSHOT.jar app.jar

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
