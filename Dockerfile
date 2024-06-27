##FROM openjdk:17
#FROM openjdk:17-jdk-slim
#ARG JAR_FILE=target/hangman-0.0.1-SNAPSHOT.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java", "-jar", "/app.jar"]

FROM openjdk:17-jdk-slim
ARG JAR_FILE=build/libs/hangmanGame3-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

