package eu.europa.ec.jrc.milc.domain.dto.parameters;

import java.io.Serializable;

import eu.europa.ec.jrc.milc.domain.PTRound;
import eu.europa.ec.jrc.milc.domain.PTRound.Status;
import eu.europa.ec.jrc.milc.domain.dto.DateRange;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = true)
public class PTRoundOverviewParameters extends BaseQueryParameters implements Serializable {
	private static final long serialVersionUID = 521105294636863172L;
	String name;
	String ptScheme;
	String matrix;
	PTRound.Type type;
	PTRound.Status status;
	DateRange registrationRange;
	DateRange reportingRange;
	String firstName;
	String lastName;
	public void setName(String name) {
		this.map.put("name", name);
		this.name = name;
	}
	public void setPtScheme(String ptScheme) {
		this.map.put("ptScheme", ptScheme);
		this.ptScheme = ptScheme;
	}
	public void setMatrix(String matrix) {
		this.map.put("matrix", matrix);
		this.matrix = matrix;
	}
	public void setType(PTRound.Type type) {
		this.map.put("type", type);
		this.type = type;
	}
	public void setStatus(Status status) {
		this.map.put("status", status);
		this.status = status;
	}
	public void setRegistrationRange(DateRange registrationRange) {
		this.map.put("registration", registrationRange);
		this.registrationRange = registrationRange;
	}
	public void setReportingRange(DateRange reportingRange) {
		this.map.put("reporting", reportingRange);
		this.reportingRange = reportingRange;
	}
	public void setFirstName(String firstName) {
		this.map.put("firstName", firstName);
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.map.put("lastName", lastName);
		this.lastName = lastName;
	}
}
