package com.example.demo.reposetory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Employee;


public interface EmployeeRepo extends JpaRepository<Employee,Integer>{
	
	public Employee findByIdAndPassword(int empId,String password);
	
	public Employee findByIdAndPasswordAndRole(int empId,String password,String role);
	
	
	// ADD BELOW existing code
	long countByDepartmentContainingIgnoreCase(String department);

	long countByDepartment(String department);

	List<Employee> findTop5ByOrderByDateOfBirthAsc();

	
}
