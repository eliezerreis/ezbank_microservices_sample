#!/bin/bash

# List of services
services=("accounts" "cards" "loans" "notificationserver" "configserver" "apigateway" "eurekaserver")

# Loop through each service
for service in "${services[@]}"; do
  echo "Building and Dockerizing service: $service"

  cd $service
  mvn -DskipTests clean install
  docker build . -t ezbank/$service:0.0.1 -t ezbank/$service:latest
  cd ..

  echo "$service Docker image built successfully."
done


#docker run -d --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:4.0-management
#docker build . -t ezbank/cards:0.0.1 -t ezbank/cards:latest

