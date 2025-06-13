package com.kodvix.jobportal.dao;

import com.kodvix.jobportal.dto.FeedbackListDTO;
import com.kodvix.jobportal.entities.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFeedbackDao extends JpaRepository<Feedback, Long> {
    @Query("select new "
            + "com.kodvix.jobportal.dto.FeedbackListDTO(fb.id, "
            + "fb.createdFor.id, "
            + "CONCAT(fb.createdFor.firstName, ' ', fb.createdFor.lastName) as freelancerName, "
            + "fb.createdFor.userName, "
            + "fb.createdBy.id, "
            + "CONCAT(fb.createdBy.firstName, ' ', fb.createdBy.lastName), "
            + "fb.createdBy.userName, "
            + "fb.ranges, fb.comments) from Feedback fb where fb.createdFor.userName=:uId and fb.createdBy.userName=:rId order by fb.id")
    public List<FeedbackListDTO> findFeedbacksForFreelancerByRecruiterId(@Param("uId") String uId, @Param("rId") String rId);

    @Query("select AVG(fb.ranges) from Feedback fb where fb.createdFor.userName = :id")
    public Float averageRatings(@Param("id") String id);
}
