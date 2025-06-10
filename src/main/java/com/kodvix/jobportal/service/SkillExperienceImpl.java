package com.kodvix.jobportal.service;

import com.kodvix.jobportal.dao.IFreelancerDao;
import com.kodvix.jobportal.dao.IJobSeekerDao;
import com.kodvix.jobportal.dao.ISkillDao;
import com.kodvix.jobportal.dao.ISkillExperienceDao;
import com.kodvix.jobportal.dto.SkillExperienceDTO;
import com.kodvix.jobportal.dto.SkillExperienceListDTO;
import com.kodvix.jobportal.entities.JobSeeker;
import com.kodvix.jobportal.entities.SkillExperience;
import com.kodvix.jobportal.exceptions.InvalidSkillExperienceException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SkillExperienceImpl implements ISkillExperienceService {

    @Autowired
    ISkillExperienceDao skillExperienceDao;

    @Autowired
    ISkillDao skillDao;

    @Autowired
    IFreelancerDao freelancerDao;

    @Autowired
    IJobSeekerDao jobSeekerDao;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public SkillExperienceDTO addSkill(SkillExperienceDTO skillExperienceDto) {
        SkillExperience exp = new SkillExperience();
        exp.setYears(skillExperienceDto.getYears());

        exp.setFreelancer(freelancerDao.findById(skillExperienceDto.getFreelancerId()).get());
        exp.setSkill(skillDao.findById(skillExperienceDto.getSkillId()).get());

        if (skillExperienceDto.getJobseekerId() != null) {
            exp.setJobSeeker(jobSeekerDao.findById(skillExperienceDto.getJobseekerId())
                    .orElseThrow(() -> new RuntimeException("JobSeeker not found")));
        }

        SkillExperience added = skillExperienceDao.save(exp);
        return modelMapper.map(added, SkillExperienceDTO.class);
    }


    @Override
    public SkillExperienceDTO getSkillById(Long id) {
        if (skillExperienceDao.existsById(id)) {
            SkillExperience skillExperience = skillExperienceDao.findById(id).get();
            return modelMapper.map(skillExperience, SkillExperienceDTO.class);
        } else {
            throw new InvalidSkillExperienceException();
        }
    }

    @Override
    public List<SkillExperienceListDTO> getSkillByFreelancerId(Long id) {
        if (freelancerDao.existsById(id)) {
            return skillExperienceDao.findByFreelancerId(id);
        } else
            throw new InvalidSkillExperienceException();
    }

    @Override
    public void updateSkillYears(Long id, Long freelancerId, Integer years) {
        if (skillExperienceDao.existsById(id)) {
            SkillExperience skillExperience = skillExperienceDao.findBySkillIdAndFreelancerId(id, freelancerId);
            skillExperience.setYears(years);
            skillExperienceDao.save(skillExperience);
        } else
            throw new InvalidSkillExperienceException();
    }

    @Override
    public List<SkillExperience> getByFreelancer(Long id) {
        if (freelancerDao.existsById(id)) {
            return skillExperienceDao.findByFreelancer(id);
        } else
            throw new InvalidSkillExperienceException();
    }

    @Override
    public List<SkillExperienceListDTO> getSkillByJobSeekerId(Long id) {
        if (jobSeekerDao.existsById(id)) {
            return convertToDTOList(skillExperienceDao.findByJobSeekerId(id));
        } else {
            throw new InvalidSkillExperienceException();
        }
    }

    @Override
    public List<SkillExperience> getByJobSeeker(Long id) {
        if (jobSeekerDao.existsById(id)) {
            JobSeeker jobSeeker = jobSeekerDao.findById(id).orElseThrow(InvalidSkillExperienceException::new);
            return skillExperienceDao.findByJobSeeker(jobSeeker);
        } else {
            throw new InvalidSkillExperienceException();
        }
    }

    private List<SkillExperienceListDTO> convertToDTOList(List<SkillExperience> entities) {
        return entities.stream()
                .map(entity -> modelMapper.map(entity, SkillExperienceListDTO.class))
                .collect(Collectors.toList());
    }


}