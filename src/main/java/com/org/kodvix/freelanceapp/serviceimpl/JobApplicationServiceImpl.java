package com.org.kodvix.freelanceapp.serviceimpl;

import com.org.kodvix.freelanceapp.dao.IFreelancerDao;
import com.org.kodvix.freelanceapp.dao.IJobApplicationDao;
import com.org.kodvix.freelanceapp.dao.IJobDao;
import com.org.kodvix.freelanceapp.dao.IJobSeekerDao;
import com.org.kodvix.freelanceapp.dto.JobApplicationDTO;
import com.org.kodvix.freelanceapp.dto.JobApplicationsListDTO;
import com.org.kodvix.freelanceapp.entities.JobApplication;
import com.org.kodvix.freelanceapp.exceptions.InvalidJobApplicationException;
import com.org.kodvix.freelanceapp.service.IJobApplicationService;
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
