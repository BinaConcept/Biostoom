package be.biostoom.certificate.model;

import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class EnergyRisk implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4772926797831589945L;
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
	private boolean powerSwitchOn = false;

}
