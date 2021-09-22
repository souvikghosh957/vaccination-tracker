#pull base image
FROM openjdk:8-jdk-alpine

#expose port 8080
EXPOSE 8082

#default command
CMD java -jar /target/govt-id-approver-0.0.1-SNAPSHOT.jar

#copy vaccination-tracker to docker image
ADD ./target/govt-id-approver-0.0.1-SNAPSHOT.jar /target/govt-id-approver-0.0.1-SNAPSHOT.jar