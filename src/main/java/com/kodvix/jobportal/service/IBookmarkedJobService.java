package com.kodvix.jobportal.service;

import com.kodvix.jobportal.dto.BookmarkedJobDTO;
import com.kodvix.jobportal.dto.BookmarkedJobListDTO;
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
