package org.ff.userService.controller;

import lombok.RequiredArgsConstructor;

import org.apache.catalina.User;
import org.ff.configModule.dto.UserDto;
import org.ff.configModule.model.Users;
import org.ff.userService.dao.UserDao;
import org.ff.userService.repository.UserRepository;
import org.ff.userService.service.UserService;
import org.ff.configModule.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserDefinedFileAttributeView;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserDao userDao;
    @PostMapping()
    public ResponseEntity<ApiResponse> saveUser(@RequestBody UserDto userDto){
        return userService.saveUser(userDto);
    }
    @GetMapping("/hi")
    public ResponseEntity<ApiResponse> getUsersByEmail(@RequestParam (name = "email") String email){
        return userService.getUsers(email);
    }

    @PutMapping
    public Users updateUser(@RequestBody Users users){
        return userDao.updateUsers(users);
    }


}
