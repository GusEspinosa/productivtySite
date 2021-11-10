package dev.rehm.services;

import dev.rehm.data.TaskDao;
import dev.rehm.models.Task;

import java.util.ArrayList;

public class TaskServiceTest {

//    private TaskService taskService = new TaskService(); // this tightly couples task service to task dao impl
    private TaskService taskService = new TaskService(new MockTaskDao());



}

class MockTaskDao implements TaskDao{

    @Override
    public ArrayList<Task> getAllTasks() {
        return null;
    }

    @Override
    public ArrayList<Task> getPendingTasks() {
        return null;
    }

    @Override
    public int addPendingTask(Task newTask) {
        return 0;
    }

    @Override
    public int completeTaskById(int id) {
        return 0;
    }

    @Override
    public Task getTaskById(int id) {
        return null;
    }
}
