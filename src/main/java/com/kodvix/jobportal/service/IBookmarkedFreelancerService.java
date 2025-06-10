package com.kodvix.jobportal.service;

import com.kodvix.jobportal.dto.BookmarkedFreelancerDTO;
import com.kodvix.jobportal.dto.BookmarkedFreelancerListDTO;
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
