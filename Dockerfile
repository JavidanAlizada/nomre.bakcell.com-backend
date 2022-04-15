FROM adoptopenjdk/openjdk11:alpine-jre

ARG JAR_FILE=target/nomre.jar

WORKDIR /opt/nomre

COPY ${JAR_FILE} nomre.jar

ENTRYPOINT ["java","-jar","nomre.jar"]