#pull base image
FROM openjdk:8-jdk-alpine

#expose port 8080
EXPOSE 8888

#default command
CMD java -jar /target/spring-cloud-config-server-0.0.1-SNAPSHOT.jar

#copy vaccination-tracker to docker image
ADD ./target/spring-cloud-config-server-0.0.1-SNAPSHOT.jar /target/spring-cloud-config-server-0.0.1-SNAPSHOT.jar