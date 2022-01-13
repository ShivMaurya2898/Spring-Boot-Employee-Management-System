package com.springboot.CRUD.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.springboot.CRUD.entity.EmployeeEntity;
import com.springboot.CRUD.service.EmployeeService;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	// Display the list of employees
	@GetMapping("/")
	public String viewHomePage(Model model) {
		/*
		 * model.addAttribute("listEmployees", employeeService.getAllEmployees());
		 * return "index";
		 */
		return findPaginated(1, model);
	}

	@GetMapping("/shownewEmployeeForm")
	public String shownewEmployeeForm(Model model) {
		// Create model attribute to bind form data
		EmployeeEntity employee = new EmployeeEntity();
		model.addAttribute("employee", employee);
		return "new_employee";
	}

	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute("employee") EmployeeEntity employee) {
		// Save data to the database
		employeeService.saveEmployee(employee);
		return "redirect:/";
	}

	@GetMapping("/showFormForUpdate/{id}")
	public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
		// get employee form the service
		EmployeeEntity employee = employeeService.getEMployeeById(id);

		// set employee as a model attribute to Pre-populate the form
		model.addAttribute("employee", employee);
		return "update_employee";
	}

	@GetMapping("/deleteEmployee/{id}")
	public String deleteEmployee(@PathVariable(value = "id") long id) {

		// Call delete employee method
		this.employeeService.deleteEmployeeById(id);
		return "redirect:/";
	}

	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
		int pageSize = 5;

		Page<EmployeeEntity> page = employeeService.findPaginated(pageNo, pageSize);
		List<EmployeeEntity> listEmployees = page.getContent();
		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("listEmployees", listEmployees);
		return "index";
	}
}
