package be.biostoom.certificate.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class PrecautionaryMeasures implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1632718049076802601L;

	@Id
	@Column(name="precautionary_measures_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "capabillity_skills_id", referencedColumnName = "capabillity_skills_id")
	private  CapabillitySkills capabillitySkills;

	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "protection_id", referencedColumnName = "protection_id")
	private  Protection protection;

	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "personal_precautionary_measures_id", referencedColumnName = "personal_precautionary_measures_id")
	private  PersonalPrecautionaryMeasures personalPrecautionaryMeasures;

	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fire_check_id", referencedColumnName = "fire_check_id")
	private  FireCheck fireCheck;
	
	private String descriptionString;
}
