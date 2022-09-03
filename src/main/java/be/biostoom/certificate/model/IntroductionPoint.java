package be.biostoom.certificate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class IntroductionPoint {

	@Id
	@Column(name="introduction_point_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String point;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="site_introduction_id")
	@JsonIgnore
	private SiteIntroduction siteIntroduction;
}
