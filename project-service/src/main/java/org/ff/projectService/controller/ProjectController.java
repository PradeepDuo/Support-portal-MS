package org.ff.projectService.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.ff.commonModule.dto.ProjectDto;
import org.ff.commonModule.model.Project;
import org.ff.commonModule.util.ApiResponse;
import org.ff.projectService.dao.ProjectDao;
import org.ff.projectService.service.ProjectService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("api/project")
public class ProjectController {
    private final ProjectService projectService;
    @PostMapping
    public ResponseEntity<ApiResponse> saveProject(@RequestBody ProjectDto projectDto, @RequestParam (name = "email")String email) {
        return projectService.saveProject(projectDto, email);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getProject(@PathVariable(name = "id")String id){
        return projectService.getProject(id);
    }




    @PutMapping("/flush")
    public ResponseEntity<ApiResponse> flushProject(@RequestBody Project project){
        log.info(project+" Project Object in project service");
       return projectService.updateProject(project);
        //return project1;
    }
}
