package be.biostoom.certificate.controller;

import be.biostoom.certificate.model.Permit;
import be.biostoom.certificate.model.dto.AssistantClosingDTO;
import be.biostoom.certificate.model.dto.PermitOverviewDTO;
import be.biostoom.certificate.model.dto.StarterDTO;
import be.biostoom.certificate.model.parameters.PermitQueryParameters;
import be.biostoom.certificate.service.PermitService;
import be.biostoom.certificate.util.PaginatedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/admin/permits")
public class AdminController {
    @Autowired
    PermitService service;

    @GetMapping
    public PaginatedResponse<PermitOverviewDTO> getAllPermits(PermitQueryParameters parameters) {
        return service.getAllPermits(parameters.getMap());
    }

    @GetMapping("/{id}")
    public Permit getPermit(@PathVariable Long id){
        return service.getPermit(id);
    }
    
    @PutMapping("/start")
    public Long startPermit(@RequestBody StarterDTO dto){
      return service.adminStartPermit(dto);
    }
    
    @PutMapping("/stop")
    public Long stopPermit(@RequestBody AssistantClosingDTO dto){
      return service.adminStopsPermit(dto);
    }
    
    @DeleteMapping("/{id}")
    public String deletPermit(@PathVariable long id){
      return service.deletePermit(id);
    }
}
