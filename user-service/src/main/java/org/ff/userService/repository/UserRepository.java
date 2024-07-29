package org.ff.userService.repository;

import org.ff.configModule.model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<Users,String> {

     Users findByEmail(String email);
}
