FROM localhost:5000/posa-java8:1.0.0
MAINTAINER Raghunath Posa <raghunath.posa@gmail.com>
VOLUME /tmp

ARG JAR_FILE
ADD ${JAR_FILE} dogs.jar

ENTRYPOINT ["/usr/bin/java", "-jar", "/dogs.jar"]

EXPOSE 8090

