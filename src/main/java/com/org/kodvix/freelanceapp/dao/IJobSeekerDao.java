package com.org.kodvix.freelanceapp.dao;


import com.org.kodvix.freelanceapp.dto.JobSeekerListDTO;
import com.org.kodvix.freelanceapp.entities.JobSeeker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IJobSeekerDao extends JpaRepository<JobSeeker, Long> {
    public JobSeeker findByUserName(String userName);


    @Query(value = "select new com.org.kodvix.freelanceapp.dto.FreelancerListDTO(f.id, f.userName, f.firstName, f.lastName, f.password) from Freelancer f where not f.firstName like 'dummy%' order by f.id")
    public List<JobSeekerListDTO> findAllJobSeekers();
}


