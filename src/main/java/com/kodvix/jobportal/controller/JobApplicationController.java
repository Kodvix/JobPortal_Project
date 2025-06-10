package com.kodvix.jobportal.controller;

import com.kodvix.jobportal.dto.JobApplicationDTO;
import com.kodvix.jobportal.exceptions.JobPortalValidationException;
import com.kodvix.jobportal.service.IJobApplicationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/jobApplication")
@CrossOrigin(origins = "*")
public class JobApplicationController {

    @Autowired
    private IJobApplicationService jobApplicationService;

    @ApiOperation("Apply to a job")
    @PostMapping("/apply")
    public ResponseEntity<String> applyToJob(@Valid @RequestBody JobApplicationDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage).collect(Collectors.toList());
            throw new JobPortalValidationException(errors);
        }

        jobApplicationService.applyToJob(dto);
        return new ResponseEntity<>("Job Applied Successfully", HttpStatus.CREATED);
    }

    @ApiOperation("Get all job applications")
    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        return ResponseEntity.ok(jobApplicationService.findAll());
    }

    @ApiOperation("Delete job application by ID")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> remove(@PathVariable Long id) {
        jobApplicationService.remove(id);
        return ResponseEntity.ok("Job application deleted successfully");
    }

    @ApiOperation("Update job application by ID")
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateJobApplication(
            @PathVariable Long id,
            @Valid @RequestBody JobApplicationDTO dto,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getFieldErrors().stream()
                    .map(FieldError::getDefaultMessage).collect(Collectors.toList());
            throw new JobPortalValidationException(errors);
        }

        jobApplicationService.updateJobApplication(id, dto);
        return ResponseEntity.ok("Job application updated successfully");
    }

    @ApiOperation("Get job applications by job ID")
    @GetMapping("/findAll/job/{jobId}")
    public ResponseEntity<?> findAllApplicationsByJob(@PathVariable Long jobId) {
        return ResponseEntity.ok(jobApplicationService.findAllByJobId(jobId));
    }

    @ApiOperation("Get job applications by job ID and freelancer ID")
    @GetMapping("/find/job/{jobId}/freelancer/{freelancerId}")
    public ResponseEntity<?> findByJobAndFreelancer(
            @PathVariable Long jobId,
            @PathVariable Long freelancerId) {
        return ResponseEntity.ok(jobApplicationService.findByJobIdAndFreelancerId(jobId, freelancerId));
    }
}
