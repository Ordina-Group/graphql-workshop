FROM openjdk:8-jre
VOLUME /tmp
ADD target/fleet-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
EXPOSE 8081
ENTRYPOINT ["java","-jar","/app.jar"]