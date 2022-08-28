package be.biostoom.certificate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "activity")
public class Activity {
	@Id
	@Column(name="activity_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private boolean assembleDessemble = false;
	private boolean openingInstallation = false;
	private boolean inspection = false;
	private boolean maintenance = false;
	private boolean welding = false ;
	private boolean cutting = false;
	private boolean BrazingSoldering = false;
	private boolean openFlame = false;
	private boolean insulationWork = false;
	private boolean hoisting = false;
	private boolean digging = false;
	private boolean painting = false;
	private boolean scafolding = false;
	private boolean highpressureWork = false;
	private String description = "This is a short description about the this activity.";
	
	@OneToOne(mappedBy = "activity")
	private Permit permit;
}
