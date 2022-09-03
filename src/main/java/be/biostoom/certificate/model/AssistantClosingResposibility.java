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
public class AssistantClosingResposibility  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -877033097108023866L;

	@Id
	@Column(name="assistant_closing_responsibility_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private boolean isCompleted;
	
	private boolean safeguardsReinstalled;
	
	private boolean equipmentClosed;
	
	private boolean workZoneCleaned;

	private boolean everyThingBackInPlace;

	private boolean isPermitExpired;
	
	private String comment;
	
	private String signature;
}
