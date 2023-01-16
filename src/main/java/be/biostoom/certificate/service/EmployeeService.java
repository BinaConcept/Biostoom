package be.biostoom.certificate.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import be.biostoom.certificate.model.Company;
import be.biostoom.certificate.model.Permit;
import be.biostoom.certificate.specification.PermitSpecification;
import be.biostoom.certificate.util.PageRequestExtractor;
import be.biostoom.certificate.util.PaginatedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import be.biostoom.certificate.model.Employee;
import be.biostoom.certificate.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository repository;

	@Autowired
	CompanyService companyService;

	public List<Employee> getAllEmployees() {
		List<Employee> employees = repository.findAll();

		return employees;
	}

	public Employee save(Employee employee) {
		Company company = companyService.getCompany(employee.getCompany_id());
		employee.setCompany_id(company.getId());
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
		return repository.findById(id).get();
	}

	public String delete(long id) {
		repository.deleteById(id);
		return "deleted";
	}

	public List<Employee> getEmployeesByCompanyId(Long id) {
		return repository.finAllByCompanyId(id);
	}
}
