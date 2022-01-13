package com.springboot.CRUD.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.springboot.CRUD.entity.EmployeeEntity;

public interface EmployeeService {
	List<EmployeeEntity> getAllEmployees();
	
	void saveEmployee(EmployeeEntity employee);
	
	EmployeeEntity getEMployeeById(long id);
	
	void deleteEmployeeById(long id);
	
	Page<EmployeeEntity> findPaginated(int pageNo, int pageSize);
}
