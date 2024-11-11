# Mvn Build
FROM maven:3.8.6-eclipse-temurin-17-focal AS build
WORKDIR /home/app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Jar Package
FROM eclipse-temurin:17-jre-focal
WORKDIR /app
COPY --from=build /home/app/target/elokuvaprojekti_sara-0.0.1-SNAPSHOT.jar /app/elokuvaprojekti_sara.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/elokuvaprojekti_sara.jar"]
