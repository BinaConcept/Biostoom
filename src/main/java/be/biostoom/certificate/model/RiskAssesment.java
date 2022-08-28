package be.biostoom.certificate.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class RiskAssesment {

	@Id
	@Column(name = "risk_assesment_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "surrounding_risk_id", referencedColumnName = "surrounding_risk_id")
	private SurroundingRisk surroundingRisk;
}
