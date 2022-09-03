package be.biostoom.certificate.service;

import be.biostoom.certificate.enumerated.PermitStatus;
import be.biostoom.certificate.model.Permit;
import be.biostoom.certificate.model.StartPermit;
import be.biostoom.certificate.model.StopPermit;
import be.biostoom.certificate.model.dto.ApplicantClosingDTO;
import be.biostoom.certificate.model.dto.AssistantClosingDTO;
import be.biostoom.certificate.model.dto.WorkFlowDTO;
import be.biostoom.certificate.repository.PermitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PermitService {
	@Autowired
	PermitRepository repository;

	public List<Permit> getPermits() {
		return repository.findAll();
	}

	public Permit save(Permit permit) {
		preparePermit(permit);
		return repository.save(permit);
	}

	private void preparePermit(Permit permit) {

		if (permit.getStatus().equals(PermitStatus.PENDING)) {
			StartPermit startPermit = new StartPermit();

			startPermit.setAssistant(null);
			startPermit.setStatus(PermitStatus.PENDING);
			// TODO take the creater from logged in users or sending id of the user.
//    		startPermit.setApplicant(null);
			startPermit.setStartDate(new Date());
			permit.getStartPermits().add(startPermit);
		}

		if (permit.getStatus().equals(PermitStatus.STARTED)) {
			StartPermit startPermit = permit.getStartPermits().stream().filter(x -> x.getAssistant() == null)
					.findFirst().get();

			StartPermit cloned = startPermit.getClone();
			cloned.setStatus(PermitStatus.STARTED);
			// TODO take the assistant from logged in users or sending id of the user.
//    		startPermit.setAssistant(null);
						
			permit.getStartPermits().remove(startPermit);

//			System.out.println("time: " + permit.getStartPermits().size());

			permit.getStartPermits().add(cloned);

		}
		
		if (permit.getStatus().equals(PermitStatus.RESTARTED)) {
			StartPermit startPermit = new StartPermit();

			startPermit.setPermit(permit);
			startPermit.setAssistant(null);
			startPermit.setApplicant(null);
			startPermit.setStatus(PermitStatus.RESTARTED);
			
			// TODO take the creater from logged in users or sending id of the user.
//    		startPermit.setApplicant(null);
			startPermit.setStartDate(new Date());
			permit.getStartPermits().add(startPermit);

		}


		if (permit.getStatus().equals(PermitStatus.STOPED)) {
			StopPermit stopPermit = new StopPermit();

			// TODO take the assistant from logged in users or sending id of the user.
    		stopPermit.setAssistant(null);
    		stopPermit.setPermit(permit);

			// TODO take the creater from logged in users or sending id of the user.
    		stopPermit.setApplicant(null);
			stopPermit.setEndDate(new Date());
			permit.getStopPermits().add(stopPermit);
		}
		
		if (permit.getStatus().equals(PermitStatus.CLOSED)) {
			StopPermit stopPermit = new StopPermit();

			// TODO take the assistant from logged in users or sending id of the user.
    		stopPermit.setAssistant(null);
    		stopPermit.setPermit(permit);

			// TODO take the creater from logged in users or sending id of the user.
    		stopPermit.setApplicant(null);
			stopPermit.setEndDate(new Date());
			permit.getStopPermits().add(stopPermit);
		}

	}

	public Permit getPermit(long id) {
		return repository.findById(id).get();
	}

	public List<Permit> startsPermit(WorkFlowDTO dto) {
		Permit permit = repository.findById(dto.getPermitId()).get();
		permit.setStatus(dto.getStatus());
		preparePermit(permit);
		repository.save(permit);
		return repository.findAll();
	}
	
	
	public String deletePermit(long id) {
		repository.deleteById(id);
		return "Permit deleted";
	}

	public List<Permit> stopPermit(AssistantClosingDTO dto) {
		Permit permit = repository.findById(dto.getPermitId()).get();
		
		StopPermit stopPermit = permit.getStopPermits().stream().filter(x -> x.getAssistant() == null)
				.findFirst().get();
		
		// TODO take the assistant from logged in users or sending id of the user.
		stopPermit.setAssistant(null);
		
		stopPermit.setAssistantClosingResposibility(dto.mapToClass());
		
		stopPermit.setEndDate(new Date());
		
		StopPermit cloned = stopPermit.cloned(); 
		
		permit.getStopPermits().remove(stopPermit);

//		System.out.println("time: " + permit.getStartPermits().size());

		permit.getStopPermits().add(cloned);
		
		
		repository.save(permit);
		
		return null;
	}
	
	public String applicantClosing(ApplicantClosingDTO dto) {
		
		Permit permit = repository.findById(dto.getPermitId()).get();
		
		StopPermit stopPermit = new StopPermit();
		
		stopPermit.setPermit(permit);
		// TODO take the creater from logged in users or sending id of the user.
		stopPermit.setApplicant(null);
		
		stopPermit.setEndDate(new Date());
		
		stopPermit.setApplicantClosingResposibility(dto.mapToClass());
		
		permit.getStopPermits().add(stopPermit);
		
		repository.save(permit);
		
		return "permit stoped";
	}
	
	
}
