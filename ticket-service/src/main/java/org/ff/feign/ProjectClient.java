package org.ff.feign;

import org.ff.configModule.model.Project;
import org.ff.configModule.util.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "PROJECT-SERVICE", path = "/api/project")
public interface ProjectClient {
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getProject(@PathVariable(name = "id")String id);

    @PutMapping("/flush")
    public ResponseEntity<Project> flushProject(@RequestBody Project project);
}
