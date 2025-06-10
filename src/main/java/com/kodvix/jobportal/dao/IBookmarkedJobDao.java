package com.kodvix.jobportal.dao;

import com.kodvix.jobportal.dto.BookmarkedJobListDTO;
import com.kodvix.jobportal.entities.BookmarkedJob;
import com.kodvix.jobportal.entities.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookmarkedJobDao extends JpaRepository<BookmarkedJob,Long>  
{


	
	@Query("SELECT bj FROM Job j, BookmarkedJob bj"
			+ " WHERE j.id = bj.job.id and bj.skill = :skill")
	List<BookmarkedJob> findBookmarkedJobBySkill(@Param("skill") Skill skill);
	
	List<BookmarkedJob> findBookmarkedJobsBySkillId(Long SId);
	
	/*******************************************************************************************
	 * Method:      getCurrentSeriesId
	 * @param       none
	 * @return      Long
	 * Description: This method returns the current value of primary key from the sequence.
	 *******************************************************************************************/
	@Query(value = "select bkd_job_seq.currval from dual", nativeQuery = true)
	Long getCurrentSeriesId();
	
	@Query("select new com.kodvix.jobportal.dto.BookmarkedJobListDTO(bj.id, bj.skill.id, bj.skill.name, bj.freelancer.id, CONCAT(bj.freelancer.firstName,' ', bj.freelancer.lastName) as freelancerName, bj.job.id, bj.job.jobTitle) from BookmarkedJob bj where bj.freelancer.id = :frId order by bj.id")
	List<BookmarkedJobListDTO> findAllBookmarks(@Param("frId") Long frId);
}
