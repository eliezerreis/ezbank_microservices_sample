FROM amazoncorretto:21-alpine-jdk

MAINTAINER eliezerreis@gmail.com

ENV JVM_XMS=${JVM_XMS:-512m}
ENV JVM_XMX=${JVM_XMX:-1024m}

# Set the timezone
ENV TZ=America/New_York

# Update timezone
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# Install curl (and other necessary dependencies)
RUN apk add --no-cache curl

VOLUME ["/mnt"]

EXPOSE 8080
ADD ./target/apigateway.jar apigateway.jar

CMD java -XX:+HeapDumpOnOutOfMemoryError -Xms$JVM_XMS -Xmx$JVM_XMX -DskipTests -jar apigateway.jar
