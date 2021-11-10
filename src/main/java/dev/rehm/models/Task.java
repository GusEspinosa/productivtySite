package dev.rehm.models;

import java.time.LocalDate;
import java.util.Objects;

// classes should be Pascal Case (e.g. TaskScheduler)
public class Task {

    // fields/methods should be Camel Case
    private int id;
    private String taskName;
    private boolean isCompleted; // false
    private String description;
    private String dueDate;

    public Task(){
        super();
    }

    public Task(String taskName){
        super();
        int x = 25; // x is in local scope
        this.taskName = taskName;
    }

    public Task(String taskName, boolean isCompleted){
        super();
        this.taskName = taskName;
        this.isCompleted = isCompleted;
    }

    public String getTaskName(){
        return this.taskName;
    }

    public void setTaskName(String taskName){
        //check for null values or empty strings
        if(taskName!=null && !"".equals(taskName)){
            this.taskName = taskName;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCompleted(){
        return this.isCompleted;
    }

    public void setCompleted(boolean isCompleted){
        this.isCompleted = isCompleted;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && isCompleted == task.isCompleted && Objects.equals(taskName, task.taskName) && Objects.equals(description, task.description) && Objects.equals(dueDate, task.dueDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, taskName, isCompleted, description, dueDate);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                ", isCompleted=" + isCompleted +
                ", description='" + description + '\'' +
                ", dueDate=" + dueDate +
                '}';
    }
}
