package task.manager.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import task.manager.api.domain.task.*;

@RequestMapping("tasks")
@RestController
@SecurityRequirement(name = "bearer-key")
public class TaskController {

    @Autowired
    private TaskRepository repository;

    @GetMapping
    public ResponseEntity<Page<ListTaskData>> listIncompletedTask(@PageableDefault(size = 10, sort = {"id"}) Pageable pagination){
        var page = repository.findAllByCompletedFalse(pagination).map(ListTaskData::new);

        return ResponseEntity.ok(page);

    }

    @GetMapping("/{id}")
    public ResponseEntity listIncompletedById(@PathVariable Long id){
        var task = repository.getReferenceById(id);

        return ResponseEntity.ok(new TaskDetailingData(task));
    }

    @PostMapping
    @Transactional
    public ResponseEntity sendTask(@RequestBody @Valid TaskRegisterData data, UriComponentsBuilder uriBuilder){
        var task = new Task(data);
        repository.save(task);
        var uri = uriBuilder.path("/tasks/{id}").buildAndExpand(task.getId()).toUri();

        return ResponseEntity.created(uri).body(new TaskDetailingData(task));
    }

    @PutMapping
    @Transactional
    public ResponseEntity editTask(@RequestBody @Valid EditionTaskData data){
        var task = repository.getReferenceById(data.id());
        task.updateTask(data);

        return ResponseEntity.ok(new TaskDetailingData(task));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteTas(@PathVariable Long id){
        var task = repository.getReferenceById(id);
        task.delete();

        return ResponseEntity.noContent().build();
    }

}
