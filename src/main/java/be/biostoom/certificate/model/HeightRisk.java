package be.biostoom.certificate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor
public class HeightRisk implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 73582750379328403L;
	@Id
	@Column(name="height_risk_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private boolean workingOnCrane = false;
	private boolean workingOnLadder = false;
	private boolean scafolding= false;
	private boolean mobilePlatformManual = false;
	private boolean mobilePlatformPowered = false ;
	private boolean ladderNearHandrail = false;
	private boolean workingOnRoof = false;
	private boolean openingPitsFloors = false;
	private boolean makingOfHolesOpenings = false;
	private boolean useOfFixedAnkerlineSafeLine = false;
	
}
