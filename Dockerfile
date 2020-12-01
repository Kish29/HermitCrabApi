FROM openjdk:15
ADD target/*.jar app.jar
EXPOSE 5555
ENTRYPOINT ["java", "-jar", "/app.jar"]