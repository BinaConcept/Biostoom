package be.biostoom.certificate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.biostoom.certificate.model.Permit;
import be.biostoom.certificate.model.SiteIntroduction;
import be.biostoom.certificate.model.dto.ApplicantClosingDTO;
import be.biostoom.certificate.repository.SiteIntroductionRepository;

@Service
public class ApplicantService {

	@Autowired
	SiteIntroductionRepository introductionRepository;
	
	@Autowired
	PermitService permitService;
	
	public SiteIntroduction getSiteIntroduction() {
		return introductionRepository.findAll().get(0);
	}

	public Permit save(Permit permit) {
		return permitService.save(permit);
	}

	public String applicantClosing(ApplicantClosingDTO dto) {
		return permitService.applicantClosing(dto);
	}
	

}
