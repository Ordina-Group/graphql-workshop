FROM openjdk:8-jre
VOLUME /tmp
ADD target/graphql-bff-0.0.1-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
EXPOSE 8082
ENTRYPOINT ["java","-jar","/app.jar"]