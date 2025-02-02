# docker run -d --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:4.0-management

cd accounts
mvn clean install
docker build . -t ezbank/accounts:0.0.1 -t ezbank/accounts:latest
cd ..

cd cards
mvn clean install
docker build . -t ezbank/cards:0.0.1 -t ezbank/cards:latest
cd ..

cd loans
mvn clean install
docker build . -t ezbank/loans:0.0.1 -t ezbank/loans:latest
cd ..

cd configserver
mvn clean install
docker build . -t ezbank/configserver:0.0.1 -t ezbank/configserver:latest
cd ..