package be.biostoom.certificate.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class StopPermit  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -465917572108142465L;

	@Id
	@Column(name="stop_permit_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToOne
	@JoinColumn(name = "assistant_id", referencedColumnName = "employee_id")
	private Employee assistant;
	
	@OneToOne
	@JoinColumn(name = "applicant_id", referencedColumnName = "employee_id")
	private Employee applicant;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "assistant_closing_responsibility_id", referencedColumnName = "assistant_closing_responsibility_id")
	private AssistantClosingResposibility assistantClosingResposibility;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "applicant_closing_responsibility_id", referencedColumnName = "applicant_closing_responsibility_id")
	private ApplicantClosingResposibility applicantClosingResposibility;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="permit_id")
	@JsonIgnore
	private Permit permit;
	
	@Column(insertable=true, updatable=false)
	@JsonIgnore
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "Europe/Brussels")
	private Date endDate;
	
	public StopPermit cloned() {
		StopPermit clone = new StopPermit();
		
		clone.setId(id);
		clone.setApplicant(applicant);
		clone.setAssistant(assistant);
		clone.setAssistantClosingResposibility(assistantClosingResposibility);
		clone.setEndDate(endDate);
		clone.setApplicantClosingResposibility(applicantClosingResposibility);
		clone.setPermit(permit);
		
		return clone;
	}
}
