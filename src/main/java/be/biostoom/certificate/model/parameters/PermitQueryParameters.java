package be.biostoom.certificate.model.parameters;
import be.biostoom.certificate.enumerated.PermitStatus;
import be.biostoom.certificate.model.dto.DateRange;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Getter
@EqualsAndHashCode(callSuper = true)
public class PermitQueryParameters extends BaseQueryParameters implements Serializable {
	private static final long serialVersionUID = 521105294636863172L;
	PermitStatus status;
	DateRange permitRange;
	Long companyId;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date startDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	Date endDate;

	public void setStatus(PermitStatus status) {
		this.map.put("status", status);
		this.status = status;
	}

	public void setPermitRange(DateRange permitRange) {
		this.map.put("permitRange", permitRange);
		this.permitRange = permitRange;
	}

	public void setStartDate(Date startDate) {
		this.map.put("startDate", startDate);
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.map.put("endDate", endDate);
		this.endDate = endDate;
	}

	public void setCompanyId(Long companyId) {
		this.map.put("companyId", companyId);
		this.companyId = companyId;
	}
}
