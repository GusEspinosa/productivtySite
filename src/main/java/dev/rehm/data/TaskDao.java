package dev.rehm.data;

import dev.rehm.models.Task;

import java.util.ArrayList;

public interface TaskDao {

    /*
        interface methods are implicitly abstract
     */
    public ArrayList<Task> getAllTasks();

    public ArrayList<Task> getPendingTasks();

    public int addPendingTask(Task newTask);

    public int completeTaskById(int id);

    public Task getTaskById(int id);
}
