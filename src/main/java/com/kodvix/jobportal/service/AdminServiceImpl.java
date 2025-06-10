package com.kodvix.jobportal.service;

import com.kodvix.jobportal.dao.IAdminDao;
import com.kodvix.jobportal.dto.AdminDTO;
import com.kodvix.jobportal.entities.Admin;
import com.kodvix.jobportal.exceptions.InvalidAdminException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class
AdminServiceImpl implements IAdminService {

    @Autowired
    IAdminDao adminDao;
    @Autowired
    ModelMapper modelMapper;

    @Override
    public AdminDTO findById(Long id) {
        if (adminDao.existsById(id)) {
            return convertToDto(adminDao.findById(id).orElseThrow());
        } else {
            throw new InvalidAdminException();
        }

    }

    @Override
    public AdminDTO save(AdminDTO adminDto) {

        if (!(adminDto.getUserName() == null ||
                adminDto.getFirstName() == null ||
                adminDto.getLastName() == null ||
                adminDto.getPassword() == null)) {
            Admin admin = new Admin();
            admin = convertToEntity(adminDto);
            return convertToDto(adminDao.save(admin));
        } else
            throw new InvalidAdminException();


    }

    @Override
    public AdminDTO update(Long id, AdminDTO adminDto) {

        if (adminDao.existsById(id)) {
            Admin existingAdmin = adminDao.findById(id).get();
            modelMapper.map(adminDto, existingAdmin);
            Admin updatedAdmin = adminDao.save(existingAdmin);
            return convertToDto(updatedAdmin);

        } else {
            throw new InvalidAdminException();
        }

    }

    @Override
    public AdminDTO findByUserName(String userName) {
        if (adminDao.existsByUserName(userName)) {
            return convertToDto(adminDao.findByUserName(userName));
        } else {
            throw new InvalidAdminException();
        }
    }


    private AdminDTO convertToDto(Admin admin) {
        return modelMapper.map(admin, AdminDTO.class);
    }

    private Admin convertToEntity(AdminDTO adminDTO) {
        return modelMapper.map(adminDTO, Admin.class);
    }

}
