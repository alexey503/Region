FROM adoptopenjdk/openjdk11:alpine-jre
ARG JAR_FILE=target/region-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} region.jar
ENTRYPOINT ["java","-jar","region.jar"]