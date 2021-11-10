package dev.rehm.data;

import dev.rehm.models.Task;
import dev.rehm.services.ConnectionService;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskDaoImpl implements TaskDao {

    private ConnectionService connectionService = new ConnectionService();
    String myStr;

    public ArrayList<Task> getAllTasks(){
        return getSubsetOfTasks("all");
    }

    private ArrayList<Task> getSubsetOfTasks(String criteria){
        String sql;
        switch(criteria){
            case "all":
                sql = "select * from task order by task_id";
                break;
            case "pending":
                sql = "select * from task where is_completed = false order by task_id";
                break;
            case "completed":
                sql = "select * from task where is_completed = true order by task_id";
                break;
            default:
                throw new RuntimeException("not supported criteria");
        }
        return getTasksBySql(sql);

    }

    private ArrayList<Task> getTasksBySql(String sql){
        ArrayList<Task> allTasks = new ArrayList<>();
        try (Connection c = connectionService.establishConnection();
             Statement stmt = c.createStatement();
             ResultSet rs = stmt.executeQuery(sql);){

            while(rs.next()){
                // for each task in our result set, we can create a Task object
                int id = rs.getInt("task_id");
                String name = rs.getString("task_name");
                boolean isCompleted = rs.getBoolean("is_completed");
                String description = rs.getString("description");
                String dueDate = rs.getString("due_date");
                Task t = new Task();
                t.setId(id);
                t.setTaskName(name);
                t.setCompleted(isCompleted);
                t.setDescription(description);
                t.setDueDate(dueDate);
                allTasks.add(t);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allTasks;
    }

    public ArrayList<Task> getPendingTasks(){
        return getSubsetOfTasks("pending");
    }

    public int addPendingTask(Task newTask){
        String sql = "insert into task (task_name, is_completed,description,due_date) values ( ? , false,?,?)";

//        String myStr; // in local and block scope, variables are not initialized with default values
//        myStr = "hello world";
//        System.out.println(myStr);

        try (Connection c = connectionService.establishConnection();
             PreparedStatement pstmt = c.prepareStatement(sql);){
            pstmt.setString(1, newTask.getTaskName());
            pstmt.setString(2, newTask.getDescription());
            pstmt.setString(3, newTask.getDueDate());
            return pstmt.executeUpdate(); // returns a number indicating the rows that were affected

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public int completeTaskById(int id){
        String sql = "update task set is_completed = true where task_id = ?";
        try (Connection c = connectionService.establishConnection();
             PreparedStatement pstmt = c.prepareStatement(sql);){

            pstmt.setInt(1, id);

            return pstmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public Task getTaskById(int id){
        String sql = "select * from task where task_id = ?";
        try(Connection c = connectionService.establishConnection();
            PreparedStatement pstmt = c.prepareStatement(sql)){
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                Task t = new Task();
                t.setId(id);
                String name = rs.getString("task_name");
                t.setTaskName(name);
                String description = rs.getString("description");
                t.setDescription(description);
                boolean isCompleted = rs.getBoolean("is_completed");
                t.setCompleted(isCompleted);
                String dueDate = rs.getString("due_date");
                t.setDueDate(dueDate);
                return t;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


}
