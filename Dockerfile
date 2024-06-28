# Stage 1: Build the application
FROM maven:3.8.4-openjdk-17 as build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean install

#Stage 2: Run the app
FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/target/grupo6-0.0.1-SNAPSHOT.jar ./tutor-app.jar
EXPOSE 8080
CMD ["java", "-jar", "tutor-app.jar"]
