package be.biostoom.certificate.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Getter
@Setter
public class Company implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1812093512452362528L;

	@Id
	@Column(name = "company_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String name;

	private String address;

	private String number;

	private String bus;

	private String zip;

	private String city;

	private String country;

	private String phone;

	private String gsm;

	private String fax;

	private String email;

	private boolean blocked;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,orphanRemoval=true,mappedBy="company")
    private Set<Employee> employees;
	
	@PrePersist
	private void setEmployeesCompany() {
		this.employees.forEach(emp->{
			emp.setCompany(this);
		});
	}

}
