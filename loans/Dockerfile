FROM amazoncorretto:21-alpine-jdk

MAINTAINER eliezerreis@gmail.com

ARG PROFILE=dev
ARG APP_VERSION
ARG GIT_COMMIT_ID

ENV ENV_PROFILE=$PROFILE
ENV LOG_LEVEL=INFO
ENV APP_VERSION=${APP_VERSION:-unknown}
ENV GIT_COMMIT_ID=${GIT_COMMIT_ID:-unknown}

ENV JVM_XMS=${JVM_XMS:-512m}
ENV JVM_XMX=${JVM_XMX:-1024m}

# Set the timezone
ENV TZ=America/New_York

# Update timezone
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

VOLUME ["/mnt"]

EXPOSE 8090
ADD ./target/loans.jar loans.jar

CMD java -XX:+HeapDumpOnOutOfMemoryError -Xms$JVM_XMS -Xmx$JVM_XMX -DskipTests -Dspring.profiles.active=$ENV_PROFILE -jar loans.jar
