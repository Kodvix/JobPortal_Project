package com.org.kodvix.freelanceapp.service;

import com.org.kodvix.freelanceapp.dto.BookmarkedJobDTO;
import com.org.kodvix.freelanceapp.dto.BookmarkedJobListDTO;
import com.org.kodvix.freelanceapp.entities.BookmarkedJob;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public interface IBookmarkedJobService {

	BookmarkedJobDTO bookmarkJob(BookmarkedJobDTO bjd);

	List<BookmarkedJobDTO> findBookmarkedJobsBySkillName(String name);

	BookmarkedJobDTO findById(Long id);
	
	List<BookmarkedJobListDTO> findAllBookmarks(Long frId);

	Long getCurrentId();
	
	void remove(Long BId);

}
