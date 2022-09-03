package be.biostoom.certificate.controller;

import be.biostoom.certificate.model.Permit;
import be.biostoom.certificate.model.dto.AssistantClosingDTO;
import be.biostoom.certificate.model.dto.WorkFlowDTO;
import be.biostoom.certificate.service.PermitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/admin")
public class AdminController {
    @Autowired
    PermitService service;

    @GetMapping
    public List<Permit> getPermits(){
        return service.getPermits();
    }

    @PostMapping
    public Permit save(@RequestBody Permit permit){
        return service.save(permit);
    }

    @GetMapping("/{id}")
    public Permit getPermit(@PathVariable Long id){
        return service.getPermit(id);
    }
    
    @PutMapping("/start")
    public List<Permit> startPermit(@RequestBody WorkFlowDTO dto){
      return service.startsPermit(dto);
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
