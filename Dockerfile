FROM eclipse-temurin:17-jdk-alpine

ADD target/*.jar maisonchap.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/maisonchap.jar"]