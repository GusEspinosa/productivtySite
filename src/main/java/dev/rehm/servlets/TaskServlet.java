package dev.rehm.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.rehm.models.Task;
import dev.rehm.services.TaskService;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TaskServlet extends HttpServlet {

    private TaskService taskService = new TaskService();
    private ObjectMapper objectMapper = new ObjectMapper();
    private final Logger logger = Logger.getLogger(TaskServlet.class);

    /*
        this method will respond to GET requests sent to /tasks
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // process GET request and prepare response
//        System.out.println("GET request sent to /tasks");
        logger.info("GET request sent to /tasks");

        // obtain all of our task records
        ArrayList<Task> pendingTasks = taskService.getPending();

        // convert task records from java objects to JSON
        String pendingTasksJson = objectMapper.writeValueAsString(pendingTasks);
//        System.out.println(pendingTasksJson);
        logger.info(pendingTasks.size()+ " tasks obtained from the database ");

//        for(Task t: pendingTasks){
//            // build a string that is a JSON representation of all of my tasks
//        }
        // {"id": 3, "taskName":"take out trash", "isCompleted": "false", "description":"trash pickup at 8:00",
        // "09-16-2021"}

        // send back task data (JSON) via the response body
        try(PrintWriter pw = response.getWriter();){
            pw.write(pendingTasksJson);
        }
    }

    /*
        this method will respond to POST requests sent to /tasks
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // obtain the JSON task from the request body
        try(BufferedReader reader = request.getReader();
            PrintWriter pw = response.getWriter();) {
                // this assumes the data is only on one line
                String taskJson = reader.readLine();

                //System.out.println(taskJson);

                // convert JSON to task object
                Task task = objectMapper.readValue(taskJson, Task.class);

                if(task==null || task.getTaskName()==null ||  task.getTaskName().isEmpty()){
                    response.setStatus(400);
                } else {
                    // use service to add new actor
                    taskService.addNewTask(task);
                    response.setStatus(201);
                }
        }
    }

}
