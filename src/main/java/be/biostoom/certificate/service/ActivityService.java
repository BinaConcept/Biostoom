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
	
	public List<Activity> allActivities () {
		return repository.findAll();
	}
}
