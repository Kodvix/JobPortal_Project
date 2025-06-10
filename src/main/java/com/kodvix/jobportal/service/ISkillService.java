package com.kodvix.jobportal.service;

import com.kodvix.jobportal.dto.SkillDTO;
import com.kodvix.jobportal.entities.Skill;
import org.springframework.stereotype.Service;

import java.util.List;

/**************************************************************************************
 * @author       Amruth N 
 * Description : This is the Service Interface for SkillService module. 
 * Created Date: 21 April, 2021 
 * Version     : v1.0.0
 *************************************************************************************/
@Service
public interface ISkillService {

	Skill findById(Long id);

	List<Skill> getAllSkills();

	Long getCurrentId();

	Skill getSkill(Long id);

	String remove(Long id);

	Skill save(Skill skill);

	Skill save(SkillDTO skillDto);

	Skill update(Long id, Skill skill);

}
