FROM adoptopenjdk/openjdk11:jdk-11.0.8_10-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar","/app.jar"]