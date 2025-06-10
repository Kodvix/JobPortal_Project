package com.kodvix.jobportal.service;

import com.kodvix.jobportal.dto.SkillExperienceDTO;
import com.kodvix.jobportal.dto.SkillExperienceListDTO;
import com.kodvix.jobportal.entities.SkillExperience;

import java.util.List;


/**************************************************************************************
 * @author       Amruth N 
 * Description : This is the Service Interface for SkillExperience module. 
 * Created Date: 21 April, 2021 
 * Version     : v1.0.0
 *************************************************************************************/
public interface ISkillExperienceService {

	public SkillExperience addSkill(SkillExperienceDTO skillExperienceDto);

	public List<SkillExperienceListDTO> getSkillByFreelancerId(Long id);
	
	public SkillExperience getSkillById(Long id);
	
	public List<SkillExperience> getByFreelancer(Long id);

	public void updateSkillYears(Long id, Long freelancerId, Integer years);
}