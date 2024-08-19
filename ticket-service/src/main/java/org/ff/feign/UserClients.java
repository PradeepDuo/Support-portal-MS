package org.ff.feign;


import org.ff.commonModule.model.Users;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "USER-SERVICE")
public interface UserClients {
    @GetMapping("/api/users/hi")
    public ResponseEntity<Users> getUsersByEmail(@RequestParam(name = "email") String email);

}
