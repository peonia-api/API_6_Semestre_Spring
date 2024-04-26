FROM openjdk:17-oracle
COPY target/*.jar login.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "login.jar"]