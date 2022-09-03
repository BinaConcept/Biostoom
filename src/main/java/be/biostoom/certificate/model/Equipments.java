package be.biostoom.certificate.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Equipments implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6152274475035046057L;

	@Id
	@Column(name="equipments_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private boolean handTools = false;
	private boolean measuringTools = false;
	private boolean ElecticalTools= false;
	private boolean forklift = false;
	private boolean cranExcavator = false ;
	private boolean truck = false;

}
