package be.biostoom.certificate.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Protection implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3286828350378969783L;
	@Id
	@Column(name="protection_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private boolean loto = false;
	private boolean demarcateArea = false;
	private boolean confinedSpace= false;
	private boolean secondPerson = false;
	private boolean manholeGuard = false ;
	private boolean earthing = false;
	private boolean ventilation = false;
	private boolean extraLighting = false;
	private boolean removeContainedEnergy = false;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "loto_fich_id", referencedColumnName = "loto_fich_id")
	private LotoFich lotoFich;
}
