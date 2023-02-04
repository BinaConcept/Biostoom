package be.biostoom.certificate.service;

import be.biostoom.certificate.enumerated.PermitStatus;
import be.biostoom.certificate.model.*;
import be.biostoom.certificate.model.dto.ApplicantStopPermitDTO;
import be.biostoom.certificate.model.dto.AssistantClosingDTO;
import be.biostoom.certificate.model.dto.PermitOverviewDTO;
import be.biostoom.certificate.model.dto.PermitStarterDTO;
import be.biostoom.certificate.repository.LocationRepository;
import be.biostoom.certificate.repository.PermitRepository;
import be.biostoom.certificate.specification.PermitSpecification;
import be.biostoom.certificate.util.PageRequestExtractor;
import be.biostoom.certificate.util.PaginatedResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PermitService extends AbstractEmployeeService {
    @Autowired
    PermitRepository repository;

    @Autowired
    EmployeeService employeeservice;

    @Autowired
    CompanyService companyService;

    @Autowired
    LocationRepository locationRepository;

    public PaginatedResponse<PermitOverviewDTO> getAllPermits(Map<String, Object> parameters) {
        Pageable pageRequest = PageRequestExtractor.extract(parameters);
        PaginatedResponse<Permit> response = repository.findAll(Permit.class, PermitSpecification.forParams(parameters).withAccessFilter(), pageRequest);

        return response.mapTo(PermitOverviewDTO::new);
    }

    public Long applicantCreatePermit(Permit permit) {
        preparePermitApplicant(permit);
        return repository.save(permit).getId();
    }

    public Long applicantReStartPermit(PermitStarterDTO dto) {
        Permit permit = repository.findById(dto.getPermitId()).get();
        permit.setApplicantId(dto.getEmployeeId());
        permit.setCompanyId(permit.getCompany().getId());
        preparePermitApplicant(permit);
        return repository.save(permit).getId();
    }


    public Permit getPermit(long id) {
        Optional<Permit> permit = repository.findByIdWithAllRelationships(id);

        return permit.get();
    }

    public Long adminStartPermit(PermitStarterDTO dto) {
        return repository.save(adminStartsPermit(dto)).getId();
    }

    public String deletePermit(long id) {
        repository.deleteById(id);
        return "Permit deleted";
    }

    public Long adminStopsPermit(AssistantClosingDTO dto) {
        Employee assistant = employeeservice.getEmployee(dto.getAssistantId());

        Permit permit = repository.findById(dto.getPermitId()).get();

        StopPermit stopPermit = permit.getStopPermits().stream().filter(x -> x.getAssistant() == null)
                .findFirst().get();

        // TODO take the assistant from logged in users or sending id of the user.
        stopPermit.setAssistant(assistant);
        stopPermit.setAssistantClosingResposibility(dto.mapToClass());
        stopPermit.setEndDate(new Date());
        StopPermit cloned = stopPermit.cloned();
        permit.getStopPermits().remove(stopPermit);
        permit.getStopPermits().add(cloned);
        return repository.save(permit).getId();
    }

    public Long applicantStopPermit(ApplicantStopPermitDTO dto) {

        Permit permit = repository.findById(dto.getPermitId()).get();

        Employee applicant = employeeservice.getEmployee(dto.getApplicantId());

        StopPermit stopPermit = permit.getStopPermits().stream().filter(x -> x.getApplicant() == null)
                .findFirst().get();
        stopPermit.setPermit(permit);
        stopPermit.setApplicant(applicant);

        stopPermit.setEndDate(new Date());
        StopPermit cloned = stopPermit.cloned();
        permit.getStopPermits().remove(stopPermit);
        permit.getStopPermits().add(cloned);


        stopPermit.setApplicantClosingResposibility(dto.mapToClass());

        permit.getStopPermits().add(stopPermit);

        return repository.save(permit).getId();
    }

    private void preparePermitApplicant(Permit permit) {

        StartPermit startPermit = new StartPermit();
        StopPermit stopPermit = new StopPermit();
        stopPermit.setEndDate(permit.getEndDate());
        permit.getStopPermits().add(stopPermit);

        Company company = companyService.getCompany(permit.getCompanyId());
        Location location = locationRepository.getById(permit.getLocationId());

        startPermit.setAssistant(null);
        startPermit.setStatus(PermitStatus.PENDING);
        // TODO take the creater from logged in users or sending id of the user.
        startPermit.setApplicant(getEmployee(permit.getApplicantId()));
        startPermit.setStartDate(permit.getStartDate());
        permit.getStartPermits().add(startPermit);
        permit.setCompany(company);
        permit.setLocation(location);
    }


    private Permit adminStartsPermit(PermitStarterDTO permitStarterDTO) {
        Permit permit = repository.findById(permitStarterDTO.getPermitId()).get();

        Employee assistant = employeeservice.getEmployee(permitStarterDTO.getEmployeeId());

        StartPermit startPermit = permit.getStartPermits().stream().filter(x -> x.getAssistant() == null)
                .findFirst().get();
        StartPermit cloned = startPermit.getClone();

        cloned.setAssistant(assistant);
        cloned.setStatus(permitStarterDTO.getStatus());

        permit.getStartPermits().remove(startPermit);
        permit.getStartPermits().add(cloned);
        permit.setStatus(permitStarterDTO.getStatus());

        return permit;
    }

}
