package com.kodvix.jobportal.service;


import com.kodvix.jobportal.dao.IJobSeekerDao;

import com.kodvix.jobportal.dto.JobSeekerDTO;

import com.kodvix.jobportal.dto.JobSeekerListDTO;
import com.kodvix.jobportal.entities.JobSeeker;

import com.kodvix.jobportal.exceptions.InvalidJobSeekerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
@Transactional
public class JobSeekerServiceImpl  implements IJobSeekerService {


    @Autowired
    IJobSeekerDao jobSeekerDao;

    @Value("${resume.upload.dir}")
    private String uploadDir;



    public String storeResume(Long jobseekerId, MultipartFile file) throws IOException {
        JobSeeker jobSeeker = jobSeekerDao.findById(jobseekerId)
                .orElseThrow(() -> new RuntimeException("JobSeeker not found"));

        if (!file.getContentType().equals("application/pdf")) {
            throw new IllegalArgumentException("Only PDF files are allowed");
        }

        String fileName = "resume_" + jobseekerId + "_" + System.currentTimeMillis() + ".pdf";
        Path filePath = Paths.get(uploadDir, fileName);
        Files.createDirectories(filePath.getParent()); // ensure dir exists
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        jobSeeker.setResumePath(filePath.toString());
        jobSeekerDao.save(jobSeeker);

        return filePath.toString();
    }

    @Override
    public JobSeeker findById(Long id) {
        if (jobSeekerDao.existsById(id)) {
            return jobSeekerDao.findById(id).get();
        } else
            throw new InvalidJobSeekerException();
    }

//    @Override
//    public Long getCurrentId() {
//        return jobSeekerDao.getCurrentSeriesId();
//    }

    @Override
    public JobSeeker save(JobSeekerDTO jobSeekerDTO) {
        JobSeeker jobSeeker=new JobSeeker();
        jobSeeker.setFirstName(jobSeekerDTO.getFirstName());
        jobSeeker.setLastName(jobSeekerDTO.getLastName());
        jobSeeker.setPassword(jobSeekerDTO.getPassword());
        jobSeeker.setUserName(jobSeekerDTO.getUserName());
        if (!(jobSeekerDTO.getFirstName() == null || jobSeekerDTO.getLastName() == null
                || jobSeekerDTO.getPassword() == null || jobSeekerDTO.getUserName() == null))
            return jobSeekerDao.save(jobSeeker);
        else
            throw new InvalidJobSeekerException();
    }

    @Override
    public List<JobSeekerListDTO> listJobSeekers() {
        return jobSeekerDao.findAllJobSeekers();
    }



}
