package com.kodvix.jobportal.controller;

import com.kodvix.jobportal.dto.FreelancerDTO;
import com.kodvix.jobportal.exceptions.InvalidFreelancerException;
import com.kodvix.jobportal.exceptions.JobPortalValidationException;
import com.kodvix.jobportal.service.IFreelancerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**************************************************************************************
 * @author Aditya
 * Description: Rest controller class for Freelancer module. 
 *************************************************************************************/
@RestController
@RequestMapping("/freelancer")
@CrossOrigin(origins = "*")
public class FreelancerController {

    @Autowired
    IFreelancerService freelancerService;

    @PostMapping("/add")
    public ResponseEntity<Object> createFreelancer(@Valid @RequestBody FreelancerDTO freelancerDto,
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
            freelancerService.save(freelancerDto);
        } catch (InvalidFreelancerException exception) {
            throw new InvalidFreelancerException("One or more entered fields contain invalid objects.");
        }
        return new ResponseEntity<>("Added successfully", HttpStatus.CREATED);
    }


    @GetMapping("/get/id/{id}")
    public FreelancerDTO getById(@PathVariable Long id) {
        try {
            return freelancerService.findById(id);
        } catch (InvalidFreelancerException exception) {
            throw new InvalidFreelancerException("Freelancer with given id not found.");
        }
    }


    @GetMapping("/get/name/{userName}")
    public FreelancerDTO getByUserName(@PathVariable String userName) {
        try {
            return freelancerService.findByUserName(userName);
        } catch (InvalidFreelancerException exception) {
            throw new InvalidFreelancerException("Freelancer with userName not found.");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateFreelancer(@Valid @PathVariable Long id,
                                                   @RequestBody FreelancerDTO freelancerDto) {
        try {
            freelancerService.update(id, freelancerDto);
        } catch (InvalidFreelancerException exception) {
            throw new InvalidFreelancerException("Freelancer with given id not found");
        }
        return new ResponseEntity<>("Updated Freelancer Successfully", HttpStatus.OK);
    }

    @GetMapping("/listAll")
    public ResponseEntity<Object> listAll() {
        return new ResponseEntity<>(freelancerService.listFreelancers(), HttpStatus.OK);
    }
}