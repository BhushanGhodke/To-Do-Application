package com.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.binding.TaskBinding;
import com.app.entity.Task;
import com.app.entity.User;
import com.app.exception.TaskNotFoundException;
import com.app.repo.TaskRepository;
import com.app.repo.UserRepository;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Task createNewTask(TaskBinding taskBinding) {

		Task task = new Task();
		task.setStatus("PENDING");
		task.setTask(taskBinding.getTask());
        task.setUserId(taskBinding.getUserId());

		Task savedTask = taskRepository.save(task);

		return savedTask;
	}

	@Override
	public Task updateExistingTask(TaskBinding taskBinding) {

		Optional<Task> taskfromDB = taskRepository.findById(taskBinding.getTaskId());

		if (taskfromDB.isPresent()) {

			Task task = taskfromDB.get();

			task.setTask(taskBinding.getTask());
			task.setStatus(taskBinding.getStatus());
			return taskRepository.save(task);
		}

		throw new TaskNotFoundException("Task Not Found");

	}
	
	
	@Override
	public Task updateTaskStatus(TaskBinding taskBinding) {
		Optional<Task> taskfromDB = taskRepository.findById(taskBinding.getTaskId());

		if (taskfromDB.isPresent()) {

			Task task = taskfromDB.get();
			
			task.setStatus(taskBinding.getStatus());
			return taskRepository.save(task);
		}

		throw new TaskNotFoundException("Task Not Found");
	
	}

	@Override
	public boolean deleteTask(Integer taskId) {
		if (taskRepository.existsById(taskId)) {
			taskRepository.deleteById(taskId);
			return true;
		}
		return false;

	}

	@Override
	public List<Task> getAllTaskByUserId(Integer userId) {

	   return taskRepository.findByUserId(userId);
	}

}
