package com.springboot.CRUD.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.CRUD.entity.EmployeeEntity;
import com.springboot.CRUD.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;
		
	@Override
	public List<EmployeeEntity> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Override
	public void saveEmployee(EmployeeEntity employee) {
		this.employeeRepository.save(employee);
	}

	@Override
	public EmployeeEntity getEMployeeById(long id) {
		Optional<EmployeeEntity> optional = employeeRepository.findById(id);
		EmployeeEntity employee = null;
		if(optional.isPresent()) {
			employee = optional.get();
		}
		else {
			throw new RuntimeException(" Employee Not Found for id :: " + id);
		}
		return employee;
	}

	@Override
	public void deleteEmployeeById(long id) {
		this.employeeRepository.deleteById(id);
	}

	@Override
	public Page<EmployeeEntity> findPaginated(int pageNo, int pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize); 
		return this.employeeRepository.findAll(pageable);
	}

}
