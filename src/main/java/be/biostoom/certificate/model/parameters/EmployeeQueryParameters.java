package be.biostoom.certificate.model.parameters;
import be.biostoom.certificate.enumerated.PermitStatus;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Getter
@EqualsAndHashCode(callSuper = true)
public class EmployeeQueryParameters extends BaseQueryParameters implements Serializable {
	private static final long serialVersionUID = 521105294636863172L;
	Long companyId;

	String query;

	public void setQuery(String query) {
		this.map.put("query", query);
		this.query = query;
	}

	public void setCompanyId(Long companyId) {
		this.map.put("companyId", companyId);
		this.companyId = companyId;
	}
}
