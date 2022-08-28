package be.biostoom.certificate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class SurroundingRisk {
	@Id
	@Column(name="surrounding_risk_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private boolean isConfinedSpace = false;
	private boolean isAccessDifficult = false;
	private boolean isTraffic= false;
	private boolean isNoisy = false;
	private boolean ishoistingActivities = false ;
	private boolean hasDustDirtLowVisibility = false;
	private boolean hasInsufficientVentilation = false;
	
	@OneToOne(mappedBy = "surroundingRisk")
	private RiskAssesment riskAssesment;
}
