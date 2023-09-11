package task.manager.api.domain.task;

import java.time.LocalDateTime;

public record TaskDetailingData(Long id, String tilte, String description, LocalDateTime duedate, Boolean completed) {

    public TaskDetailingData(Task task){
        this(task.getId(), task.getTitle(), task.getDescription(), task.getDuedate(), task.getCompleted());
    }

}
