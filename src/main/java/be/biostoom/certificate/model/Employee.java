package be.biostoom.certificate.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@Entity
@AllArgsConstructor
@Getter
@Setter
public class Employee implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -202337302908445883L;

	@Id
	@Column(name="employee_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@NotBlank(message = "Voornaam is een verplicht veld.")
	private String firstName;

	@NotBlank(message = "Achternaam is een verplicht veld.")
	private String lastName;

	@Email(message = "Ongeldig e-mail.")
	private String email;

	@Pattern(regexp = "^\\d{10}$", message = "Ongeldig gsmnummer.")
	private String gsm;
	
	@Transient
	private long company_id;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="company_id")
	@JsonIgnore
	private Company company;

	private boolean isActive;
	
	private boolean hasReadSiteIntroduction;
	
}
