package com.org.kodvix.freelanceapp.controller;

import com.org.kodvix.freelanceapp.dto.JobApplicationDTO;
import com.org.kodvix.freelanceapp.exceptions.InvalidJobApplicationException;
import com.org.kodvix.freelanceapp.exceptions.InvalidJobException;
import com.org.kodvix.freelanceapp.exceptions.JobPortalValidationException;
import com.org.kodvix.freelanceapp.service.IJobApplicationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "/jobApplication")
@CrossOrigin(origins = "*")
public class JobApplicationController {

	@Autowired
	IJobApplicationService jobApplicationService;



	@ApiOperation(value = "apply to job application")
	@PostMapping(value = "/apply")
	public ResponseEntity<String> applyToJob(@Valid @RequestBody JobApplicationDTO jobApplicationDto,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			System.out.println("Some errors exist!");
			List<FieldError> fieldErrors = bindingResult.getFieldErrors();

			List<String> errMessages = new ArrayList<>();
			for (FieldError fe : fieldErrors) {
				errMessages.add(fe.getDefaultMessage());
			}
			throw new JobPortalValidationException(errMessages);
		}
		try {
			jobApplicationService.applyToJob(jobApplicationDto);
		} catch (InvalidJobException e) {
			throw new InvalidJobException("Job not found With given Id");
		} catch (InvalidJobApplicationException exception) {
			throw new InvalidJobApplicationException("One or more entered fields contain invalid objects.");
		}
		return new ResponseEntity<>("Job Applied Successfully", HttpStatus.CREATED);
	}



	@GetMapping(value = "/findAll")
	public ResponseEntity<Object> findAll() {
		try {
			return new ResponseEntity<>(jobApplicationService.findAll(), HttpStatus.OK);
		} catch (InvalidJobApplicationException e) {
			throw new InvalidJobApplicationException("Job Application Id Not Found");
		}
	}


	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<Object> remove(@Valid @PathVariable Long id) {
		try {
			jobApplicationService.remove(id);
			return new ResponseEntity<>("job application deleted successfully", HttpStatus.OK);
		} catch (InvalidJobApplicationException e) {
			throw new InvalidJobApplicationException("Invalid Job Application Id");
		}
	}



		@PutMapping(value = "/update/{id}")
	public ResponseEntity<Object> updateJobApplication(@Valid @PathVariable Long id,
			@RequestBody JobApplicationDTO jobApplicationDto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			System.out.println("Some errors exist!");
			List<FieldError> fieldErrors = bindingResult.getFieldErrors();

			List<String> errMessages = new ArrayList<>();
			for (FieldError fe : fieldErrors) {
				errMessages.add(fe.getDefaultMessage());
			}
			throw new JobPortalValidationException(errMessages);
		}
		try {
			jobApplicationService.updateJobApplication(id, jobApplicationDto);
			return new ResponseEntity<>("job application updated successfully", HttpStatus.OK);
		} catch (InvalidJobApplicationException e) {
			throw new InvalidJobApplicationException("Invalid Job Application Id");
		}
	}

	@GetMapping(value = "/findAll/job/{jobId}")
	public ResponseEntity<Object> findAllApplications(@PathVariable Long jobId) {
		try {
			return new ResponseEntity<>(jobApplicationService.findAllByJobId(jobId), HttpStatus.OK);
		} catch (InvalidJobApplicationException e) {
			throw new InvalidJobApplicationException("Job with given Id not found");
		}
	}

//	@GetMapping(value = "/findByFrId/job/{jobId}/frId/{frId}")
//	public ResponseEntity<Object> findByFrId(@PathVariable Long jobId, @PathVariable Long frId) {
//		return new ResponseEntity<>(jobApplicationService.findByFreelancerId(jobId, frId), HttpStatus.OK);
//	}

}