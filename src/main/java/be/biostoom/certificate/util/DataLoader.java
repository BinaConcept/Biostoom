package be.biostoom.certificate.util;

import be.biostoom.certificate.model.*;
import be.biostoom.certificate.repository.LocationRepository;
import be.biostoom.certificate.service.CompanyService;
import be.biostoom.certificate.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    private CompanyService companyService;

    @Autowired
    private EmployeeService employeeService;


    @Autowired
    LocationRepository locationRepository;

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
        Long biostoomId = companyService.register(biostoom);

        Long externalId = companyService.register(external);

        atif.setCompanyId(externalId);
        arsal.setCompanyId(biostoomId);

        employeeService.save(arsal);
        employeeService.save(atif);

    }

    public void insertLocations(){
        List<Location> locations = new ArrayList<>(Arrays.asList(
                new Location((long) 1, "Boiler ", new HashSet<>()),
                new Location((long) 3, "Buiten Terrein ", new HashSet<>()),
                new Location((long)4, "Buiten Installatie", new HashSet<>()),
                new Location((long)5, "Voor Behandeling", new HashSet<>())
        ));
        locationRepository.saveAll(locations);
    }

    @Override
    public void run(String... args) throws Exception {
        insertCompany();
        insertLocations();
    }

}
