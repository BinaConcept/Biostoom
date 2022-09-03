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
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
public class RiskAssesment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1971115781088738666L;

	@Id
	@Column(name = "risk_assesment_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "surrounding_risk_id", referencedColumnName = "surrounding_risk_id")
	private SurroundingRisk surroundingRisk;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "height_risk_id", referencedColumnName = "height_risk_id")
	private HeightRisk heightRisk;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "energy_risk_id", referencedColumnName = "energy_risk_id")
	private EnergyRisk energyRisk;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "equipments_id", referencedColumnName = "equipments_id")
	private Equipments equipments;
	
	
}
