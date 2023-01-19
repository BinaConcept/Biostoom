package be.biostoom.certificate.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import be.biostoom.certificate.model.Company;
import be.biostoom.certificate.model.Permit;
import be.biostoom.certificate.model.dto.EmployeeOverviewDTO;
import be.biostoom.certificate.model.dto.ListItem;
import be.biostoom.certificate.model.dto.PermitOverviewDTO;
import be.biostoom.certificate.specification.EmployeeSpecification;
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


	public Employee save(Employee employee) {
		if(isExist(employee.getEmail()))
			throw new IllegalArgumentException("Deze gebruiker is reeds gekend");
		Company company = companyService.getCompany(employee.getCompanyId());
		employee.setCompany(company);
		Employee savedEmployee = repository.save(employee);
		savedEmployee.setCompanyId(company.getId());
		return savedEmployee;
	}

	public Employee update(long id, Employee employee) {
		Employee savedEmployee = repository.save(employee);
		savedEmployee.setCompanyId(savedEmployee.getCompany().getId());
		return savedEmployee;
	}

	public boolean isExist(String email) {
		Employee employee = repository.findByEmailIgnoreCase(email);
		return employee != null;
	}

	public Employee getEmployee(Long id) {
		Employee savedEmployee = repository.findById(id).get();
		savedEmployee.setCompanyId(savedEmployee.getCompany().getId());
		return savedEmployee;
	}

	public String delete(long id) {
		repository.deleteById(id);
		return "deleted";
	}

	public List<ListItem> getEmployeesByCompanyId(Long id) {
		List<ListItem> employees = repository.finAllByCompanyId(id);
		return employees;
	}

	private void prepareCompanyIds(List<Employee> employees){
		employees.forEach(employee -> {
			employee.setCompanyId(employee.getCompany().getId());
		});
	}

	public PaginatedResponse<EmployeeOverviewDTO> getAllEmployees(Map<String, Object> parameters) {
		Pageable pageRequest = PageRequestExtractor.extract(parameters);
		PaginatedResponse<Employee> response = repository.findAll(Employee.class, EmployeeSpecification.forParams(parameters).withAccessFilter(), pageRequest);

		return response.mapTo(EmployeeOverviewDTO::new);
	}
}
