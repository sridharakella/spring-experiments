FROM java:8-jre
COPY build/libs/sub-service-1.0-SNAPSHOT.jar /tmp/app.jar
ENTRYPOINT exec java -jar /tmp/app.jar
EXPOSE 8080
