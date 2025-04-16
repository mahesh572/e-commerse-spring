#stage1
# initialize build and set base image for first stage
FROM maven:3.8.3-openjdk-17-slim AS stage1
# speed up Maven JVM a bit
ENV MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"
# set working directory which is in container
WORKDIR /opt/demo
# copy just pom.xml
COPY pom.xml .
# go-offline using the pom.xml
RUN mvn dependency:go-offline
# copy your other files
COPY ./src ./src
# compile the source code and package it in a jar file
RUN mvn clean install -Dmaven.test.skip=true


#Stage 2
# set base image for second stage
#FROM openjdk:17-jdk-slim  almost occupied 486 MB size  -  eclipse-temurin:17-jdk-alpine - 380MB
FROM eclipse-temurin:17-jdk-alpine
# set deployment directory
WORKDIR /opt/demo
COPY --from=stage1 /opt/demo/target/eureka-server-1.0.0.jar /opt/eureka-server-1.0.0.jar
EXPOSE 8761
# Download agent
ADD https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/download/v2.11.0/opentelemetry-javaagent.jar /app/libs/opentelemetry-javaagent.jar
ENTRYPOINT ["java", "-javaagent:/app/libs/opentelemetry-javaagent.jar", "-jar", "/opt/eureka-server-1.0.0.jar"]