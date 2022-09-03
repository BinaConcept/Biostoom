package be.biostoom.certificate.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonIgnore;

import be.biostoom.certificate.enumerated.PermitStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
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
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "activity_id", referencedColumnName = "activity_id")
	private Activity activity;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "risk_assesment_id", referencedColumnName = "risk_assesment_id")
	private RiskAssesment riskAssesment;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "precautionary_measures_id", referencedColumnName = "precautionary_measures_id")
	private PrecautionaryMeasures precautionaryMeasures;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,orphanRemoval=true,mappedBy="permit")
	@JsonIgnore
    private Set<StartPermit> startPermits = new HashSet<StartPermit>();
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,orphanRemoval=true,mappedBy="permit")
	@JsonIgnore
    private Set<StopPermit> stopPermits = new HashSet<StopPermit>();
	
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
