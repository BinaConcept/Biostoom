package be.biostoom.certificate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class HeightRisk {
	@Id
	@Column(name="height_risk_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private boolean isWorkingOnCrane = false;
	private boolean isWorkingOnLadder = false;
	private boolean hasScafolding= false;
	private boolean isMobilePlatformManual = false;
	private boolean isMobilePlatformPowered = false ;
	private boolean isLadderNearHandrail = false;
	private boolean isWorkingOnRoof = false;
	private boolean isOpeningPitsFloors = false;
	private boolean isMakingOfHolesOpenings = false;
	private boolean hasUseOfFixedAnkerlineSafeLine = false;
}
