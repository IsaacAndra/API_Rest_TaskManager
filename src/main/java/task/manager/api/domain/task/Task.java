package task.manager.api.domain.task;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "tasks")
@Entity(name = "Task")
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Task {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private LocalDateTime duedate;

    private Boolean completed;

    public Task(TaskRegisterData data) {

        this.title = data.title();
        this.description = data.description();
        this.duedate = data.duedate();
        this.completed = false;
    }

    public void updateTask(EditionTaskData data) {

        if (data.title() != null){
            this.title = data.title();
        }
        if (data.description() != null){
            this.description = data.description();
        }
        if (data.duedate() != null){
            this.duedate = data.duedate();
        }
        if (data.completed() != null){
            this.completed = data.completed();
        }

    }
}
