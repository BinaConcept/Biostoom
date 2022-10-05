package be.biostoom.certificate.model.dto;

import java.io.Serializable;

import javax.persistence.Id;

import be.biostoom.certificate.model.AssistantClosingResposibility;
import lombok.Data;

@Data

public class AssistantClosingDTO  implements Serializable{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 3421498677101169047L;

	private long permitId;
	
	private long assistantId;
	
	private boolean isCompleted;
	
	private boolean safeguardsReinstalled;
	
	private boolean equipmentClosed;
	
	private boolean workZoneCleaned;

	private boolean everyThingBackInPlace;

	private boolean isPermitExpired;
	
	private String comment;
	
	private String signature;
	
	public AssistantClosingResposibility mapToClass() {
		AssistantClosingResposibility resposibility = new AssistantClosingResposibility();
		
		resposibility.setComment(comment);
		resposibility.setCompleted(isCompleted);
		resposibility.setEquipmentClosed(equipmentClosed);
		resposibility.setWorkZoneCleaned(workZoneCleaned);
		resposibility.setPermitExpired(isPermitExpired);
		resposibility.setEveryThingBackInPlace(everyThingBackInPlace);
		resposibility.setSafeguardsReinstalled(safeguardsReinstalled);
		resposibility.setSignature(signature);
		
		return resposibility;
	}
}
