package dev.rehm.data;

import dev.rehm.models.Task;
import dev.rehm.services.ConnectionService;
import org.h2.tools.RunScript;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class TaskDaoImplTest {

    private TaskDao taskDao = new TaskDaoImpl();

    @BeforeAll
    public static void runSetUp(){
        try(Connection connection = new ConnectionService().establishConnection()){
            RunScript.execute(connection, new FileReader("sql-setup.sql"));
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void getTaskByValidId(){
        Task actual = taskDao.getTaskById(6);
        Task expected = new Task();
        expected.setId(6);
        expected.setTaskName("super cool new task");
        expected.setCompleted(true);
        //expected.setDueDate(dueDate);
        assertEquals(expected, actual);
    }

    @Test
    public void getTaskByInvalidId(){
        Task actual = taskDao.getTaskById(25);
        assertNull(actual);
    }

    @Test
    public void completeValidTask(){
        Task beforeUpdate = taskDao.getTaskById(2);
        int rowsAffected = taskDao.completeTaskById(2);
        Task afterUpdate = taskDao.getTaskById(2);

        assertAll(
                ()->assertFalse(beforeUpdate.isCompleted()),
                ()->assertEquals(1, rowsAffected),
                ()->assertTrue(afterUpdate.isCompleted())
        );
    }

    @Test
    public void completeInvalidTask(){
        int expected = 0;
        int actual = taskDao.completeTaskById(25);
        assertEquals(expected, actual);
    }

}
