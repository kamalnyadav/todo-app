package com.deloitte.todoapp.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.deloitte.todoapp.models.TodoTask;
import com.deloitte.todoapp.repositories.TodoTaskRepository;

@RunWith(SpringRunner.class)
public class TodoAppServiceTest {

	@TestConfiguration
	static class EmployeeServiceImplTestContextConfiguration {

		@Bean
		public TodoTaskService employeeService() {
			return new TodoTaskServiceImpl();
		}
	}

	@Autowired
	private TodoTaskService todoTaskService;

	@MockBean
	private TodoTaskRepository todoTaskRepository;
	
	private TodoTask task1;

	@Before
    public void setUp() {
        task1 = new TodoTask(1L,"task1", false, "2023/01/18 01:12:23", "2023/01/18 01:12:37", "test");
        Optional<TodoTask> optionalTask = Optional.of(task1);
        List<TodoTask> listTodoTask = new ArrayList<>();
        listTodoTask.add(task1);

        Mockito.when(todoTaskRepository.findByUserId("test")).thenReturn(listTodoTask);
        Mockito.when(todoTaskRepository.findById(1L)).thenReturn(optionalTask);
        Mockito.when(todoTaskRepository.save(task1)).thenReturn(task1);
    }

	@Test
    public void whenValidUserTaskAreFound() {
    	TodoTask expectedData = new TodoTask(1L,"task1", false, "2023/01/18 01:12:23", "2023/01/18 01:12:37", "test");
        List<TodoTask> listTask = todoTaskService.getAll("test");
        TodoTask actualData = listTask.get(0);
         assertEquals(expectedData.getId(), actualData.getId());
         assertEquals(expectedData.getDescription(), actualData.getDescription());
         assertEquals(expectedData.getUserId(), actualData.getUserId());
         assertEquals(expectedData.getIsComplete(), actualData.getIsComplete());
         assertEquals(expectedData.getCreatedTime(), actualData.getCreatedTime());
     }
	
	@Test
    public void whenValidUserTaskAreNotFound() {
        List<TodoTask> listTask = todoTaskService.getAll("user");
        assertTrue(listTask.isEmpty());
     }
	
	@Test
	public void whenValidUserIdTaskAreFound() {
		TodoTask expectedData = new TodoTask(1L,"task1", false, "2023/01/18 01:12:23", "2023/01/18 01:12:37", "test");
		Optional<TodoTask> tasks = todoTaskService.getById(1L);
		assertTrue(tasks.isPresent());
		TodoTask actualData = tasks.get();
        assertEquals(expectedData.getId(), actualData.getId());
        assertEquals(expectedData.getDescription(), actualData.getDescription());
        assertEquals(expectedData.getUserId(), actualData.getUserId());
        assertEquals(expectedData.getIsComplete(), actualData.getIsComplete());
        assertEquals(expectedData.getCreatedTime(), actualData.getCreatedTime());
	}
	
	@Test
	public void whenValidUserIdTaskAreNotFound() {
		Optional<TodoTask> tasks = todoTaskService.getById(2L);
		assertTrue(tasks.isEmpty());
	}
	
	@Test
	public void savingUserTasks() {
		TodoTask expectedData = new TodoTask(1L,"task1", false, "2023/01/18 01:12:23", "2023/01/18 01:12:37", "test");
		TodoTask actualData = todoTaskService.save(task1);
        assertEquals(expectedData.getId(), actualData.getId());
        assertEquals(expectedData.getDescription(), actualData.getDescription());
        assertEquals(expectedData.getUserId(), actualData.getUserId());
        assertEquals(expectedData.getIsComplete(), actualData.getIsComplete());
        assertEquals(expectedData.getCreatedTime(), actualData.getCreatedTime());
	}
}
