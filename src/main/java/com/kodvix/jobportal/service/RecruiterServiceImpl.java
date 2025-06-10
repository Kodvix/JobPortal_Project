package com.kodvix.jobportal.service;

import com.kodvix.jobportal.dao.IRecruiterDao;
import com.kodvix.jobportal.dto.RecruiterDTO;
import com.kodvix.jobportal.dto.RecruiterListDTO;
import com.kodvix.jobportal.entities.Recruiter;
import com.kodvix.jobportal.exceptions.InvalidRecruiterException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RecruiterServiceImpl implements IRecruiterService {

    @Autowired
    IRecruiterDao recruiterDao;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public RecruiterDTO findById(Long id) {
        if (recruiterDao.existsById(id)) {
            Recruiter recruiter = recruiterDao.findById(id).get();
            return mapToDTO(recruiter);
        } else {
            throw new InvalidRecruiterException();
        }
    }

    @Override
    public Long getCurrentId() {
        return recruiterDao.getCurrentSeriesId();
    }

    @Override
    public Recruiter save(RecruiterDTO recruiterDto) {
        if (recruiterDto.getFirstName() != null && recruiterDto.getLastName() != null
                && recruiterDto.getUserName() != null && recruiterDto.getPassword() != null) {
            Recruiter recruiter = mapToEntity(recruiterDto);
            return recruiterDao.save(recruiter);
        } else {
            throw new InvalidRecruiterException();
        }
    }


    @Override
    public Recruiter update(Long id, RecruiterDTO recruiterDto) {
        if (recruiterDao.existsById(id)) {
            Recruiter recruiter = mapToEntity(recruiterDto);
            recruiter.setId(id);
            return recruiterDao.save(recruiter);
        } else {
            throw new InvalidRecruiterException();
        }
    }

    private Recruiter mapToEntity(RecruiterDTO dto) {
        return modelMapper.map(dto, Recruiter.class);
    }

    private RecruiterDTO mapToDTO(Recruiter entity) {
        return modelMapper.map(entity, RecruiterDTO.class);
    }

    @Override
    public Recruiter findByUserName(String userName) {
        if (recruiterDao.existsByUserName(userName)) {
            return recruiterDao.findByUserName(userName);
        } else {
            throw new InvalidRecruiterException();
        }
    }

    @Override
    public List<RecruiterListDTO> findAll() {
        List<Recruiter> recruiters = recruiterDao.findAll();
        return recruiters.stream()
                .map(recruiter -> modelMapper.map(recruiter, RecruiterListDTO.class))
                .collect(Collectors.toList());
    }

}
