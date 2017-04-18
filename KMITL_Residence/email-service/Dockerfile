FROM openjdk:alpine
ADD ./target/email-service.jar app.jar
ENTRYPOINT ["sh", "-c", "java -jar app.jar"]
