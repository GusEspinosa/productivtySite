package dev.rehm.services;

import dev.rehm.data.TaskDao;
import dev.rehm.data.TaskDaoImpl;
import dev.rehm.models.Task;

import java.util.ArrayList;

public class TaskService {

    // using the TaskDao instead of TaskDaoImpl is an example of the Dependency Inversion principle
        // where we want our class to depend on abstract entities rather than concrete implementations
    private TaskDao taskData;

    public TaskService(){
        super();
        this.taskData = new TaskDaoImpl();
    }

    public TaskService(TaskDao taskDao){
        super();
        this.taskData = taskDao;
    }

    // method overloading here - we have two addNewTask methods, each with different parameters
    public void addNewTask(String name){
        Task newTask = new Task(name);
        taskData.addPendingTask(newTask);
    }

    public void addNewTask(Task newTask){
        taskData.addPendingTask(newTask);
    }

    public ArrayList<Task> getPending() {
        return taskData.getPendingTasks();
    }

    public void completeTask(Task task){
        taskData.completeTaskById(task.getId());
    }

    public void updateTask(int id){
        // id = param
        // isComplete = true
    }

    public void updateTask(int id, boolean isCompleted){

    }



}
