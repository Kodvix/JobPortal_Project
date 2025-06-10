package com.kodvix.jobportal.service;

import com.kodvix.jobportal.dto.JobSeekerDTO;
import com.kodvix.jobportal.dto.JobSeekerListDTO;
import com.kodvix.jobportal.entities.JobSeeker;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
