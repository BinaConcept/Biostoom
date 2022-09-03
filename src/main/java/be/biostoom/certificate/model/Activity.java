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
public class Activity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7813002416709639627L;
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
}
