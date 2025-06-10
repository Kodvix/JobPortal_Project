package com.kodvix.jobportal.dto;

import javax.validation.constraints.NotNull;

/**************************************************************************************
 * @author       Amruth N
 * Description : This is the DTO class for SkillExperience module. 
 * Created Date: 22 April, 2021 
 * Version     : v1.0.0
 *************************************************************************************/
public class SkillExperienceDTO {
	@NotNull(message = "Experience cannot be empty")
	private Integer years;
	@NotNull(message = "Skill Id cannot be blank")
	private Long skillId;
	@NotNull(message = "Freelancer Id cannot be blank")
	private Long freelancerId;

	@NotNull(message = "JobSeeker Id cannot be blank")
	private Long jobseekerId;



	public SkillExperienceDTO() {
		super();
	}

	public SkillExperienceDTO(Integer years, Long skillId, Long freelancerId) {
		this.years = years;
		this.skillId = skillId;
		this.freelancerId = freelancerId;

	}

	public SkillExperienceDTO(Integer years, Long skillId, Long freelancerId, Long jobseekerId) {
		this.years = years;
		this.skillId = skillId;
		this.freelancerId = freelancerId;
		this.jobseekerId = jobseekerId;
	}

	public @NotNull(message = "Experience cannot be empty") Integer getYears() {
		return years;
	}

	public void setYears(@NotNull(message = "Experience cannot be empty") Integer years) {
		this.years = years;
	}

	public @NotNull(message = "Skill Id cannot be blank") Long getSkillId() {
		return skillId;
	}

	public void setSkillId(@NotNull(message = "Skill Id cannot be blank") Long skillId) {
		this.skillId = skillId;
	}

	public @NotNull(message = "Freelancer Id cannot be blank") Long getFreelancerId() {
		return freelancerId;
	}

	public void setFreelancerId(@NotNull(message = "Freelancer Id cannot be blank") Long freelancerId) {
		this.freelancerId = freelancerId;
	}

	public @NotNull(message = "JobSeeker Id cannot be blank") Long getJobseekerId() {
		return jobseekerId;
	}

	public void setJobseekerId(@NotNull(message = "JobSeeker Id cannot be blank") Long jobseekerId) {
		this.jobseekerId = jobseekerId;
	}
}