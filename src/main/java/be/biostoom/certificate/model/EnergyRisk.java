package be.biostoom.certificate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class EnergyRisk {
	@Id
	@Column(name="energy_risk_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private boolean movingPartContact = false;
	private boolean electricityContact = false;
	private boolean insideElectricalCabinet = false;
	private boolean mediumHighVoltage = false;
	private boolean compressedAir = false ;
	private boolean heatSteam = false;
	private boolean chemical = false;
	private boolean gesses = false;
	private boolean containedEnergy = false;
	private boolean isPowerSwitchOn = false;

}
