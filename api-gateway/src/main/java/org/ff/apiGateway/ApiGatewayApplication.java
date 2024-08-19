package org.ff.apiGateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class ApiGatewayApplication {

    public static void main(String[] args) {

        SpringApplication.run(ApiGatewayApplication.class,args);
    }

//    public static void fallBackMethod(String[]args,Throwable throwable){
////        return "the  Service is temporarily down";
//        log.info("Service failed");
//    }
}