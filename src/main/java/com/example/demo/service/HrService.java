package com.example.demo.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.CreatePost;
import com.example.demo.entity.Employee;
import com.example.demo.reposetory.CreatepostRepo;
import com.example.demo.reposetory.EmployeeRepo;
@Service
public class HrService {
	
	@Autowired
	private EmployeeRepo employeeRepo;
	
	@Autowired
	private CreatepostRepo createpostRepo;

	public Employee addEmployee(Employee employee) {
		Employee save = employeeRepo.save(employee);
		return save;
	}
	
	public List<Employee>getAllEmployee(){
		List<Employee>findAll =employeeRepo.findAll();
		return findAll;
	}
	
	public CreatePost addPost(CreatePost createPost) {
		CreatePost save=createpostRepo.save(createPost);
		return save;
	}
}
