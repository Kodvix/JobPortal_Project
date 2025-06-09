package com.org.kodvix.freelanceapp.service;

import com.org.kodvix.freelanceapp.dto.JobDTO;
import com.org.kodvix.freelanceapp.entities.Job;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IJobService {

	void close(Long id);

	JobDTO findById(Long id);
	
	List<Object> findJobsBySkillName(String name);
	
	List<Object> findAll();
	
	List<Object> findAllActiveJobs();
	
	JobDTO postJob(JobDTO jobDto);
	
	void awardJob(Long jobId, Long freelancerId);
}
