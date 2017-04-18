FROM openjdk:alpine
ADD ./target/reservation-service.jar app.jar
ENTRYPOINT ["sh", "-c", "java -jar app.jar"]
