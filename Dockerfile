#First stage : MAVEN build
FROM maven:3.8-eclipse-temurin-17 AS MAVEN

COPY ./ ./

RUN mvn clean package

#Second stage : Java 17 build
FROM eclipse-temurin:17-jre-alpine

COPY --from=MAVEN ./target/demo-0.0.1-SNAPSHOT.jar /app.jar

CMD ["java", "-jar", "/app.jar"]