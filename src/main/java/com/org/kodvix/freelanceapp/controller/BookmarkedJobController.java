package com.org.kodvix.freelanceapp.controller;

import com.org.kodvix.freelanceapp.dto.BookmarkedJobDTO;
import com.org.kodvix.freelanceapp.entities.BookmarkedJob;
import com.org.kodvix.freelanceapp.exceptions.InvalidBookmarkedJobException;
import com.org.kodvix.freelanceapp.exceptions.JobPortalValidationException;
import com.org.kodvix.freelanceapp.service.IBookmarkedJobService;
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
@RequestMapping("/bmark/job")
@CrossOrigin(origins = "*")
public class BookmarkedJobController {
	@Autowired
    IBookmarkedJobService bookmarkedJobService;


	@PostMapping("/add")
	public ResponseEntity<Object> createBookmark(@Valid @RequestBody BookmarkedJobDTO bookmarkedjobdto,
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
			bookmarkedJobService.bookmarkJob(bookmarkedjobdto);
		} catch (InvalidBookmarkedJobException exception) {
			throw new InvalidBookmarkedJobException("One or more entered fields contain invalid objects.");
		}
		return new ResponseEntity<>("Added successfully", HttpStatus.OK);

	}



	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteById(@PathVariable Long id) {
		try {
			bookmarkedJobService.remove(id);
		} catch (InvalidBookmarkedJobException exception) {
			throw new InvalidBookmarkedJobException("No bookmark with specified id exists.");
		}
		return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
	}



	@GetMapping("/get/id/{id}")
	public ResponseEntity<Object> getById(@Valid @PathVariable Long id) {
		try {
			BookmarkedJobDTO bookmarkedJobDTO = bookmarkedJobService.findById(id);
			return new ResponseEntity<>(bookmarkedJobDTO, HttpStatus.OK);
		} catch (InvalidBookmarkedJobException exception) {
			throw new InvalidBookmarkedJobException("No Bookmark with specified id.");
		}
	}



	@GetMapping("/get/skill/{skillName}")
	public List<BookmarkedJobDTO> listJobsBySkill(@Valid @PathVariable String skillName) {
		try {
			List<BookmarkedJobDTO> bookmarkedJobDTOS = bookmarkedJobService.findBookmarkedJobsBySkillName(skillName);
			return bookmarkedJobDTOS;
		} catch (InvalidBookmarkedJobException exception) {
			throw new InvalidBookmarkedJobException("No bookmarks found for the specified skill name");
		}
	}

	@GetMapping("findAll/{frId}")
	public ResponseEntity<Object> findAll(@PathVariable Long frId) {
		System.out.println(frId);
		return new ResponseEntity<>(bookmarkedJobService.findAllBookmarks(frId), HttpStatus.OK);
	}

}
