package be.biostoom.certificate.controller;
import be.biostoom.certificate.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.biostoom.certificate.model.AuthenticationRequest;
import be.biostoom.certificate.model.Company;
//import be.biostoom.certificate.service.AuthenticationService;


@RestController
@RequestMapping("${api.prefix}/authentication")
public class AuthenticationController {

//	@Autowired
//	AuthenticationService service;

    @Autowired
    CompanyService companyService;
	
	@GetMapping
    public String  getMessage(){
        return "This is message";
    }
	
	@PostMapping("/register")
    public Long register(@RequestBody Company company){
        return companyService.register(company);
    }
	
//	@PostMapping
//	public ResponseEntity<?> createToken(@RequestBody AuthenticationRequest req) throws Exception{
//		return service.authenthicate(req);
//	}
}
