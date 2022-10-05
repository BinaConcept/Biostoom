package be.biostoom.certificate.controller;

import be.biostoom.certificate.model.Permit;
import be.biostoom.certificate.model.dto.AssistantClosingDTO;
import be.biostoom.certificate.model.dto.RestarterDTO;
import be.biostoom.certificate.service.PermitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/admin/permits")
public class AdminController {
    @Autowired
    PermitService service;

    @GetMapping
    public List<Permit> getPermits(){
        return service.getPermits();
    }

    @GetMapping("/{id}")
    public Permit getPermit(@PathVariable Long id){
        return service.getPermit(id);
    }
    
    @PutMapping("/start")
    public List<Permit> startPermit(@RequestBody RestarterDTO dto){
      return service.startPermit(dto);
    }
    
    @PutMapping("/stop")
    public List<Permit> stopPermit(@RequestBody AssistantClosingDTO dto){
      return service.stopPermit(dto);
    }
    
    @DeleteMapping("/{id}")
    public String deletPermit(@PathVariable long id){
      return service.deletePermit(id);
    }
}
