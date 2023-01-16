package be.biostoom.certificate.util;

import be.biostoom.certificate.model.Activity;
import be.biostoom.certificate.model.Company;
import be.biostoom.certificate.model.Employee;
import be.biostoom.certificate.model.Permit;
import be.biostoom.certificate.service.CompanyService;
import be.biostoom.certificate.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private CompanyService companyService;

    @Autowired
    private EmployeeService employeeService;


    public void insertCompany() {
        Employee arsal = new Employee();
        arsal.setId(1);
        arsal.setFirstName("Arsal");
        arsal.setLastName("Bulent");
        arsal.setGsm("0494527365");
        arsal.setEmail("arsal@biostoom.be");
        arsal.setHasReadSiteIntroduction(true);

        Employee atif = new Employee();
        atif.setId(1);
        atif.setFirstName("Atif");
        atif.setLastName("Khan");
        atif.setGsm("0494527368");
        atif.setEmail("external@external.be");
        atif.setHasReadSiteIntroduction(true);

        Company biostoom = new Company(1,"Biostoom","somestreetname","24",
                "","2400","Beringen","Belgie","013 54 65 57", "095 86 88 59",
                "023 98 98 98", "email@biostoom.be", false, "biostoom", true,new HashSet<>(),new HashSet<>());

        Company external = new Company(2,"External","somestreetname","24",
                "","2400","Mol","Belgie","013 54 65 57", "095 86 88 59",
                "023 98 98 98", "email@external.be", false, "external", false,new HashSet<>(),new HashSet<>());
        Long biostoomId =   companyService.register(biostoom);

        Long externalId =   companyService.register(external);

        atif.setCompany_id(externalId);
        arsal.setCompany_id(biostoomId);

        employeeService.save(arsal);
        employeeService.save(atif);

    }

    @Override
    public void run(String... args) throws Exception {
        insertCompany();
    }

}
