package be.biostoom.certificate.controller;

import java.util.List;

import be.biostoom.certificate.model.Employee;
import be.biostoom.certificate.model.dto.ListItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.biostoom.certificate.model.Company;
import be.biostoom.certificate.service.CompanyService;

import javax.validation.Valid;

@RestController
@RequestMapping("${api.prefix}/companies")
public class CompanyController {

	@Autowired
	CompanyService service;
	
	@GetMapping
	public List<Company> getAllCompanies(){
		return service.getAllCompanies();
	}

    @GetMapping("/list-items")
    public List<ListItem> getCompaniesListItems(){
        return service.getCompaniesListItems();
    }
	@PutMapping("/{id}")
    public Company update(@PathVariable long id, @RequestBody Company company){
        return service.update(id, company);
    }

    @PostMapping
    public Company save(@RequestBody @Valid Company company)
    {
        return service.save(company);
    }

    @GetMapping("/{id}")
    public Company getCompany(@PathVariable Long id){
        return service.getCompany(id);
    }
    
    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id){
    	return service.delete(id);
    }
}
