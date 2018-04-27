package pl.jawegiel.todohibernate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "task_table")
public class Task implements Publisher {

    @Id
    @Column(name = "task_id")
    private int taskId;
    public int getTaskId() {
        return taskId;
    }

    @Column(name = "task_data")
    private LocalDateTime data;
    public LocalDateTime getData() {
        return data;
    }

    @Column(name = "task_info")
    private String taskInfo = "aa";
    public String getTaskInfo() {
        return taskInfo;
    }

    @Transient
    // observers list
    private List<Observer> observerList = new ArrayList<>();





    public Task() {
    }

    public Task(int taskId, LocalDateTime data, String taskInfo) {
        this.taskId = taskId;
        this.data = data;
        this.taskInfo = taskInfo;
    }


    @Override
    public void register(Observer o) {
        observerList.add(o);
    }

    @Override
    public void unregister(Observer o) {
        observerList.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observerList) {
            o.update();
        }
    }

    public void taskComes(int taskId) {
        System.out.println("Task " + String.valueOf(taskId) + " is coming!");
        notifyObservers();
    }
}
