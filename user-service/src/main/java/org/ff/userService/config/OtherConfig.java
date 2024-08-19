package org.ff.userService.config;

import org.ff.commonModule.util.ApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OtherConfig {
    @Bean
    public ModelMapper modelMapper() {
        var modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        modelMapper.getConfiguration().setCollectionsMergeEnabled(false);
        return modelMapper;
    }
    @Bean
    public ApiResponse apiResponse(){
        return new ApiResponse();
    }

//    @Bean
//    public UserDao userDao(){
//        Object userRepository = null;
//        return new UserDao();
//    }

}
