package com.kodvix.jobportal.service;

import com.kodvix.jobportal.dto.SkillDTO;
import com.kodvix.jobportal.entities.Skill;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ISkillService {

    SkillDTO findById(Long id);

    List<SkillDTO> getAllSkills();

    SkillDTO getCurrentId(Long id);

    Skill getSkill(Long id);

    String remove(Long id);

    Skill save(Skill skill);

    SkillDTO save(SkillDTO skillDto);

    SkillDTO update(Long id, SkillDTO skillDto);

}
