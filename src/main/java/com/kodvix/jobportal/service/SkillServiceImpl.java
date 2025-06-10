package com.kodvix.jobportal.service;

import com.kodvix.jobportal.dao.ISkillDao;
import com.kodvix.jobportal.dto.SkillDTO;
import com.kodvix.jobportal.entities.Skill;
import com.kodvix.jobportal.exceptions.DuplicateSkillException;
import com.kodvix.jobportal.exceptions.InvalidSkillException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**************************************************************************************
 * Description : This is the Service Implementation for Skill module.
 *************************************************************************************/
@Service
@Transactional
public class SkillServiceImpl implements ISkillService {

    @Autowired
    ISkillDao skillDao;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public SkillDTO findById(Long id) {
        Skill skill = skillDao.findById(id).get();
        return convertToDTO(skill);
    }

    @Override
    public List<SkillDTO> getAllSkills() {
        return skillDao.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }

    private SkillDTO convertToDTO(Skill skill) {
        return modelMapper.map(skill, SkillDTO.class);
    }


    @Override
    public SkillDTO getCurrentId(Long id) {
        Skill skill = skillDao.findById(id).get();
        return modelMapper.map(skill, SkillDTO.class);
    }

    @Override
    public Skill getSkill(Long id) {
        return skillDao.getOne(id);
    }

    @Override
    public String remove(Long id) {
        if (skillDao.existsById(id)) {
            skillDao.deleteById(id);
            return "Deleted";
        } else {
            throw new InvalidSkillException();
        }
    }

    @Override
    public Skill save(Skill skill) {
        return skillDao.save(skill);
    }

    public SkillDTO save(SkillDTO skillDto) {
        //Skill skill = new Skill();
        if (skillDao.existsByName(skillDto.getName())) {
            throw new DuplicateSkillException();
        } else {
            Skill skill = mapToEntity(skillDto);
            return mapToDTO(skillDao.save(skill));
        }
    }

    private Skill mapToEntity(SkillDTO skillDto) {
        return modelMapper.map(skillDto, Skill.class);
    }

    private SkillDTO mapToDTO(Skill skill) {
        return modelMapper.map(skill, SkillDTO.class);
    }

    @Override
    public SkillDTO update(Long id, SkillDTO skillDto) {
        if (skillDao.existsById(id)) {
            //skill.setId(id);
            //return skillDao.save(skill);
            Skill skill = mapToEntity(skillDto);
            skill.setId(id);
            Skill updated = skillDao.save(skill);
            return mapToDTO(updated);
        } else {
            throw new InvalidSkillException();
        }
    }

}
