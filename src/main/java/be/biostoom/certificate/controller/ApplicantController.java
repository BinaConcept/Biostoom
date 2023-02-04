package be.biostoom.certificate.controller;

import be.biostoom.certificate.model.Permit;
import be.biostoom.certificate.model.SiteIntroduction;
import be.biostoom.certificate.model.dto.ApplicantStopPermitDTO;
import be.biostoom.certificate.model.dto.ListItem;
import be.biostoom.certificate.model.dto.PermitStarterDTO;
import be.biostoom.certificate.repository.LocationRepository;
import be.biostoom.certificate.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/applicant")
public class ApplicantController {

	@Autowired
	ApplicantService service;

	@Autowired
	LocationRepository locationRepository;

	@GetMapping("/site-introduction")
	public SiteIntroduction setSiteIntroduction() {
		return service.getSiteIntroduction();
	}

	@GetMapping("/locations")
	public List<ListItem> getLocations(){
		return locationRepository.findAllLocations();
	}
	@PostMapping("/start-permit")
	public Long createPermit(@RequestBody Permit permit) {
		return service.createPermit(permit);
	}

	@PutMapping("/stop-permit")
	public Long stopPermit(@RequestBody ApplicantStopPermitDTO dto) {
		return service.applicantStopPermit(dto);
	}

	@PutMapping("/restart-permit")
	public Long restartPermit(@RequestBody PermitStarterDTO dto)
	{
		return service.applicantRestartsPermit(dto);
	}

}
