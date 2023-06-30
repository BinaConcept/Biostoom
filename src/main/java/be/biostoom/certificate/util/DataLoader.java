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
        arsal.setFirstName("Dirk");
        arsal.setLastName("Maes");
        arsal.setGsm("0494527365");
        arsal.setEmail("Dirk@biostoom.be");
        arsal.setHasReadSiteIntroduction(true);

        Employee atif = new Employee();
        atif.setId(1);
        atif.setFirstName("Freddy");
        atif.setLastName("Houben");
        atif.setGsm("0494527368");
        atif.setEmail("external@external.be");
        atif.setHasReadSiteIntroduction(true);

        Company biostoom = new Company(1,"Bionerga","somestreetname","24",
                "","2400","Beringen","Belgie","013 54 65 57", "095 86 88 59",
                "023 98 98 98", "email@biostoom.be", false, "bionerga", true,new HashSet<>(),new HashSet<>());

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
                new Location(null, "Boiler", new HashSet<>()),
                new Location(null, "Buiten installatie", new HashSet<>()),
                new Location(null, "Buiten terrein", new HashSet<>()),
                new Location(null, "RGR", new HashSet<>()),
                new Location(null, "Sortering", new HashSet<>()),
                new Location(null, "Tippinghall", new HashSet<>()),
                new Location(null, "Turbine gebouw", new HashSet<>()),
                new Location(null, "Voorbehandeling", new HashSet<>())

        ));
        locationRepository.saveAll(locations);
    }

    @Override
    public void run(String... args) throws Exception {
        insertCompany();
        insertLocations();
    }

}
