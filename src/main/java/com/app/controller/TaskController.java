package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.binding.TaskBinding;
import com.app.entity.Task;
import com.app.service.TaskService;

@RestController
@RequestMapping("/task")
@CrossOrigin
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@PostMapping("/createTask")
	public ResponseEntity<Task> createNewTask(@RequestBody TaskBinding taskBinding){

		Task task = taskService.createNewTask(taskBinding);
	
		return new ResponseEntity<>(task, HttpStatus.CREATED);
	}
	
	@GetMapping("/getTask/{userId}")
	public ResponseEntity<List<Task>> gettaskByUserId(@PathVariable Integer userId){
		List<Task> taskList = taskService.getAllTaskByUserId(userId);
	
		return new ResponseEntity<List<Task>>(taskList, HttpStatus.OK);
	}
	

	@PutMapping("/updateTask")
	public ResponseEntity<Task> updateTask(@RequestBody TaskBinding taskBinding){

		Task task = taskService.updateExistingTask(taskBinding);
	
		return new ResponseEntity<>(task, HttpStatus.OK);
	}
	
	@PatchMapping("/updateTaskStatus")
	public ResponseEntity<Task> updateTaskStatus(@RequestBody TaskBinding taskBinding){

		Task task = taskService.updateTaskStatus(taskBinding);
	
		return new ResponseEntity<>(task, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteTask/{taskId}")
	public ResponseEntity<Boolean> deleteTask(@PathVariable Integer taskId){

		boolean status = taskService.deleteTask(taskId);
	
		return new ResponseEntity<>(status, HttpStatus.CREATED);
	}
	
}
