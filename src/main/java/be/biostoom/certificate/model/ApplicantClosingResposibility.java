package be.biostoom.certificate.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ApplicantClosingResposibility implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 271121547184933684L;

	@Id
	@Column(name="applicant_closing_responsibility_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private boolean isWorkZoneChecked;
	
	private boolean isLottoRemoved;
	
	private String comment;
	
	private String signature;

}
