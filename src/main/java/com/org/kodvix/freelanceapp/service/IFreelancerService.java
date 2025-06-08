package com.org.kodvix.freelanceapp.service;

import com.org.kodvix.freelanceapp.dto.FreelancerDTO;
import com.org.kodvix.freelanceapp.dto.FreelancerListDTO;
import com.org.kodvix.freelanceapp.entities.Freelancer;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;


@Service
public interface IFreelancerService {

	FreelancerDTO findById(Long id);

	Long getCurrentId();

	FreelancerDTO save(FreelancerDTO freelancerDto);

	FreelancerDTO update(@Valid Long id, FreelancerDTO freelancerDto);

	FreelancerDTO findByUserName(String userName);
	
	List<FreelancerListDTO> listFreelancers();
}
