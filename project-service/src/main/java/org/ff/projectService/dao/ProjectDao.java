package org.ff.projectService.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ff.configModule.model.Project;
import org.ff.configModule.model.Ticket;
import org.ff.projectService.repository.ProjectRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ProjectDao {
    private final ProjectRepository projectRepository;
    private final MongoTemplate  mongoTemplate;
    public Project saveProject(Project project){
        return projectRepository.save(project);
    }

    public Project getProject(String id) {
        return projectRepository.findById(id).get();
    }
    public Project updateProject(Project project) {
        log.info(project.toString() + "Project Object");
        log.info(project.getId() + " Project Id");

        Query query = new Query();
        Update update = new Update();
        for(Ticket ticket : project.getTickets()){
            update.addToSet("tickets",ticket);
        }
        Project andModify = mongoTemplate.findAndModify(
                query.addCriteria(Criteria.where("_id").is(project.getId())),
                update,
                Project.class,
                "project"
        );
        Project project1 = getProject(andModify.getId());
        System.out.println(project1+" after update by kafka in dao");
        return project1;
    }
}
