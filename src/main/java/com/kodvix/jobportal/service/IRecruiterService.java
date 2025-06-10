package com.kodvix.jobportal.service;

import com.kodvix.jobportal.dto.RecruiterDTO;
import com.kodvix.jobportal.dto.RecruiterListDTO;
import com.kodvix.jobportal.entities.Recruiter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IRecruiterService {

    RecruiterDTO findById(Long id);

    Long getCurrentId();

    Recruiter save(RecruiterDTO recruiterdto);

    Recruiter update(Long id, RecruiterDTO recruiterDto);

    Recruiter findByUserName(String userName);

    List<RecruiterListDTO> findAll();
}
