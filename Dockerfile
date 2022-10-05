FROM openjdk

EXPOSE 8080

ADD target/biostoom-backend-k8s.jar biostoom-backend-k8s.jar

ENTRYPOINT ["java","-jar","biostoom-backend-k8s.jar"]