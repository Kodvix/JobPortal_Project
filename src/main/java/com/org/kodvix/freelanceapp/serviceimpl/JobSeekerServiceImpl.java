package com.org.kodvix.freelanceapp.serviceimpl;


import com.org.kodvix.freelanceapp.dao.IJobSeekerDao;

import com.org.kodvix.freelanceapp.dto.FreelancerListDTO;
import com.org.kodvix.freelanceapp.dto.JobSeekerDTO;

import com.org.kodvix.freelanceapp.dto.JobSeekerListDTO;
import com.org.kodvix.freelanceapp.entities.JobSeeker;

import com.org.kodvix.freelanceapp.exceptions.InvalidJobSeekerException;
import com.org.kodvix.freelanceapp.service.IJobSeekerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class JobSeekerServiceImpl  implements IJobSeekerService {


    @Autowired
    IJobSeekerDao jobSeekerDao;

    @Override
    public JobSeeker findById(Long id) {
        if (jobSeekerDao.existsById(id)) {
            return jobSeekerDao.findById(id).get();
        } else
            throw new InvalidJobSeekerException();
    }

//    @Override
//    public Long getCurrentId() {
//        return jobSeekerDao.getCurrentSeriesId();
//    }

    @Override
    public JobSeeker save(JobSeekerDTO jobSeekerDTO) {
        JobSeeker jobSeeker=new JobSeeker();
        jobSeeker.setFirstName(jobSeekerDTO.getFirstName());
        jobSeeker.setLastName(jobSeekerDTO.getLastName());
        jobSeeker.setPassword(jobSeekerDTO.getPassword());
        jobSeeker.setUserName(jobSeekerDTO.getUserName());
        if (!(jobSeekerDTO.getFirstName() == null || jobSeekerDTO.getLastName() == null
                || jobSeekerDTO.getPassword() == null || jobSeekerDTO.getUserName() == null))
            return jobSeekerDao.save(jobSeeker);
        else
            throw new InvalidJobSeekerException();
    }

    @Override
    public List<JobSeekerListDTO> listJobSeekers() {
        return jobSeekerDao.findAllJobSeekers();
    }



}
