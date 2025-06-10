package com.kodvix.jobportal.service;

import com.kodvix.jobportal.dao.IFreelancerDao;
import com.kodvix.jobportal.dao.IJobDao;
import com.kodvix.jobportal.dao.IRecruiterDao;
import com.kodvix.jobportal.dao.ISkillDao;
import com.kodvix.jobportal.dto.JobDTO;
import com.kodvix.jobportal.dto.JobListDTO;
import com.kodvix.jobportal.entities.Freelancer;
import com.kodvix.jobportal.entities.Job;
import com.kodvix.jobportal.exceptions.InvalidJobException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * @author      Akash Sunil Kumar 
 * Description: This is a service implementation class for Job Entity
 *
 */

@Service
public class JobServiceImpl implements IJobService {

	@Autowired
	IJobDao jobdao;

	@Autowired
	ISkillDao skillDao;

	@Autowired
	IFreelancerDao freelancerDao;

	@Autowired
	IRecruiterDao recruiterDao;

	@Autowired
	ModelMapper model;

	public void close(Long id) {
		if (jobdao.existsById(id)) {
			Job job = jobdao.findById(id).get();
			job.setActive(false);
			jobdao.save(job);
		} else {
			throw new InvalidJobException();
		}
	}

	@Override
	public JobDTO findById(Long id) {

		if (jobdao.existsById(id)) {
			return convertToDto(jobdao.findById(id).get());
		} else
			throw new InvalidJobException();

	}

	@Override

	public List<Object> findJobsBySkillName(String name) {
		if (skillDao.existsByName(name)) {
			return jobdao.findBySkill(name);
		} else {
			throw new InvalidJobException();
		}

	}

	@Override
	public JobDTO postJob(JobDTO jobdto) {
		Job job = new Job();
		if (recruiterDao.existsById(jobdto.getRecruiterId()) && freelancerDao.existsById(jobdto.getFreelancerId())
				&& skillDao.existsById(jobdto.getSkillId())) {
			job.setPostedBy(recruiterDao.findById(jobdto.getRecruiterId()).get());
			job.setAwardedTo(freelancerDao.findById(2L).get());
			job.setSkill(skillDao.findById(jobdto.getSkillId()).get());
			job.setActive(true);
			job.setJobTitle(jobdto.getJobTitle());
			job.setJobDescription(jobdto.getJobDescription());
			return convertToDto(jobdao.save(job));
		} else
			throw new InvalidJobException();
	}

	@Override
	public List<Object> findAll() {
		return jobdao.findAllDTO();
	}

	@Override
	public void awardJob(Long jobId, Long freelancerId) {
		Job job = jobdao.findById(jobId).get();
		Freelancer freelancer = freelancerDao.findById(freelancerId).get();
		job.setAwardedTo(freelancer);
		jobdao.saveAndFlush(job);
		
	}

	@Override
	public List<Object> findAllActiveJobs() {
		return jobdao.findAllActiveDTO();
	}


	private JobDTO convertToDto(Job job){
		return model.map(job,JobDTO.class);
	}

	private Job convertToEntity(JobListDTO jobListDTO){
		return model.map(jobListDTO,Job.class);
	}

	private List<JobDTO> convertoDtoList(List<Job> jobList){
		return jobList.stream().map(this::convertToDto).collect(Collectors.toList());
	}

}