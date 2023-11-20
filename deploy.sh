#!/bin/sh
cd  eureka-server
echo $PWD
echo 'build eureka-server'
docker build -t usersys/eureka-server  -f Dockerfile .
docker ps | grep "eureka-server" | awk '{print $1 }'|xargs docker stop
echo 'run eureka-server'
docker run -d -p 8761:8761 usersys/eureka-server


cd ../userinfo-service
echo $PWD
docker build -t usersys/userinfo-service  -f Dockerfile .
docker ps | grep "userinfo-service" | awk '{print $1 }'|xargs docker stop
docker run -d -p 9090:9090 usersys/userinfo-service


cd ../userinfo-restapi
echo $PWD
docker build -t usersys/userinfo-restapi  -f Dockerfile .
echo 'build userinfo-restapi'
docker ps | grep "userinfo-restapi" | awk '{print $1 }'|xargs docker stop
echo 'run userinfo-restapi'
docker run -d -p 8080:8080 usersys/userinfo-restapi