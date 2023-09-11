package task.manager.api.domain.task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record TaskRegisterData(
        @NotBlank
        String title,
        @NotBlank
        String description,
        @NotNull
        LocalDateTime duedate,
        @NotNull
        Boolean completed
) {
}
