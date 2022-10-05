package be.biostoom.certificate.model;


public class AuthenthicationResponse {

	private final String jwt;
	
	public AuthenthicationResponse(String jwt) {
		this.jwt = jwt;
	}
	
	public String getJwt() {
		return jwt;
	}
	
}
