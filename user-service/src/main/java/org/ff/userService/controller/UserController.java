package org.ff.userService.controller;

import lombok.RequiredArgsConstructor;


import org.ff.commonModule.dto.UserDto;
import org.ff.commonModule.model.Users;
import org.ff.commonModule.util.ApiResponse;
import org.ff.userService.dao.UserDao;
import org.ff.userService.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
