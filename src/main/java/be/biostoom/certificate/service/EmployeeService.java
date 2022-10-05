package be.biostoom.certificate.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.biostoom.certificate.model.Employee;
import be.biostoom.certificate.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository repository;

	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	public Employee save(Employee employee) {
		return repository.save(employee);
	}

	public Employee update(long id, Employee employee) {
		return repository.save(employee);
	}

	public boolean isExist(String email) {
		Employee employee = repository.findByEmailIgnoreCase(email);
		return employee != null;
	}

	public Employee getEmployee(Long id) {
		Optional<Employee> retrieved = repository.findById(id);
		Employee retrieved1 = retrieved.get();
		return repository.findById(id).get();
	}

	public String delete(long id) {
		repository.deleteById(id);
		return "deleted";
	}

}
