package com.kodvix.jobportal.controller;

import com.kodvix.jobportal.dao.IJobSeekerDao;
import com.kodvix.jobportal.dto.JobSeekerDTO;
import com.kodvix.jobportal.entities.JobSeeker;
import com.kodvix.jobportal.exceptions.InvalidFreelancerException;
import com.kodvix.jobportal.exceptions.InvalidJobSeekerException;
import com.kodvix.jobportal.exceptions.JobPortalValidationException;
import com.kodvix.jobportal.service.IJobSeekerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/jobseeker")
@CrossOrigin(origins = "*")
public class JobSeekerController {

    @Autowired
    IJobSeekerService jobSeekerService;

    @Autowired
    IJobSeekerDao jobSeekerDao;


    @PostMapping("/{id}/upload-resume")
    public ResponseEntity<String> uploadResume(
            @PathVariable Long id,
            @RequestParam("file") MultipartFile file) {
        try {
            String path = jobSeekerService.storeResume(id, file);
            return ResponseEntity.ok("Resume uploaded: " + path);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Upload failed: " + e.getMessage());
        }
    }


    @GetMapping("/{id}/resume")
    public ResponseEntity<UrlResource> downloadResume(@PathVariable Long id) throws IOException {
        JobSeeker jobSeeker = jobSeekerDao.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found"));

        Path path = Paths.get(jobSeeker.getResumePath());
        UrlResource resource = new UrlResource(path.toUri());

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + path.getFileName())
                .body(resource);
    }


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
    public JobSeekerDTO getById(@PathVariable Long id) {
        try {
            return jobSeekerService.findById(id);
        } catch (InvalidJobSeekerException exception) {
            throw new InvalidFreelancerException("JobSeeker with given id not found.");
        }
    }
}