package be.biostoom.certificate.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import be.biostoom.certificate.enumerated.PermitStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class StartPermit {
	@Id
	@Column(name="start_permit_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne
	@JsonIgnore
	@JoinColumn(name = "assistant_id", referencedColumnName = "employee_id")
	private Employee assistant;
	
	@Transient
	private long assistantId;
	
	@Transient
	private long applicantId;
	
	@OneToOne
	@JoinColumn(name = "applicant_id", referencedColumnName = "employee_id")
	@JsonIgnore
	private Employee applicant;
	
	private String applicantGsm;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="permit_id")
	@JsonIgnore
	private Permit permit;
	
	@Column(insertable=true, updatable=false)
	@JsonIgnore
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Europe/Brussels")
	private Date startDate;
	
	@Enumerated(EnumType.STRING)
	private PermitStatus status;
	
	public StartPermit getClone() {
		StartPermit clone = new StartPermit();
		clone.setId(this.id);
		clone.setApplicant(this.applicant);
		clone.setPermit(this.permit);
		clone.setStartDate(this.startDate);
		clone.setApplicantGsm(this.applicantGsm);
		return clone;
	}
	
}
