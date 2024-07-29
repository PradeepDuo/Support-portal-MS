package org.ff.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallBackController {

    @GetMapping("fallBack")
    public String fallBackMethod(){
        return "Service is currently unavailable";
    }

}
