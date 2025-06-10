package com.kodvix.jobportal.dao;

import com.kodvix.jobportal.dto.BookmarkedFreelancerListDTO;
import com.kodvix.jobportal.entities.BookmarkedFreelancer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**************************************************************************************
 * @author Vishnuvardhan 
 * Description: This is the DAO Interface for BookmarkedFreelancer module. 
 * Created Date: 19 April, 2021 
 * Version : v1.0.0
 *************************************************************************************/
@Repository
public interface IBookmarkedFreelancerDao extends JpaRepository<BookmarkedFreelancer, Long> {


	@Query(value = "select bkd_fr_seq.currval from dual", nativeQuery = true)
	Long getCurrentSeriesId();

	@Query(value="select new com.kodvix.jobportal.dto.BookmarkedFreelancerListDTO(bf.id, CONCAT(bf.freelancer.firstName, ' ', bf.freelancer.lastName) as freelancerName, bf.freelancer.userName, CONCAT(bf.bookmarkedBy.firstName, ' ', bf.bookmarkedBy.lastName) as recruiterName, bf.bookmarkedBy.userName) from BookmarkedFreelancer bf order by bf.id")
	List<BookmarkedFreelancerListDTO> findAllDTO();
}
