# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="sunil_kumar@ezcorp.com"

#Name of the environment for which image is being built
ARG env
ENV ENVIRONMENT=${env:-dev}

# Name of the service
ARG servicename
ENV SERVICE_NAME=${servicename:-ABC}

#Create Directory inside the image
RUN mkdir -p /opt/${SERVICE_NAME}
RUN chmod -R 555 /opt/${SERVICE_NAME}
RUN mkdir -p /opt/${SERVICE_NAME}/logs
RUN chmod -R 555 /opt/${SERVICE_NAME}/logs

WORKDIR /opt/${SERVICE_NAME}

# Add a volume pointing to /tmp
VOLUME /opt/${SERVICE_NAME}/logs:/opt/java/CP/logs

# Make port 9001 available to the world outside this container
EXPOSE 8080

# copy jar file to the location /opt/${SERVICE_NAME}/ in the container
COPY target/${SERVICE_NAME}-0.0.1-SNAPSHOT.jar /opt/${SERVICE_NAME}/

# Add the application's configurations for DEV Environment to the container
COPY resources_dev /opt/$SERVICE_NAME/

# Add the application's configurations for QA Environment to the container
COPY resources_qa /opt/$SERVICE_NAME/

# Add the application's configurations for UAT Environment to the container
COPY resources_uat /opt/$SERVICE_NAME/

RUN chmod -R 555 /opt/$SERVICE_NAME/

# Run the jar file
ENTRYPOINT java -Dlog4j.configurationFile=file:resources_${ENVIRONMENT}/log4j2.xml -DLog4jContextSelector=org.apache.logging.log4j.core.async.AsyncLoggerContextSelector -jar ${SERVICE_NAME}-0.0.1-SNAPSHOT.jar --spring.config.location=file:resources_${ENVIRONMENT}/
