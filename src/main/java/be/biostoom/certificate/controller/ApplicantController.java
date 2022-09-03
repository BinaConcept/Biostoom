package be.biostoom.certificate.controller;

import be.biostoom.certificate.model.Permit;
import be.biostoom.certificate.model.SiteIntroduction;
import be.biostoom.certificate.model.dto.ApplicantClosingDTO;
import be.biostoom.certificate.service.ApplicantService;
import be.biostoom.certificate.service.PermitService;
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
  public SiteIntroduction setSiteIntroduction(){
      return service.getSiteIntroduction();
  }

    @PostMapping("/permit-start")
    public Permit save(@RequestBody Permit permit){
        return service.save(permit);
    }
    
    @PutMapping("/permit-stop")
    public String stopPermit(@RequestBody ApplicantClosingDTO dto){
      return service.applicantClosing(dto);
    }
    
    
    
}
