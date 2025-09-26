package com.app.service;

import java.util.List;

import com.app.binding.TaskBinding;
import com.app.entity.Task;

public interface TaskService {

	public Task createNewTask(TaskBinding task);

    public Task updateExistingTask(TaskBinding task);
    
    public Task updateTaskStatus(TaskBinding task);
    
    public boolean deleteTask(Integer taskId);
    
    public List<Task> getAllTaskByUserId(Integer userId);
  
}
