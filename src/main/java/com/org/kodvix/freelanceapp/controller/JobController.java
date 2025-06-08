package com.org.kodvix.freelanceapp.controller;

import com.org.kodvix.freelanceapp.dto.JobDTO;
import com.org.kodvix.freelanceapp.exceptions.InvalidJobException;
import com.org.kodvix.freelanceapp.service.IJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 
 * @author   Akash Sunil Kumar
 * Description: This class is the controller for the job module 
 * Created Date: 18 April, 2021 
 * Version : v1.0.0
 */

@RestController
@RequestMapping("/job")
@CrossOrigin(origins = "*")
public class JobController {

	@Autowired
    IJobService jobService;



	@GetMapping("/close/{id}")
	public ResponseEntity<Object> close(@PathVariable Long id) {
		try {
			jobService.close(id);
		} catch (InvalidJobException exception) {
			throw new InvalidJobException("Job with given id not found");
		}
		return new ResponseEntity<>("closed successfully", HttpStatus.OK);

	}

	/**
	 * 
	 * @param        id
	 * @return       Response Entity of Job type
	 * Description : This method finds a job by id
	 * @GetMapping: Annotation for mapping HTTP GET requests onto specific handler methods.
	 * 
	 */

	@GetMapping(value = "/findById/{id}")
	public ResponseEntity<Object> findById(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(jobService.findById(id), HttpStatus.OK);
		} catch (InvalidJobException exception) {
			throw new InvalidJobException("Job with given id doesn't exist");
		}

	}

	/**
	 * 
	 * @param        name
	 * @return       Response Entity of Object type
	 * Description : This method finds a job by Skill
	 * @GetMapping: Annotation for mapping HTTP GET requests onto specific handler methods.
	 * 
	 */

	@GetMapping(value = "/findJobsBySkill/{name}")
	@ResponseBody
	public ResponseEntity<Object> findbyskill(@PathVariable String name) {
		try {
			return new ResponseEntity<>(jobService.findJobsBySkillName(name), HttpStatus.OK);
		} catch (InvalidJobException exception) {
			throw new InvalidJobException("no job with this skill found");
		}

	}

	/**
	 * 
	 * @param        jobDto
	 * @return       Response Entity of Object type
	 * Description : This method posts a new job.
	 * @PostMapping: Annotation for mapping HTTP POST requests onto specific handler methods.
	 * 
	 */

	@PostMapping("/postJob")
	public ResponseEntity<Object> job(@RequestBody JobDTO jobDto) {
		System.out.println(jobDto);
		jobService.postJob(jobDto);
		return new ResponseEntity<>("Job Posted Successfully", HttpStatus.OK);
	}

	@GetMapping("/findAll")
	public ResponseEntity<Object> findAll() {
		return new ResponseEntity<>(jobService.findAll(), HttpStatus.OK);
	}

	@PutMapping("/awardJob/{jobId}/{freelancerId}")
	public ResponseEntity<String> awardJob(@PathVariable Long jobId, @PathVariable Long freelancerId) {
		jobService.awardJob(jobId, freelancerId);
		String response = "Job Awarded Successfully";
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/getAllActive")
	public ResponseEntity<Object> getAllActiveJobs() {
		return new ResponseEntity<>(jobService.findAllActiveJobs(), HttpStatus.OK);
		
	}

}
