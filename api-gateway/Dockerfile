FROM openjdk:19
COPY target/*.jar review-service.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","review-service.jar"]