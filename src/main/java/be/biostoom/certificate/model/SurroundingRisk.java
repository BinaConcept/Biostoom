package be.biostoom.certificate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
public class SurroundingRisk implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6977621429262583821L;
	@Id
	@Column(name="surrounding_risk_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private boolean ConfinedSpace = false;
	private boolean AccessDifficult = false;
	private boolean Traffic= false;
	private boolean Noisy = false;
	private boolean hoistingActivities = false ;
	private boolean dustDirtLowVisibility = false;
	private boolean insufficientVentilation = false;
	
}
