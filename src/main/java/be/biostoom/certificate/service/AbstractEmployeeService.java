package be.biostoom.certificate.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.biostoom.certificate.model.Employee;

@Service
public class AbstractEmployeeService {
	
	@Autowired
	EmployeeService employeeService;
	
	public Employee getEmployee(long id) {
		return	employeeService.getEmployee(id);
	}
	
//	public final String JRC_ADMIN = "BIOSTOOM";
//	public final String JRC_VALIDATOR = "EXTERNAL";
//		
//	public boolean isExternalEmployee() {
//		return hasRole(UserServiceImpl.OBSERVER);
//	}
//	public boolean isBioostoomEmployee() {
//		return hasRole(UserServiceImpl.JRC_ADMIN);
//	}
//	public boolean isValidator() {
//		return hasRole(UserServiceImpl.JRC_VALIDATOR);
//	}
//	public boolean isEditor() {
//		return hasRole(UserServiceImpl.JRC_EDITOR);
//	}
//
//	private boolean hasRole(String role){
//		return Arrays.stream(getPrincipal().getRoles()).anyMatch(role::equals);	
//	}

}