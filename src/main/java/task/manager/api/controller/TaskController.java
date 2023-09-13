package task.manager.api.controller;

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

    @PutMapping
    @Transactional
    public ResponseEntity editTask(@RequestBody @Valid EditionTaskData data){
        var task = repository.getReferenceById(data.id());

        task.updateTask(data);

        return ResponseEntity.ok(new TaskDetailingData(task));
    }

    @GetMapping
    public ResponseEntity<Page<ListTaskData>> listTask(@PageableDefault(size = 10, sort = {"id"}) Pageable pagination){
        var page = repository.findAllByCompletedFalse(pagination).map(ListTaskData::new);
        return ResponseEntity.ok(page);
    }

}
