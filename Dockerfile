#jdk 17
FROM openjdk:17-jdk-alpine3.13
LABEL maintainer="Tonde"
#set working directory
WORKDIR /app

#copy jar file
COPY target/*.jar app.jar
#expose port
EXPOSE 8080
#run jar file
ENTRYPOINT ["java","-jar","/app.jar"]


