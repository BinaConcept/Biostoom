package be.biostoom.certificate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.biostoom.certificate.model.Activity;
import be.biostoom.certificate.service.ActivityService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("${api.prefix}/activities")
public class ActivityController {
	@Autowired
	ActivityService activityService;
	
	@GetMapping
	public List<Activity> activities (){
		return activityService.allActivities();
	}
	
	@PostMapping
	public Activity create(@RequestBody Activity activity ) {
		return new Activity();
	}
 	
}
