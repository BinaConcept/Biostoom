package be.biostoom.certificate.controller;

import java.util.List;

import be.biostoom.certificate.model.Permit;
import be.biostoom.certificate.model.parameters.PermitQueryParameters;
import be.biostoom.certificate.util.PaginatedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.biostoom.certificate.model.Employee;
import be.biostoom.certificate.service.EmployeeService;

import javax.validation.Valid;

@RestController
@RequestMapping("${api.prefix}/employees")
public class EmployeeController {
	@Autowired
	EmployeeService service;

	@PostMapping
    public Employee save(@RequestBody @Valid Employee employee)
    {
        return service.save(employee);
    }
	
	@PutMapping("/{id}")
    public Employee update(@PathVariable long id, @RequestBody Employee employee){
        return service.update(id, employee);
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Long id){
        return service.getEmployee(id);
    }

    @GetMapping("/company/{id}")
    public List<Employee> getEmployeesByCompanyId(@PathVariable Long id){

        return service.getEmployeesByCompanyId(id);

    }
    @GetMapping("/is-exist/{email}")
    public Boolean getEmployee(@PathVariable String email){
        return service.isExist(email);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id){
    	return service.delete(id);
    }
}
