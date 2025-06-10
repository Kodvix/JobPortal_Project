package com.kodvix.jobportal.dto;

public class JobDTO {

    private Long id;
    private String jobTitle;
    private String jobDescription;

    private Long skillId;
    private String skillName;

    private Long recruiterId;
    private String recruiterName;

    private Long awardedToId;

    private Long freelancerId;

    private Boolean active;

    public JobDTO() {
    }

    public JobDTO(Long id, String jobTitle, String jobDescription,
                  Long skillId, String skillName,
                  Long recruiterId, String recruiterName,
                  Long awardedToId, Long freelancerId, Boolean active) {
        this.id = id;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.skillId = skillId;
        this.skillName = skillName;
        this.recruiterId = recruiterId;
        this.recruiterName = recruiterName;
        this.awardedToId = awardedToId;
        this.freelancerId = freelancerId;
        this.active = active;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
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

    public Long getRecruiterId() {
        return recruiterId;
    }

    public void setRecruiterId(Long recruiterId) {
        this.recruiterId = recruiterId;
    }

    public String getRecruiterName() {
        return recruiterName;
    }

    public void setRecruiterName(String recruiterName) {
        this.recruiterName = recruiterName;
    }

    public Long getAwardedToId() {
        return awardedToId;
    }

    public void setAwardedToId(Long awardedToId) {
        this.awardedToId = awardedToId;
    }

    public Long getFreelancerId() {
        return freelancerId;
    }

    public void setFreelancerId(Long freelancerId) {
        this.freelancerId = freelancerId;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
