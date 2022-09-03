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
@Entity
@NoArgsConstructor
public class LotoFich implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4570813471711405746L;

	@Id
	@Column(name="loto_fich_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private long number;
}
