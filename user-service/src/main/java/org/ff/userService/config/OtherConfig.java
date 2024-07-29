package org.ff.userService.config;
import org.ff.configModule.util.ApiResponse;
import org.ff.userService.dao.UserDao;
import org.ff.userService.repository.UserRepository;
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
