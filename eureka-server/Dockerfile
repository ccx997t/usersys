FROM openjdk:8-jdk-alpine
EXPOSE 8761
COPY target/eureka-server-1.jar   eureka-server-1.jar
ENTRYPOINT ["java","-jar","/eureka-server-1.jar"]