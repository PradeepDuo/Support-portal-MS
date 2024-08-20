FROM openjdk:19
ARG JAR_FILE=target/*exe.jar
COPY ${JAR_FILE} commonmodule.jar
ENTRYPOINT ["java","-jar","/commonmodule.jar"]
EXPOSE 8085