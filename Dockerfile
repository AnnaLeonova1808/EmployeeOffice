FROM openjdk:17-jdk
ARG JAR_DILE=target/*.jar
COPY ./target/empOf.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]