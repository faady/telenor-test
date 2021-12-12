FROM openjdk:11
ADD springio/gs-spring-boot-docker.jar gs-spring-boot-docker.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "gs-spring-boot-docker.jar"]