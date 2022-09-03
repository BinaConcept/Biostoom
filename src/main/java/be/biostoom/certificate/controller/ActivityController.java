package be.biostoom.certificate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import be.biostoom.certificate.model.Activity;
import be.biostoom.certificate.service.ActivityService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

//@RestController
//@RequestMapping("${api.prefix}/activities")
public class ActivityController {
	@Autowired
	ActivityService activityService;
	
	@GetMapping
	public List<Activity> getActivities (){
		return activityService.getActivities();
	}

	@GetMapping("/{id}")
	public Activity getActivitiy(@PathVariable Long id){
		return activityService.getActivity(id);
	}

	@PostMapping
	public Activity create(@RequestBody Activity activity ) {
		return activityService.save(activity);
	}
 	
}
