package com.org.kodvix.freelanceapp.controller;


import com.org.kodvix.freelanceapp.dto.FreelancerDTO;
import com.org.kodvix.freelanceapp.dto.JobSeekerDTO;
import com.org.kodvix.freelanceapp.entities.Freelancer;
import com.org.kodvix.freelanceapp.entities.JobSeeker;
import com.org.kodvix.freelanceapp.exceptions.InvalidFreelancerException;
import com.org.kodvix.freelanceapp.exceptions.InvalidJobSeekerException;
import com.org.kodvix.freelanceapp.exceptions.JobPortalValidationException;
import com.org.kodvix.freelanceapp.service.IJobSeekerService;
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
@RequestMapping("/jobseeker")
@CrossOrigin(origins = "*")
public class JobSeekerController {

    @Autowired
    IJobSeekerService jobSeekerService;

    @PostMapping("/add")
    public ResponseEntity<Object> createFreelancer(@Valid @RequestBody JobSeekerDTO jobSeekerDTO,
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
            jobSeekerService.save(jobSeekerDTO);
        } catch (InvalidJobSeekerException exception) {
            throw new InvalidJobSeekerException("One or more entered fields contain invalid objects.");
        }
        return new ResponseEntity<>("Added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/get/id/{id}")
    public JobSeeker getById(@PathVariable Long id) {
        try {
            return jobSeekerService.findById(id);
        }
        catch(InvalidJobSeekerException exception){
            throw new InvalidFreelancerException("JobSeeker with given id not found.");
        }
    }

}
