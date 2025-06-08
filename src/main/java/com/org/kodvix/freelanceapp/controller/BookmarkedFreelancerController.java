package com.org.kodvix.freelanceapp.controller;

import com.org.kodvix.freelanceapp.dto.BookmarkedFreelancerDTO;
import com.org.kodvix.freelanceapp.entities.BookmarkedFreelancer;
import com.org.kodvix.freelanceapp.exceptions.InvalidBookmarkedFreelancerException;
import com.org.kodvix.freelanceapp.exceptions.JobPortalValidationException;
import com.org.kodvix.freelanceapp.service.IBookmarkedFreelancerService;
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
 * @author Vishnuvardhan 
 * Description: This is the rest controller class for BookmarkedFreelancer module. 
 * Created Date: 20 April, 2021 
 * Version : v1.0.0
 *************************************************************************************/
@RestController
@RequestMapping("/bmark/freelancer")
@CrossOrigin(origins = "*")
public class BookmarkedFreelancerController {

	@Autowired
    IBookmarkedFreelancerService bookmarkedFreelancerService;

	@PostMapping("/add")
	public ResponseEntity<Object> createBookmark(@Valid @RequestBody BookmarkedFreelancerDTO bookmarkedFreelancerDto,
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
			bookmarkedFreelancerService.bookmarkFreelancer(bookmarkedFreelancerDto);
		} catch (InvalidBookmarkedFreelancerException exception) {
			throw new InvalidBookmarkedFreelancerException("One or more entered fields contain invalid objects.");
		}
		return new ResponseEntity<>("Added successfully", HttpStatus.OK);
	}


	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteById(@PathVariable Long id) {
		try {
			bookmarkedFreelancerService.deleteBookmarkedFreelancerById(id);
		} catch (InvalidBookmarkedFreelancerException exception) {
			throw new InvalidBookmarkedFreelancerException("No bookmark with specified id exists.");
		}
		return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
	}


	@GetMapping("/get/id/{id}")
	public ResponseEntity<Object> getById(@Valid @PathVariable Long id) {
		try {
			BookmarkedFreelancerDTO bookmarkedFreelancerDTO = bookmarkedFreelancerService.findById(id);
			return new ResponseEntity<>(bookmarkedFreelancerDTO, HttpStatus.OK);
		} catch (InvalidBookmarkedFreelancerException exception) {
			throw new InvalidBookmarkedFreelancerException("No Bookmark with specified id.");
		}
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<Object> getAll(){
		return new ResponseEntity<>(bookmarkedFreelancerService.getAll(), HttpStatus.OK);
	}
}
