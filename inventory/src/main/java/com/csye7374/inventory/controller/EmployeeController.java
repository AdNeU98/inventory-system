package com.csye7374.inventory.controller;

import com.csye7374.inventory.dao.EmployeeStrategy;
import com.csye7374.inventory.dao.InventoryStrategy;
import com.csye7374.inventory.model.Employee;
import com.csye7374.inventory.repository.EmployeeRepository;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

/**
 * @apiNote - REST Controller for Employees
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepo;

	@GetMapping("/getAll")
	public List<Employee> getAll() {
		return employeeRepo.findAll();
	}

	@GetMapping("/getEmployee/{id}")
	public Employee getEmployee(@PathVariable int id) {
		return employeeRepo.findById(id).get();
	}

	@PutMapping("/update/{id}")
	public void update(@RequestBody Employee employee, @PathVariable int id) {
		InventoryStrategy strategy = new InventoryStrategy(new EmployeeStrategy(employeeRepo, employee));
		strategy.executeUpdate(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deletebyID(@PathVariable int id) {
		InventoryStrategy strategy = new InventoryStrategy(new EmployeeStrategy(employeeRepo, id));
		strategy.executeDelete();
	}

	@PostMapping("/save")
	public void save(@RequestBody Employee employee) {
		InventoryStrategy strategy = new InventoryStrategy(new EmployeeStrategy(employeeRepo, employee));
		strategy.executeAdd();
	}

	@PostMapping("/login")
	public Employee login(@RequestBody JSONObject jsoncredentials) {
		String username = (String) jsoncredentials.get("username");
		String password = (String) jsoncredentials.get("password");
		Optional<Employee> employee = employeeRepo.findByUsernameAndPassword(username, password);
		if(employee.isEmpty())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Username/Password is incorrect");
		return employee.get();
	}
	
}
