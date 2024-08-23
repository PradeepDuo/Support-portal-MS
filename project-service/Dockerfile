FROM openjdk:19
ARG JAR_FILE=target/*exe.jar
COPY ${JAR_FILE} projectservice.jar
ENTRYPOINT ["java","-jar","/projectservice.jar"]
EXPOSE 8082