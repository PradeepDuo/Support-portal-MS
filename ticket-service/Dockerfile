FROM openjdk:19
ARG JAR_FILE=target/*exe.jar
COPY ${JAR_FILE} ticketservice.jar
ENTRYPOINT ["java","-jar","/ticketservice.jar"]
EXPOSE 8083