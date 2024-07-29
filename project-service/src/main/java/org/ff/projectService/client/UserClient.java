package org.ff.projectService.client;

import org.ff.configModule.model.Users;
import org.ff.configModule.util.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "USER-SERVICE")
public interface UserClient {
    @GetMapping("/api/users/hi")
    public ResponseEntity<Users> getUsersByEmail(@RequestParam(name = "email") String email);
    @PutMapping("/api/users")
    public Users updateUser(@RequestBody Users users);

}
