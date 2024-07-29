package org.ff.userService.service;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.ff.userService.dao.UserDao;
import org.ff.configModule.dto.UserDto;
import org.ff.configModule.model.Users;
import org.ff.configModule.util.ResponseUtil;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.ff.configModule.util.ApiResponse;

import java.util.Objects;

@Service
@AllArgsConstructor
public class UserService {
     private final UserDao userDao;
     private  final ModelMapper modelMapper;
     private final ApiResponse apiResponse;
    public ResponseEntity<ApiResponse> saveUser(UserDto userDto){
        Users user = modelMapper.map(userDto,Users.class);
        Users savedUser = userDao.saveUser(user);
        apiResponse.setResponse(savedUser);
        apiResponse.setHttpStatus(HttpStatus.CREATED);
        apiResponse.setStatusCode(HttpStatus.CREATED.value());
       return ResponseUtil.getCreatedResponse(apiResponse);
    }
    public ResponseEntity<ApiResponse> getUsers(String email){
        Users user = userDao.getUserByEmail(email);
        if(Objects.nonNull(user)){
            apiResponse.setStatusCode(HttpStatus.OK.value());
            apiResponse.setHttpStatus(HttpStatus.OK);
            apiResponse.setResponse(user);
            return ResponseUtil.getOkResponse(user);
        }else return ResponseUtil.getNoContentResponse("user not found");
    }


}
