FROM openjdk:alpine
ADD ./target/rooms-service.jar app.jar
ENTRYPOINT ["sh", "-c", "java -jar app.jar"]
