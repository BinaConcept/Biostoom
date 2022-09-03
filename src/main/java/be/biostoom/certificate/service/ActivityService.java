package be.biostoom.certificate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.biostoom.certificate.model.Activity;
import be.biostoom.certificate.repository.ActivityRepository;

@Service
public class ActivityService {

	@Autowired
	ActivityRepository repository;
	
	public List<Activity> getActivities() {
		return repository.findAll();
	}

    public Activity getActivity(Long id) {
		return repository.findById(id).get();
    }

	public Activity save(Activity activity) {
		return repository.save(activity);
	}
}
