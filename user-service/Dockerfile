FROM openjdk:19
ARG JAR_FILE=target/*exe.jar
COPY ${JAR_FILE} userservice.jar
ENTRYPOINT ["java","-jar","/userservice.jar"]
EXPOSE 8081