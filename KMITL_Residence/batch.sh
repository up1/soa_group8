cd email-service
mvn clean package

cd ../user-service
mvn clean package

cd ../rooms-service
mvn clean install

cd ../reservation
mvn clean install
