package com.kodvix.jobportal.service;

import com.kodvix.jobportal.dto.FreelancerDTO;
import com.kodvix.jobportal.dto.FreelancerListDTO;
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
