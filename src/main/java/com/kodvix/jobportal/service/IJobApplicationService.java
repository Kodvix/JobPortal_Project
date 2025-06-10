package com.kodvix.jobportal.service;

import com.kodvix.jobportal.dto.JobApplicationDTO;
import com.kodvix.jobportal.dto.JobApplicationsListDTO;

import java.util.List;

public interface IJobApplicationService {
    JobApplicationDTO applyToJob(JobApplicationDTO jobApplicationDto);

    List<JobApplicationsListDTO> findAll();

    List<JobApplicationsListDTO> findAllByJobId(Long jobId);

    List<JobApplicationsListDTO> findByJobIdAndFreelancerId(Long jobId, Long freelancerId);

    JobApplicationsListDTO updateJobApplication(Long id, JobApplicationDTO jobApplicationDto);

    void remove(Long id);
}
