package be.biostoom.certificate.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SiteIntroduction {

	@Id
	@Column(name = "site_introduction_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "siteIntroduction")
	@JsonIgnore
	private Set<IntroductionPoint> introductionPoints = new HashSet<IntroductionPoint>();

	@PrePersist
	public void setSiteIntroductiononCreate() {
		introductionPoints.forEach(point -> {
			point.setSiteIntroduction(this);
		});
	}
	
	@PreUpdate
	public void setSiteIntroductionOnUpdate() {
		introductionPoints.forEach(point -> {
			point.setSiteIntroduction(this);
		});
	}
	

}
