FROM openjdk:8-jdk-alpine
EXPOSE 9090
COPY target/userinfo-service-1.jar   userinfo-service-1.jar
ENTRYPOINT ["java","-jar","/userinfo-service-1.jar"]