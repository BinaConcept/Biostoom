package be.biostoom.certificate.model;

import java.io.Serializable;
import java.util.HashSet;
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


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@NoArgsConstructor
@AllArgsConstructor
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
	
	private String password;
	
	private Boolean isBioostoom;

	@JsonIgnore
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, orphanRemoval=true, mappedBy="company")
    private Set<Employee> employees = new HashSet<>();

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company", orphanRemoval = true)
	@Fetch(value = FetchMode.SUBSELECT)
	Set<Permit> permits = new HashSet<>();

	@PrePersist
	private void setOnSave() {
		this.employees.forEach(emp->{
			emp.setCompany(this);
		});

		this.permits.forEach(permit -> {
			permit.setCompany(this);
		});
	}

}
