FROM openjdk:11-jdk-slim
EXPOSE 8080
ENTRYPOINT ["java","-jar","SpringBootDemoProject-0.0.1-SNAPSHOT.jar"]
