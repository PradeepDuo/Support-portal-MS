package org.ff.projectService.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.ff.configModule.model.Project;
import org.ff.configModule.model.Ticket;
import org.ff.projectService.client.UserClient;
import org.ff.projectService.dao.ProjectDao;
import org.ff.configModule.dto.ProjectDto;
import org.ff.configModule.model.Users;
import org.ff.configModule.util.ApiResponse;
import org.ff.configModule.util.ResponseUtil;
import org.ff.userService.dao.UserDao;
import org.ff.userService.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
@AllArgsConstructor
@EnableKafka
public class ProjectService {
    private final ModelMapper modelMapper;
    private final UserClient userClient;
    private final ProjectDao projectDao;
    private final ObjectMapper objectMapper;
    private final MongoTemplate mongoTemplate;



    public ResponseEntity<ApiResponse> saveProject(ProjectDto projectDto, String email){
      Project project =  modelMapper.map(projectDto, Project.class);
      Users user = userClient.getUsersByEmail(email).getBody();
        Project savedProject = projectDao.saveProject(project);
        List<Project> projects = user.getProjects()!=null?user.getProjects():new ArrayList<>();
        projects.add(savedProject);
        user.setProjects(projects);
        Users updatedUser = userClient.updateUser(user);
      return ResponseUtil.getCreatedResponse(savedProject);
    }

    public ResponseEntity<ApiResponse> getProject(String id) {
        Project project = projectDao.getProject(id);
        return ResponseUtil.getOkResponse(project);
    }

    public ResponseEntity<ApiResponse>  updateProject(Project project) {

        return ResponseUtil.getOkResponse(projectDao.updateProject(project));
    }

    @KafkaListener(id = "project listener",topics = "ticket", groupId = "project-group")
    public void updateProjectByKafka (ConsumerRecord<String,Object> record){

        String map = (String) record.value();
        try {
            Project project = objectMapper.readValue(map, Project.class);
            System.out.println(map +" string format");
            System.out.println(record.value()+"project from kafka listener ");
            projectDao.updateProject(project );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
//        Project project = (Project) record .value();


    }
}
