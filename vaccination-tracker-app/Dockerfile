#pull base image
FROM openjdk:8-jdk-alpine

#expose port 8080
EXPOSE 8081

#default command
CMD java -jar /target/vaccination-tracker-app-0.0.1-SNAPSHOT.jar

#copy vaccination-tracker to docker image
ADD ./target/vaccination-tracker-app-0.0.1-SNAPSHOT.jar /target/vaccination-tracker-app-0.0.1-SNAPSHOT.jar