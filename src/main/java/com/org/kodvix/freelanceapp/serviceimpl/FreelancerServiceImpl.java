package com.org.kodvix.freelanceapp.serviceimpl;

import com.org.kodvix.freelanceapp.dao.IFreelancerDao;
import com.org.kodvix.freelanceapp.dto.BookmarkedFreelancerDTO;
import com.org.kodvix.freelanceapp.dto.BookmarkedFreelancerListDTO;
import com.org.kodvix.freelanceapp.dto.FreelancerDTO;
import com.org.kodvix.freelanceapp.dto.FreelancerListDTO;
import com.org.kodvix.freelanceapp.entities.BookmarkedFreelancer;
import com.org.kodvix.freelanceapp.entities.Freelancer;
import com.org.kodvix.freelanceapp.exceptions.InvalidFreelancerException;
import com.org.kodvix.freelanceapp.service.IFreelancerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class FreelancerServiceImpl implements IFreelancerService {

	@Autowired
	IFreelancerDao freelancerDao;

	@Autowired
	ModelMapper model;

	@Override
	public FreelancerDTO findById(Long id) {
		if (freelancerDao.existsById(id)) {
			return convertToDto(freelancerDao.findById(id).get());
		} else
			throw new InvalidFreelancerException();
	}


	@Override
	public Long getCurrentId() {
		return freelancerDao.getCurrentSeriesId();
	}

	@Override
	public FreelancerDTO save(FreelancerDTO freelancerDto) {
		Freelancer freelancer = convertToEntity(freelancerDto);
		if (!(freelancerDto.getFirstName() == null || freelancerDto.getLastName() == null
				|| freelancerDto.getPassword() == null || freelancerDto.getUserName() == null))
			return convertToDto(freelancerDao.save(freelancer));
		else
			throw new InvalidFreelancerException();
	}

	@Override
	public FreelancerDTO update(Long id, FreelancerDTO freelancerDto) {
		if (freelancerDao.existsById(id)) {
			Freelancer freelancer = freelancerDao.findById(id).get();
			model.map(freelancerDto,freelancer);
			return convertToDto(freelancerDao.save(freelancer));
		} else {
			throw new InvalidFreelancerException();
		}

	}

	@Override
	public FreelancerDTO findByUserName(String userName) {
		if (freelancerDao.existsByUserName(userName)) {
			return convertToDto(freelancerDao.findByUserName(userName));
		} else {
			throw new InvalidFreelancerException();
		}
	}

	@Override
	public List<FreelancerListDTO> listFreelancers() {
		return freelancerDao.findAllFreelancers();
	}


	private FreelancerDTO convertToDto(Freelancer freelancer){
		return model.map(freelancer,FreelancerDTO.class);
	}

	private Freelancer convertToEntity(FreelancerDTO freelancerDTO){
		return model.map(freelancerDTO,Freelancer.class);
	}

	private List<FreelancerDTO> convertoDtoList(List<Freelancer> freelancerList){
		return freelancerList.stream().map(this::convertToDto).collect(Collectors.toList());
	}

}
