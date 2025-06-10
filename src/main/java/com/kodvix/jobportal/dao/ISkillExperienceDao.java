package com.kodvix.jobportal.dao;
import com.kodvix.jobportal.dto.SkillExperienceListDTO;
import com.kodvix.jobportal.entities.SkillExperience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISkillExperienceDao extends JpaRepository<SkillExperience, Long> {

	@Query(value = "select hibernate_id_seq.currval from dual", nativeQuery = true)
	Long getCurrentSeriesId();

	// Existing Freelancer query
	@Query("select new com.kodvix.jobportal.dto.SkillExperienceListDTO(se.id, se.skill.id, se.skill.name, se.years, f.id, CONCAT(f.firstName, ' ', f.lastName), f.userName) " +
			"from SkillExperience se, Freelancer f where f.id = :id and se.freelancer.id = :id order by se.id")
	List<SkillExperienceListDTO> findByFreelancerId(@Param("id") Long freelancerId);



	@Query("select se from SkillExperience se where se.freelancer.id = :id order by se.id")
	List<SkillExperience> findByFreelancer(@Param("id") Long freelancerId);

	@Query("select se from SkillExperience se where se.freelancer.id = :frId and se.skill.id = :sId order by se.id")
	SkillExperience findBySkillIdAndFreelancerId(@Param("sId") Long skillId, @Param("frId") Long freelancerId);
}
