package be.biostoom.certificate.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class PersonalPrecautionaryMeasures  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4574163406137816232L;
	@Id
	@Column(name = "personal_precautionary_measures_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private boolean safetyShoes = false;
	private boolean helmet = false;
	private boolean flouJacket = false;
	private boolean eyeProtection = false;
	private boolean faceProtection = false;
	private boolean gloves = false;
	private boolean earProtection = false;
	private boolean openingPitsFloors = false;
	private boolean respratoryProtection = false;
	private boolean protectionClothing = false;
}
