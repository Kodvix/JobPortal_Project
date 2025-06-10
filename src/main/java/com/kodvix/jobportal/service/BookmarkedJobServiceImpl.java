package com.kodvix.jobportal.service;

import com.kodvix.jobportal.dao.IBookmarkedJobDao;
import com.kodvix.jobportal.dao.IFreelancerDao;
import com.kodvix.jobportal.dao.IJobDao;
import com.kodvix.jobportal.dao.ISkillDao;
import com.kodvix.jobportal.dto.BookmarkedJobDTO;
import com.kodvix.jobportal.dto.BookmarkedJobListDTO;
import com.kodvix.jobportal.entities.BookmarkedJob;
import com.kodvix.jobportal.entities.Skill;
import com.kodvix.jobportal.exceptions.InvalidBookmarkedJobException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class BookmarkedJobServiceImpl implements IBookmarkedJobService {
    @Autowired
    IBookmarkedJobDao bookmarkedjobdao;
    @Autowired
    ISkillDao skilldao;
    @Autowired
    IFreelancerDao freelancerdao;
    @Autowired
    IJobDao jobdao;

    @Autowired
    ModelMapper model;

    /**
     * Method     : bookmarkedJob
     *
     * @param bookmarkedjobdto
     * @return BookmarkedJob object
     * Description: The method saves the bookmarkedjob object.
     * @throws InvalidBookmarkedJobException
     */

    @Transactional
    public BookmarkedJobDTO bookmarkJob(BookmarkedJobDTO bookmarkedjobdto) {
        BookmarkedJob bookmarkedJob = new BookmarkedJob();

        if (jobdao.existsById(bookmarkedjobdto.getJobId()) &&
                freelancerdao.existsById(bookmarkedjobdto.getFreelancerId()) &&
                skilldao.existsById(bookmarkedjobdto.getSkillId())) {
            bookmarkedJob.setSkill(skilldao.findById(bookmarkedjobdto.getSkillId()).get());
            bookmarkedJob.setFreelancer(freelancerdao.findById(bookmarkedjobdto.getFreelancerId()).get());
            bookmarkedJob.setJob(jobdao.findById(bookmarkedjobdto.getJobId()).get());

            return convertToDto(bookmarkedjobdao.save(bookmarkedJob));
        } else {
            throw new InvalidBookmarkedJobException();
        }
    }

    @Override
    @Transactional
    public List<BookmarkedJobDTO> findBookmarkedJobsBySkillName(String name) {
        if (skilldao.existsByName(name)) {
            Skill skill = skilldao.findByName(name);
            return convertoDtoList(bookmarkedjobdao.findBookmarkedJobBySkill(skill));
        } else throw new InvalidBookmarkedJobException();
    }


    @Transactional
    @Override
    public BookmarkedJobDTO findById(Long id) {
        if (bookmarkedjobdao.existsById(id)) {

            return convertToDto(bookmarkedjobdao.findById(id).get());
        } else {
            throw new InvalidBookmarkedJobException();
        }
    }

    @Override
    public Long getCurrentId() {
        return bookmarkedjobdao.getCurrentSeriesId();
    }

    @Transactional
    public void remove(Long BId) {
        if (bookmarkedjobdao.existsById(BId)) {

            BookmarkedJob bj = bookmarkedjobdao.findById(BId).get();
            bookmarkedjobdao.delete(bj);
        } else {
            throw new InvalidBookmarkedJobException();
        }
    }


    @Override
    public List<BookmarkedJobListDTO> findAllBookmarks(Long frId) {
        return bookmarkedjobdao.findAllBookmarks(frId);
    }


    private BookmarkedJobDTO convertToDto(BookmarkedJob bookmarkedJob) {
        return model.map(bookmarkedJob, BookmarkedJobDTO.class);
    }

    private BookmarkedJob convertToEntity(BookmarkedJobListDTO bookmarkedJobListDT) {
        return model.map(bookmarkedJobListDT, BookmarkedJob.class);
    }

    private List<BookmarkedJobDTO> convertoDtoList(List<BookmarkedJob> bookmarkedJobList) {
        return bookmarkedJobList.stream().map(this::convertToDto).collect(Collectors.toList());
    }


}
