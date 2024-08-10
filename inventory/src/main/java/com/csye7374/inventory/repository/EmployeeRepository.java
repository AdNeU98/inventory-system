package com.csye7374.inventory.repository;

import com.csye7374.inventory.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	Optional<Employee> findById(int id);

	Optional<Employee> findByUsernameAndPassword(String username, String password);
	
	Optional<Employee> findByUsername(String username);
}
