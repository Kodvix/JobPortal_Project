package com.org.kodvix.freelanceapp.serviceimpl;

import com.org.kodvix.freelanceapp.dao.IFreelancerDao;
import com.org.kodvix.freelanceapp.dao.IJobSeekerDao;
import com.org.kodvix.freelanceapp.dao.ISkillDao;
import com.org.kodvix.freelanceapp.dao.ISkillExperienceDao;
import com.org.kodvix.freelanceapp.dto.SkillExperienceDTO;
import com.org.kodvix.freelanceapp.dto.SkillExperienceListDTO;
import com.org.kodvix.freelanceapp.entities.SkillExperience;
import com.org.kodvix.freelanceapp.exceptions.InvalidSkillExperienceException;
import com.org.kodvix.freelanceapp.service.ISkillExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
	IJobSeekerDao jobseekerDao;

	@Override
	public SkillExperience addSkill(SkillExperienceDTO skillExperienceDto) {
		SkillExperience skillExperience = new SkillExperience();
		skillExperience.setYears(skillExperienceDto.getYears());
		skillExperience.setFreelancer(freelancerDao.findById(skillExperienceDto.getFreelancerId()).get());
		skillExperience.setJobSeeker(jobseekerDao.findById(skillExperienceDto.getJobseekerId()).get());
		skillExperience.setSkill(skillDao.findById(skillExperienceDto.getSkillId()).get());
		return skillExperienceDao.save(skillExperience);
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
	public SkillExperience getSkillById(Long id) {
		if (skillExperienceDao.existsById(id)) {
			return skillExperienceDao.findById(id).get();
		} else {
			throw new InvalidSkillExperienceException();
		}
	}

	@Override
	public List<SkillExperience> getByFreelancer(Long id) {
		if (freelancerDao.existsById(id)) {
			return skillExperienceDao.findByFreelancer(id);
		} else
			throw new InvalidSkillExperienceException();
	}
}