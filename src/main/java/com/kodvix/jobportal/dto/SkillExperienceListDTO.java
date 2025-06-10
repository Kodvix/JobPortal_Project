package com.kodvix.jobportal.dto;

public class SkillExperienceListDTO {
	private Long id;
	private Long skillId;
	private String skillName;
	private Integer experience;
	private Long freelancerId;
	private String freelancerName;
	private String freelancerUName;

	private Long jobseekerId;
	private String jobseekerName;
	private String jobseekerUName;



	public SkillExperienceListDTO() {
	}

	public SkillExperienceListDTO(Long id, Long skillId, String skillName, Integer experience, Long freelancerId, String freelancerName, String freelancerUName) {
		this.id = id;
		this.skillId = skillId;
		this.skillName = skillName;
		this.experience = experience;
		this.freelancerId = freelancerId;
		this.freelancerName = freelancerName;
		this.freelancerUName = freelancerUName;
	}

	public SkillExperienceListDTO(Long id, Long skillId, String skillName, Integer experience, Long freelancerId, String freelancerName, String freelancerUName, Long jobseekerId, String jobseekerName, String jobseekerUName) {
		this.id = id;
		this.skillId = skillId;
		this.skillName = skillName;
		this.experience = experience;
		this.freelancerId = freelancerId;
		this.freelancerName = freelancerName;
		this.freelancerUName = freelancerUName;
		this.jobseekerId = jobseekerId;
		this.jobseekerName = jobseekerName;
		this.jobseekerUName = jobseekerUName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getSkillId() {
		return skillId;
	}

	public void setSkillId(Long skillId) {
		this.skillId = skillId;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public Integer getExperience() {
		return experience;
	}

	public void setExperience(Integer experience) {
		this.experience = experience;
	}

	public Long getFreelancerId() {
		return freelancerId;
	}

	public void setFreelancerId(Long freelancerId) {
		this.freelancerId = freelancerId;
	}

	public String getFreelancerName() {
		return freelancerName;
	}

	public void setFreelancerName(String freelancerName) {
		this.freelancerName = freelancerName;
	}

	public String getFreelancerUName() {
		return freelancerUName;
	}

	public void setFreelancerUName(String freelancerUName) {
		this.freelancerUName = freelancerUName;
	}


	public Long getJobseekerId() {
		return jobseekerId;
	}

	public void setJobseekerId(Long jobseekerId) {
		this.jobseekerId = jobseekerId;
	}

	public String getJobseekerName() {
		return jobseekerName;
	}

	public void setJobseekerName(String jobseekerName) {
		this.jobseekerName = jobseekerName;
	}

	public String getJobseekerUName() {
		return jobseekerUName;
	}

	public void setJobseekerUName(String jobseekerUName) {
		this.jobseekerUName = jobseekerUName;
	}

	@Override
	public String toString() {
		return "SkillExperienceListDTO{" +
				"id=" + id +
				", skillId=" + skillId +
				", skillName='" + skillName + '\'' +
				", experience=" + experience +
				", freelancerId=" + freelancerId +
				", freelancerName='" + freelancerName + '\'' +
				", freelancerUName='" + freelancerUName + '\'' +
				", jobseekerId=" + jobseekerId +
				", jobseekerName='" + jobseekerName + '\'' +
				", jobseekerUName='" + jobseekerUName + '\'' +
				'}';
	}
}
