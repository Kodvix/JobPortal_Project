package com.kodvix.jobportal.service;

import com.kodvix.jobportal.dao.IFreelancerDao;
import com.kodvix.jobportal.dao.IJobDao;
import com.kodvix.jobportal.dao.IRecruiterDao;
import com.kodvix.jobportal.dao.ISkillDao;
import com.kodvix.jobportal.dto.JobDTO;
import com.kodvix.jobportal.entities.Freelancer;
import com.kodvix.jobportal.entities.Job;
import com.kodvix.jobportal.exceptions.InvalidJobException;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

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
    ModelMapper modelMapper;

    @PostConstruct
    public void initModelMapper() {
        modelMapper.addMappings(new PropertyMap<JobDTO, Job>() {
            @Override
            protected void configure() {
                map().setId(source.getId());
            }
        });
    }


    @Override
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
        if (!jobdao.existsById(id)) {
            throw new InvalidJobException();
        }
        Job job = jobdao.findById(id).get();
        return modelMapper.map(job, JobDTO.class);
    }

    @Override
    public List<Object> findJobsBySkillName(String name) {
        if (!skillDao.existsByName(name)) {
            throw new InvalidJobException();
        }
        return (List<Object>) jobdao.findBySkill(name);
    }

    @Override
    public JobDTO postJob(JobDTO jobDto) {
        if (recruiterDao.existsById(jobDto.getRecruiterId()) &&
                freelancerDao.existsById(jobDto.getFreelancerId()) &&
                skillDao.existsById(jobDto.getSkillId())) {

            Job job = mapToEntity(jobDto);
            return mapToDTO(jobdao.save(job));
        } else {
            throw new InvalidJobException();
        }
    }


    private Job mapToEntity(JobDTO dto) {
        return modelMapper.map(dto, Job.class);
    }

    private JobDTO mapToDTO(Job job) {
        return modelMapper.map(job, JobDTO.class);
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
}
