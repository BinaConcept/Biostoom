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
public class CapabillitySkills implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7093942156934687702L;
	@Id
	@Column(name="capabillity_skills_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private boolean validLicensesCertificates = false;
	private boolean validLicensesCertificatesOfEquipment = false;
}
