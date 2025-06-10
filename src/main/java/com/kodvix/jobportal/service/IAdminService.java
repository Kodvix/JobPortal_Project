package com.kodvix.jobportal.service;

import com.kodvix.jobportal.dto.AdminDTO;
import org.springframework.stereotype.Service;

@Service
public interface IAdminService {
	AdminDTO findById(Long id);

	AdminDTO save(AdminDTO adminDto);

	AdminDTO update(Long id, AdminDTO adminDto);
	
	AdminDTO findByUserName(String userName);

}
