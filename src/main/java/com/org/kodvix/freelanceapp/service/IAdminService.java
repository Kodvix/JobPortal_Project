package com.org.kodvix.freelanceapp.service;

import com.org.kodvix.freelanceapp.dto.AdminDTO;
import com.org.kodvix.freelanceapp.entities.Admin;
import org.springframework.stereotype.Service;

@Service
public interface IAdminService {
	AdminDTO findById(Long id);

	AdminDTO save(AdminDTO adminDto);

	AdminDTO update(Long id, AdminDTO adminDto);
	
	AdminDTO findByUserName(String userName);

}
