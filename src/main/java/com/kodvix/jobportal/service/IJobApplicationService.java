package com.kodvix.jobportal.service;

import com.kodvix.jobportal.dto.JobApplicationDTO;
import com.kodvix.jobportal.dto.JobApplicationsListDTO;
import com.kodvix.jobportal.entities.JobApplication;
import org.springframework.stereotype.Service;

import java.util.List;

/**************************************************************************************
 * @author       Siddhesh
 * Description : This is the Service Interface for JobApplication module. 
 * Created Date: 26 April, 2021 
 * Version     : v1.0.0
 *************************************************************************************/

@Service
public interface IJobApplicationService {

	JobApplication applyToJob(JobApplicationDTO jobApplicationDto);

	List<JobApplicationsListDTO> findAll();

	void remove(Long id);
	
	JobApplication updateJobApplication(Long id, JobApplicationDTO jobApplicationDto);
	
	List<JobApplicationsListDTO> findAllByJobId(Long jobId);
	
	List<JobApplicationsListDTO> findByFreelancerId(Long jobId, Long freelancerId);

	List<JobApplicationsListDTO> findByJobSeekerId(Long jobId, Long jobseekerId);

}