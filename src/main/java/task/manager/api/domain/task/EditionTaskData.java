package task.manager.api.domain.task;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record EditionTaskData(
        @NotNull
        Long id,
        String title,
        String description,
        @NotNull
        LocalDateTime duedate,
        Boolean completed

) {

}
