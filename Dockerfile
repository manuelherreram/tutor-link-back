# Stage 1: Build the application
FROM maven:3.8.4-openjdk-17 as build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install

# Stage 2: Run the app
FROM openjdk:17-jdk-alpine
WORKDIR /app

# Copy the built jar file from the build stage
COPY --from=build /app/target/grupo6-0.0.1-SNAPSHOT.jar ./tutor-app.jar

# Copy the .env file to the container
COPY .env /app/.env

# Copy the script to the container
COPY src/main/resources/create_firebase_config.sh /app/create_firebase_config.sh
RUN chmod +x /app/create_firebase_config.sh
RUN /app/create_firebase_config.sh

EXPOSE 8080
CMD ["java", "-jar", "tutor-app.jar"]
