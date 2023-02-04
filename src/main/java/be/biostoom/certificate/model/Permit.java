package be.biostoom.certificate.model;

import javax.persistence.*;

import be.biostoom.certificate.model.dto.PermitStarterDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import be.biostoom.certificate.enumerated.PermitStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permit implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2069274663268411093L;

	@Id
	@Column(name = "permit_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Enumerated(EnumType.STRING)
	private PermitStatus status;

	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToOne(fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	@JoinColumn(name = "activity_id", referencedColumnName = "activity_id")
	private Activity activity;

	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "risk_assesment_id", referencedColumnName = "risk_assesment_id")
	private RiskAssesment riskAssesment;

	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "precautionary_measures_id", referencedColumnName = "precautionary_measures_id")
	private PrecautionaryMeasures precautionaryMeasures;


	@JsonIgnore
	@EqualsAndHashCode.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "location_id")
	private Location location;

	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,orphanRemoval=true,mappedBy="permit")
	@JsonIgnore
    private Set<StartPermit> startPermits = new HashSet<StartPermit>();

	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,orphanRemoval=true,mappedBy="permit")
	@JsonIgnore
    private Set<StopPermit> stopPermits = new HashSet<StopPermit>();

	@JsonIgnore
	@EqualsAndHashCode.Exclude
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COMPANY_ID")
	Company company;

	@Transient
	private Long applicantId;

	@Transient
	private Long companyId;

	@Transient
	private Long locationId;

	@Transient
	private Date startDate;

	@Transient
	private Date endDate;

	@PrePersist
	private void setActors() {

		this.startPermits.forEach(startPermit->{
			startPermit.setPermit(this);
		});
		
		this.stopPermits.forEach(stopPermit->{
			stopPermit.setPermit(this);
		});
	}
		
}
