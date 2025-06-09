package com.org.kodvix.freelanceapp.service;

import com.org.kodvix.freelanceapp.dto.BookmarkedFreelancerDTO;
import com.org.kodvix.freelanceapp.dto.BookmarkedFreelancerListDTO;
import com.org.kodvix.freelanceapp.entities.BookmarkedFreelancer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IBookmarkedFreelancerService {

	BookmarkedFreelancerDTO bookmarkFreelancer(BookmarkedFreelancerDTO bookmarkedFreelancerDto);

	void deleteBookmarkedFreelancerById(Long id);

	BookmarkedFreelancerDTO findById(Long id);
	
	List<BookmarkedFreelancerListDTO> getAll();

	Long getCurrentId();

	BookmarkedFreelancerDTO save(BookmarkedFreelancerDTO bookmarkedFreelancerDto);

}
