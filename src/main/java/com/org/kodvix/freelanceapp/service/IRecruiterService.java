package com.org.kodvix.freelanceapp.service;

import com.org.kodvix.freelanceapp.dto.RecruiterDTO;
import com.org.kodvix.freelanceapp.dto.RecruiterListDTO;
import com.org.kodvix.freelanceapp.entities.Recruiter;
import org.springframework.stereotype.Service;

import java.util.List;

/**************************************************************************************
 * @author       Aditya 
 * Description : This is the Service Interface for Recruiter module. 
 * Created Date: 21 April, 2021 
 * Version     : v1.0.0
 *************************************************************************************/
@Service
public interface IRecruiterService {

	Recruiter findById(Long id);

	Long getCurrentId();

	Recruiter save(RecruiterDTO recruiterdto);

	Recruiter update(Long id, RecruiterDTO recruiterDto);
	
	Recruiter findByUserName(String userName);
	
	List<RecruiterListDTO> findAll();
}
