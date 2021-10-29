FROM openjdk:11.0.11-jre-slim-buster

# Refer to Maven build -> finalName
ARG JAR_FILE=target/interview-transactions-api.jar

# cd /opt/app
WORKDIR /opt/app

COPY ${JAR_FILE} interview-transactions-api.jar

EXPOSE 8082 8082

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","interview-transactions-api.jar"]