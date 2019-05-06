# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="sunil_kumar@ezcorp.com"

# Name of the service
ARG servicename
ENV SERVICE_NAME=${servicename:-test}

#Create Directory inside the image
RUN mkdir -p /opt
RUN chmod -R 555 /opt
RUN mkdir -p /opt/logs
RUN chmod -R 555 /opt/logs

WORKDIR /opt/

# Add a volume pointing to logs
#VOLUME /opt/logs:/opt/java/CP/logs

# Make port 8080 available to the world outside this container
#EXPOSE 8080

# copy jar file to the location /opt/ in the container
COPY target/${SERVICE_NAME}-0.0.1-SNAPSHOT.jar /opt/

# copy jar file to the location /opt/ in the container
RUN mkdir -p /opt/config
COPY resources_dev/* /opt/config/

RUN chmod -R 555 /opt/config 
RUN chmod -R 555 /opt/

# Run the jar file
ENTRYPOINT java -Dlog4j.configurationFile=file:/opt/config/log4j2.xml -DLog4jContextSelector=org.apache.logging.log4j.core.async.AsyncLoggerContextSelector -jar /opt/$SERVICE_NAME/${SERVICE_NAME}-0.0.1-SNAPSHOT.jar --spring.config.location=file:/opt/config/
