package com.kodvix.jobportal.controller;

import com.kodvix.jobportal.dto.AdminDTO;
import com.kodvix.jobportal.exceptions.InvalidAdminException;
import com.kodvix.jobportal.exceptions.JobPortalValidationException;
import com.kodvix.jobportal.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author    Akash Sunil Kumar
 * Description: This class is used as the controller for the Admin module 
 * Created Date: 18 April, 2021 
 * Version : v1.0.0
 */
@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200")

public class AdminController {

	@Autowired
	IAdminService adminService;



	@PostMapping("/save")
	public ResponseEntity<Object> adminSave(@Valid @RequestBody AdminDTO adminDto, BindingResult bindingResult) {
		System.out.println(adminDto.toString());
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

			return new ResponseEntity<>(adminService.save(adminDto), HttpStatus.ACCEPTED);
		} catch (InvalidAdminException exception) {
			throw new InvalidAdminException("Could not save new Admin");
		}

	}


	@PutMapping("/update/{id}")
	public ResponseEntity<Object> adminUpdate(@PathVariable Long id, @RequestBody AdminDTO adminDto) {

		try {
			adminService.update(id, adminDto);
			return new ResponseEntity<>("Admin Successfully", HttpStatus.ACCEPTED);

		} catch (InvalidAdminException exception) {
			throw new InvalidAdminException("Admin with given id not found");
		}
	}



	@GetMapping(value = "/find/id/{id}")
	public AdminDTO findById(@PathVariable Long id) {
		try {
			return adminService.findById(id);
		} catch (InvalidAdminException exception) {
			throw new InvalidAdminException("Id not found");
		}
	}



	@GetMapping(value = "/find/name/{userName}")
	public AdminDTO findByUserName(@PathVariable String userName) {
		try {
			return adminService.findByUserName(userName);
		} catch (InvalidAdminException exception) {
			throw new InvalidAdminException("Admin with userName not found");
		}
	}
}