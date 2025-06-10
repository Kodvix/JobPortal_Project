package com.kodvix.jobportal.service;

import com.kodvix.jobportal.dao.IBookmarkedFreelancerDao;
import com.kodvix.jobportal.dao.IFreelancerDao;
import com.kodvix.jobportal.dao.IRecruiterDao;
import com.kodvix.jobportal.dao.ISkillDao;
import com.kodvix.jobportal.dto.BookmarkedFreelancerDTO;
import com.kodvix.jobportal.dto.BookmarkedFreelancerListDTO;
import com.kodvix.jobportal.entities.BookmarkedFreelancer;
import com.kodvix.jobportal.exceptions.InvalidBookmarkedFreelancerException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**************************************************************************************
 * @author Vishnuvardhan
 * Description : This is the Service Implementation for BookmarkedFreelancer module.	 
 * Created Date: 21 April, 2021 
 * Version     : v1.0.0
 *************************************************************************************/
@Service
@Transactional
public class BookmarkedFreelancerServiceImpl implements IBookmarkedFreelancerService {

    @Autowired
    IBookmarkedFreelancerDao bookmarkedFreelancerDao;

    @Autowired
    ISkillDao skillDao;

    @Autowired
    IFreelancerDao freelancerDao;

    @Autowired
    IRecruiterDao recruiterDao;

    @Autowired
    private ModelMapper model;

    @Override
    public BookmarkedFreelancerDTO bookmarkFreelancer(BookmarkedFreelancerDTO bookmarkedFreelancerDto) {

        BookmarkedFreelancer bookmarkedFreelancer = new BookmarkedFreelancer();

        if (recruiterDao.existsById(bookmarkedFreelancerDto.getRecruiterId())
                && freelancerDao.existsById(bookmarkedFreelancerDto.getFreelancerId())) {

            bookmarkedFreelancer.setBookmarkedBy(recruiterDao.findById(bookmarkedFreelancerDto.getRecruiterId()).get());
            bookmarkedFreelancer.setFreelancer(freelancerDao.findById(bookmarkedFreelancerDto.getFreelancerId()).get());

            return convertToDto(bookmarkedFreelancerDao.save(bookmarkedFreelancer));
        } else
            throw new InvalidBookmarkedFreelancerException();

    }


    @Override
    public void deleteBookmarkedFreelancerById(Long id) {
        if (bookmarkedFreelancerDao.existsById(id)) {
            bookmarkedFreelancerDao.deleteById(id);
        } else {
            throw new InvalidBookmarkedFreelancerException();
        }

    }


    @Override
    public BookmarkedFreelancerDTO findById(Long id) {
        if (bookmarkedFreelancerDao.existsById(id)) {
            return convertToDto(bookmarkedFreelancerDao.findById(id).get());
        } else
            throw new InvalidBookmarkedFreelancerException();

    }


    @Override
    public Long getCurrentId() {
        return bookmarkedFreelancerDao.getCurrentSeriesId();
    }

    @Override
    public BookmarkedFreelancerDTO save(BookmarkedFreelancerDTO bookmarkedFreelancerDto) {
        BookmarkedFreelancer bookmarkedFreelancer = new BookmarkedFreelancer();
        bookmarkedFreelancer.setBookmarkedBy(recruiterDao.findById(bookmarkedFreelancerDto.getRecruiterId()).get());
        bookmarkedFreelancer.setFreelancer(freelancerDao.findById(bookmarkedFreelancerDto.getFreelancerId()).get());
        return convertToDto(bookmarkedFreelancerDao.save(bookmarkedFreelancer));
    }

    @Override
    public List<BookmarkedFreelancerListDTO> getAll() {
        return bookmarkedFreelancerDao.findAllDTO();
    }

    private BookmarkedFreelancerDTO convertToDto(BookmarkedFreelancer bookFreelancer) {
        return model.map(bookFreelancer, BookmarkedFreelancerDTO.class);
    }

    private BookmarkedFreelancer convertToEntity(BookmarkedFreelancerListDTO bookFreelancer) {
        return model.map(bookFreelancer, BookmarkedFreelancer.class);
    }

    private List<BookmarkedFreelancerDTO> convertoDtoList(List<BookmarkedFreelancer> bookFreelancerList) {
        return bookFreelancerList.stream().map(this::convertToDto).collect(Collectors.toList());
    }
}
