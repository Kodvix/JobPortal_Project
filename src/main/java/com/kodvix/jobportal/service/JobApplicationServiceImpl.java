package com.kodvix.jobportal.service;

import com.kodvix.jobportal.dao.IFreelancerDao;
import com.kodvix.jobportal.dao.IJobApplicationDao;
import com.kodvix.jobportal.dao.IJobDao;
import com.kodvix.jobportal.dto.JobApplicationDTO;
import com.kodvix.jobportal.dto.JobApplicationsListDTO;
import com.kodvix.jobportal.entities.Freelancer;
import com.kodvix.jobportal.entities.Job;
import com.kodvix.jobportal.entities.JobApplication;
import com.kodvix.jobportal.exceptions.InvalidJobApplicationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class JobApplicationServiceImpl implements IJobApplicationService {

    @Autowired
    private IJobApplicationDao jobApplicationDao;

    @Autowired
    private IJobDao jobDao;

    @Autowired
    private IFreelancerDao freelancerDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public JobApplicationDTO applyToJob(JobApplicationDTO dto) {
        if (dto.getFreelancerId() == null || dto.getJobId() == null ||
                dto.getCoverLetter() == null || dto.getCoverLetter().isBlank()) {
            throw new InvalidJobApplicationException("Freelancer ID, Job ID, or Cover Letter is missing.");
        }

        Freelancer freelancer = freelancerDao.findById(dto.getFreelancerId())
                .orElseThrow(() -> new InvalidJobApplicationException("Invalid Freelancer"));

        Job job = jobDao.findById(dto.getJobId())
                .orElseThrow(() -> new InvalidJobApplicationException("Invalid Job"));

        JobApplication jobApplication = convertToEntity(dto, freelancer, job);
        JobApplication saved = jobApplicationDao.save(jobApplication);
        return convertToDto(saved);
    }

    @Override
    public List<JobApplicationsListDTO> findAll() {
        return convertToDtoList(jobApplicationDao.findAll());
    }

    @Override
    public List<JobApplicationsListDTO> findAllByJobId(Long jobId) {
        return jobApplicationDao.findAllByJobId(jobId);
    }

    @Override
    public List<JobApplicationsListDTO> findByJobIdAndFreelancerId(Long jobId, Long freelancerId) {
        return jobApplicationDao.findByFreelancerId(jobId, freelancerId);
    }


    @Override
    public void remove(Long id) {
        if (!jobApplicationDao.existsById(id)) {
            throw new InvalidJobApplicationException("Job Application not found");
        }
        jobApplicationDao.deleteById(id);
    }

    @Override
    public JobApplicationsListDTO updateJobApplication(Long id, JobApplicationDTO dto) {
        JobApplication existing = jobApplicationDao.findById(id)
                .orElseThrow(() -> new InvalidJobApplicationException("Invalid Job Application ID"));

        modelMapper.map(dto, existing);

        Job job = jobDao.findById(dto.getJobId())
                .orElseThrow(() -> new InvalidJobApplicationException("Invalid Job"));
        existing.setJob(job);

        Freelancer freelancer = freelancerDao.findById(dto.getFreelancerId())
                .orElseThrow(() -> new InvalidJobApplicationException("Invalid Freelancer"));
        existing.setFreelancer(freelancer);

        JobApplication updated = jobApplicationDao.save(existing);
        return convertToListDto(updated);
    }

    private JobApplicationDTO convertToDto(JobApplication jobApplication) {
        JobApplicationDTO dto = modelMapper.map(jobApplication, JobApplicationDTO.class);
        dto.setFreelancerId(jobApplication.getFreelancer().getId());
        dto.setJobId(jobApplication.getJob().getId());
        return dto;
    }

    private JobApplicationsListDTO convertToListDto(JobApplication jobApplication) {
        JobApplicationsListDTO dto = modelMapper.map(jobApplication, JobApplicationsListDTO.class);
        dto.setFreelancerId(jobApplication.getFreelancer().getId());
        dto.setJobId(jobApplication.getJob().getId());
        return dto;
    }

    private JobApplication convertToEntity(JobApplicationDTO dto, Freelancer freelancer, Job job) {
        JobApplication jobApplication = modelMapper.map(dto, JobApplication.class);
        jobApplication.setFreelancer(freelancer);
        jobApplication.setJob(job);
        return jobApplication;
    }

    private List<JobApplicationsListDTO> convertToDtoList(List<JobApplication> entities) {
        return entities.stream().map(this::convertToListDto).collect(Collectors.toList());
    }
}
