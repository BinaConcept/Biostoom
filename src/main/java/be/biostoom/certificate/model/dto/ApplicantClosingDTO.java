package be.biostoom.certificate.model.dto;

import java.io.Serializable;

import be.biostoom.certificate.model.ApplicantClosingResposibility;
import lombok.Data;

@Data
public class ApplicantClosingDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4937633164675516184L;
	
	private long permitId;
	
	private boolean isWorkZoneChecked;
	
	private boolean isLottoRemoved;
	
	private String comment;
	
	private String signature;
	
	public ApplicantClosingResposibility mapToClass() {
		ApplicantClosingResposibility resposibility = new ApplicantClosingResposibility();
		
		resposibility.setComment(comment);
		resposibility.setWorkZoneChecked(isWorkZoneChecked);
		resposibility.setLottoRemoved(isLottoRemoved);
		resposibility.setSignature(signature);
				
		return resposibility;
	}
}
