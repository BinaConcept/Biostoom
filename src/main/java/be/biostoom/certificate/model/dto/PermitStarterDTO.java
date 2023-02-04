package be.biostoom.certificate.model.dto;

import java.io.Serializable;

import be.biostoom.certificate.enumerated.PermitStatus;
import lombok.Data;

@Data
public class PermitStarterDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4844063511879294016L;
	
	private long permitId;
	
	private long employeeId;
	 
	private PermitStatus status;

}
