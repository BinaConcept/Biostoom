package be.biostoom.certificate.model.dto;

import be.biostoom.certificate.model.Company;
import be.biostoom.certificate.model.Employee;
import be.biostoom.certificate.model.Permit;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class EmployeeOverviewDTO implements Serializable {

    private long id;

    private String firstName;

    private String lastName;

    private String email;

    private String gsm;

    private long companyId;
    private String company;

    private boolean isActive;

    private boolean hasReadSiteIntroduction;

    public EmployeeOverviewDTO(Employee employee) {
        Company company = employee.getCompany();
        this.id = employee.getId();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.email = employee.getEmail();
        this.gsm = employee.getGsm();
        this.firstName = employee.getFirstName();
        this.companyId = company.getId();
        this.company = company.getName();
        this.isActive = employee.isActive();
        this.hasReadSiteIntroduction = employee.isHasReadSiteIntroduction();
    }

}
