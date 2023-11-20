#  Project Description and Deployment

## Installing dependencies

   ### Docker
   Install docker. It is recommended to follow the instructions from the official site.

   ### JDK
   Install JDK.  JDK 1.8 is recommended.

   ### apache-maven
   Install apache-maven

   ### Mysql
   Install Mysql. 

## This project has four modules

* eureka-server : service discovery and registration
* userinfo-common: used to store tool classes and other modules common code.
* userinfo-service: service provider, it provides  apis to manipulate the database and send email.
* userinfo-userinfo-restapi: service consumer, it can call the userinfo-service api
     
   
##  Deployment

* create database table ,use db.sql
* use jdk  and maven , generate  three jars for the three modules
* excute deploy.sh,  it can build three docker images and run docker three containers.
   
   ```bash
    sh deploy.sh
   ```

##  REST API documentation 

* use swagger and annotation
* http://localhost:9090/swagger-ui/index.html
* http://localhost:8080/swagger-ui/index.html
   
