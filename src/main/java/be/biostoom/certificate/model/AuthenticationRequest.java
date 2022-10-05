package be.biostoom.certificate.model;

import lombok.Data;

@Data
public class AuthenticationRequest {

	private String username;
	
	private String password;
}
