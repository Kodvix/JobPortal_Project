package com.org.kodvix.freelanceapp.serviceimpl;

import com.org.kodvix.freelanceapp.dao.IBookmarkedJobDao;
import com.org.kodvix.freelanceapp.dao.IFreelancerDao;
import com.org.kodvix.freelanceapp.dao.IJobDao;
import com.org.kodvix.freelanceapp.dao.ISkillDao;
import com.org.kodvix.freelanceapp.dto.BookmarkedFreelancerDTO;
import com.org.kodvix.freelanceapp.dto.BookmarkedFreelancerListDTO;
import com.org.kodvix.freelanceapp.dto.BookmarkedJobDTO;
import com.org.kodvix.freelanceapp.dto.BookmarkedJobListDTO;
import com.org.kodvix.freelanceapp.entities.BookmarkedFreelancer;
import com.org.kodvix.freelanceapp.entities.BookmarkedJob;
import com.org.kodvix.freelanceapp.entities.Skill;
import com.org.kodvix.freelanceapp.exceptions.InvalidBookmarkedJobException;
import com.org.kodvix.freelanceapp.service.IBookmarkedJobService;
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
	 *
	 * Method     : bookmarkedJob
	 * @param       bookmarkedjobdto
	 * @throws InvalidBookmarkedJobException
	 * @return      BookmarkedJob object
	 * Description: The method saves the bookmarkedjob object.
	 *
	 */

	@Transactional
	public BookmarkedJobDTO bookmarkJob(BookmarkedJobDTO bookmarkedjobdto)
	{
		BookmarkedJob bookmarkedJob=new BookmarkedJob();

		if (jobdao.existsById(bookmarkedjobdto.getJobId()) &&
				freelancerdao.existsById(bookmarkedjobdto.getFreelancerId())&&
				skilldao.existsById(bookmarkedjobdto.getSkillId())) {
			bookmarkedJob.setSkill(skilldao.findById(bookmarkedjobdto.getSkillId()).get());
			bookmarkedJob.setFreelancer(freelancerdao.findById(bookmarkedjobdto.getFreelancerId()).get());
			bookmarkedJob.setJob(jobdao.findById(bookmarkedjobdto.getJobId()).get());

			return convertToDto(bookmarkedjobdao.save(bookmarkedJob));
		}

		else
		{
			throw new InvalidBookmarkedJobException();
		}
	}

	@Override
	@Transactional
	public List<BookmarkedJobDTO> findBookmarkedJobsBySkillName(String name) {
		if(skilldao.existsByName(name)) {
			Skill skill = skilldao.findByName(name);
			return convertoDtoList(bookmarkedjobdao.findBookmarkedJobBySkill(skill));
		}else throw new InvalidBookmarkedJobException();
	}



	@Transactional
	@Override
	public BookmarkedJobDTO findById(Long id) {
		if(bookmarkedjobdao.existsById(id))
		{

			return convertToDto( bookmarkedjobdao.findById(id).get());
		}
		else
		{
			throw new InvalidBookmarkedJobException();
		}
	}

	@Override
	public Long getCurrentId() {
		return bookmarkedjobdao.getCurrentSeriesId();
	}

	@Transactional
	public void remove(Long BId)
	{
		if(bookmarkedjobdao.existsById(BId))
		{

			BookmarkedJob bj=bookmarkedjobdao.findById(BId).get();
			bookmarkedjobdao.delete(bj);
		}
		else
		{
			throw new InvalidBookmarkedJobException();
		}
	}


	@Override
	public List<BookmarkedJobListDTO> findAllBookmarks(Long frId) {
		return bookmarkedjobdao.findAllBookmarks(frId);
	}




	private BookmarkedJobDTO convertToDto(BookmarkedJob bookmarkedJob){
		return model.map(bookmarkedJob,BookmarkedJobDTO.class);
	}

	private BookmarkedJob convertToEntity(BookmarkedJobListDTO bookmarkedJobListDT){
		return model.map(bookmarkedJobListDT,BookmarkedJob.class);
	}

	private List<BookmarkedJobDTO> convertoDtoList(List<BookmarkedJob> bookmarkedJobList){
		return bookmarkedJobList.stream().map(this::convertToDto).collect(Collectors.toList());
	}




}
