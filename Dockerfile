FROM openjdk:8-jdk-alpine

ARG JAR_FILE=target/find-links.jar
ARG JAR_LIB_FILE=target/lib/

# cd /usr/local/rogerio
WORKDIR /usr/local/rogerio

# copy target/find-links.jar /usr/local/rogerio/app.jar
COPY ${JAR_FILE} app.jar

# copy project dependencies
# cp -rf target/lib/  /usr/local/rogerio/lib
ADD ${JAR_LIB_FILE} lib/

# java -jar /usr/local/rogerio/app.jar
ENTRYPOINT ["java","-jar","app.jar"]