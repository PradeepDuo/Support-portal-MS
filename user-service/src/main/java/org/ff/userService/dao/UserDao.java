package org.ff.userService.dao;

import lombok.RequiredArgsConstructor;
import org.ff.configModule.model.Project;
import org.ff.configModule.model.Users;
import org.ff.userService.repository.UserRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.Queue;

@Repository
@RequiredArgsConstructor
public class UserDao {

    private final UserRepository userRepository;
    private final MongoTemplate mongoTemplate;


    public Users saveUser(Users user){
        return userRepository.save(user);
    }
    public Users getUserById(String userId){
        return userRepository.findById(userId).orElseThrow(RuntimeException::new);
    }

    public Users getUserByEmail(String email) {
//        Users users = mongoTemplate.findOne(new Query(Criteria.where("id").is("669e8eb60f03ff154f6d0592")), Users.class, "users");
        Users users = userRepository.findByEmail(email);
        return users;
    }

    public Users updateUsers(Users users){
        Query query = new Query();
        Update update = new Update();
        for (Project project : users.getProjects()) {
            update.addToSet("projects", project);
        }
        return mongoTemplate.findAndModify(
                query.addCriteria(Criteria.where("-id")
                        .is(users.getId())),
                update,
                Users.class,
                "users");
    }

}
