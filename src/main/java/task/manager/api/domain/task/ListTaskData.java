package task.manager.api.domain.task;

import java.time.LocalDateTime;

public record ListTaskData(
        Long id,
        String title,
        String description,
        LocalDateTime duedate,
        Boolean completed
) {
    public ListTaskData(Task task){
        this(task.getId(), task.getTitle(), task.getDescription(), task.getDuedate(), task.getCompleted());
    }
}
