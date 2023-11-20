FROM openjdk:8-jdk-alpine
EXPOSE 8080
COPY target/userinfo-restapi-1.jar   userinfo-restapi-1.jar
ENTRYPOINT ["java","-jar","/userinfo-restapi-1.jar"]