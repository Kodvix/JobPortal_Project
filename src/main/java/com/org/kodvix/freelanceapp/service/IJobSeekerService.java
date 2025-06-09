package com.org.kodvix.freelanceapp.service;

import com.org.kodvix.freelanceapp.dto.FreelancerDTO;
import com.org.kodvix.freelanceapp.dto.FreelancerListDTO;
import com.org.kodvix.freelanceapp.dto.JobSeekerDTO;
import com.org.kodvix.freelanceapp.dto.JobSeekerListDTO;
import com.org.kodvix.freelanceapp.entities.Freelancer;
import com.org.kodvix.freelanceapp.entities.JobSeeker;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Service
public interface IJobSeekerService {

    JobSeeker findById(Long id);

    //Long getCurrentId();

    JobSeeker save(JobSeekerDTO jobSeekerDTOD);

    //JobSeeker update(@Valid Long id, JobSeekerDTO jobSeekerDTO);

    //JobSeeker findByUserName(String userName);

    List<JobSeekerListDTO> listJobSeekers();

    String storeResume(Long jobseekerId, MultipartFile file) throws IOException;


}
