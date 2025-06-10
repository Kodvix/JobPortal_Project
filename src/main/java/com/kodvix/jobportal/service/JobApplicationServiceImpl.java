package com.kodvix.jobportal.service;

import com.kodvix.jobportal.dao.IFreelancerDao;
import com.kodvix.jobportal.dao.IJobApplicationDao;
import com.kodvix.jobportal.dao.IJobDao;
import com.kodvix.jobportal.dao.IJobSeekerDao;
import com.kodvix.jobportal.dto.JobApplicationDTO;
import com.kodvix.jobportal.dto.JobApplicationsListDTO;
import com.kodvix.jobportal.entities.JobApplication;
import com.kodvix.jobportal.exceptions.InvalidJobApplicationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class JobApplicationServiceImpl implements IJobApplicationService {

	@Autowired
	IJobApplicationDao jobApplicationDao;

	@Autowired
	IJobDao jobDao;

	@Autowired
	IFreelancerDao freelancerDao;

	@Autowired
	IJobSeekerDao jobSeekerDao;

	@Override
	public JobApplication applyToJob(JobApplicationDTO jobApplicationDto) {
		JobApplication jobApplication = new JobApplication();

		if ((jobApplicationDto.getFreelancerId() != null) ||
				!jobApplicationDto.getCoverLetter().isEmpty() ||
				jobApplicationDto.getJobId() != null ||
				jobApplicationDto.getJobseekerId() != null) {

			jobApplication.setCoverLetter(jobApplicationDto.getCoverLetter());
			jobApplication.setFreelancer(freelancerDao.findById(jobApplicationDto.getFreelancerId()).get());
			jobApplication.setJob(jobDao.findById(jobApplicationDto.getJobId()).get());
			jobApplication.setJobSeeker(jobSeekerDao.findById(jobApplicationDto.getJobseekerId()).get());

			return jobApplicationDao.save(jobApplication);
		} else {
			throw new InvalidJobApplicationException();
		}
	}

	@Override
	public List<JobApplicationsListDTO> findAll() {
		return jobApplicationDao.findAllApps();
	}

	@Override
	public void remove(Long id) {
		if (jobApplicationDao.existsById(id)) {
			jobApplicationDao.deleteById(id);
		} else {
			throw new InvalidJobApplicationException();
		}
	}

	@Override
	public JobApplication updateJobApplication(Long id, JobApplicationDTO jobApplicationDto) {
		if (jobApplicationDao.existsById(id)) {
			JobApplication jobApplication = jobApplicationDao.findById(id).get();
			jobApplication.setCoverLetter(jobApplicationDto.getCoverLetter());
			jobApplication.setJob(jobDao.findById(jobApplicationDto.getJobId()).get());
			return jobApplicationDao.save(jobApplication);
		} else {
			throw new InvalidJobApplicationException();
		}
	}

	@Override
	public List<JobApplicationsListDTO> findAllByJobId(Long jobId) {
		return jobApplicationDao.findAllByJobId(jobId);
	}

	@Override
	public List<JobApplicationsListDTO> findByFreelancerId(Long jobId, Long freelancerId) {
		return jobApplicationDao.findByFreelancerId(jobId, freelancerId);
	}

	@Override
	public List<JobApplicationsListDTO> findByJobSeekerId(Long jobId, Long jobseekerId) {
		return jobApplicationDao.findByJobSeekerId(jobId, jobseekerId);
	}
}
