package be.biostoom.certificate.controller;

import be.biostoom.certificate.model.Permit;
import be.biostoom.certificate.model.SiteIntroduction;
import be.biostoom.certificate.model.dto.ApplicantStopPermitDTO;
import be.biostoom.certificate.model.dto.StarterDTO;
import be.biostoom.certificate.service.ApplicantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/applicant")
public class ApplicantController {

	@Autowired
	ApplicantService service;

//    @GetMapping
//    public List<Permit> getPermits(){
//        return service.getPermits();
//    }



	@GetMapping("/site-introduction")
	public SiteIntroduction setSiteIntroduction() {
		return service.getSiteIntroduction();
	}

	@PostMapping("/start-permit")
	public Long startPermit(@RequestBody Permit permit) {
		return service.save(permit);
	}

	@PutMapping("/stop-permit")
	public Long stopPermit(@RequestBody ApplicantStopPermitDTO dto) {
		return service.applicantStopPermit(dto);
	}

	@PutMapping("/restart-permit")
	public Long restartPermit(@RequestBody StarterDTO dto)
	{
		return service.applicantRestartsPermit(dto);
	}

}
