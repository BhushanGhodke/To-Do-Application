package com.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Task;
import java.util.List;


public interface TaskRepository extends JpaRepository<Task, Integer>{
	  
	public List<Task> findByUserId(Integer userId);
}
