package org.ff;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.math.ec.custom.sec.SecT113Field;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

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