package com.kodvix.jobportal.service;

import com.kodvix.jobportal.dto.SkillExperienceDTO;
import com.kodvix.jobportal.dto.SkillExperienceListDTO;
import com.kodvix.jobportal.entities.SkillExperience;

import java.util.List;

public interface ISkillExperienceService {

    public SkillExperienceDTO addSkill(SkillExperienceDTO skillExperienceDto);

    public List<SkillExperienceListDTO> getSkillByFreelancerId(Long id);

    public SkillExperienceDTO getSkillById(Long id);

    public List<SkillExperience> getByFreelancer(Long id);

    public void updateSkillYears(Long id, Long freelancerId, Integer years);

    public List<SkillExperienceListDTO> getSkillByJobSeekerId(Long id);

    public List<SkillExperience> getByJobSeeker(Long id);
}