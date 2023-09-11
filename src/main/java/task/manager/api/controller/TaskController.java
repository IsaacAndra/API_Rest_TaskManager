package task.manager.api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import task.manager.api.domain.task.Task;
import task.manager.api.domain.task.TaskDetailingData;
import task.manager.api.domain.task.TaskRegisterData;
import task.manager.api.domain.task.TaskRepository;

@RequestMapping("tasks")
@RestController
public class TaskController {

    @Autowired
    private TaskRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity sendTask(@RequestBody @Valid TaskRegisterData data, UriComponentsBuilder uriBuilder){
        var task = new Task(data);
        repository.save(task);

        var uri = uriBuilder.path("/tasks/{id}").buildAndExpand(task.getId()).toUri();

        return ResponseEntity.created(uri).body(new TaskDetailingData(task));

    }

}
