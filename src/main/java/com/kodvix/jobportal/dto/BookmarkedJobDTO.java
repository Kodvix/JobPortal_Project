package com.kodvix.jobportal.dto;

import javax.validation.constraints.NotNull;

public class BookmarkedJobDTO {

    private Long id;

    @NotNull(message = "Freelancer ID cannot be null")
    private Long freelancerId;

    @NotNull(message = "Job ID cannot be null")
    private Long jobId;

    @NotNull(message = "Skill ID cannot be null")
    private Long skillId;

    public BookmarkedJobDTO() {
        super();
    }

    public BookmarkedJobDTO(Long id, Long freelancerId, Long jobId, Long skillId) {
        this.id = id;
        this.freelancerId = freelancerId;
        this.jobId = jobId;
        this.skillId = skillId;
    }

    public Long getId() {
        return id;
    }

    public Long getFreelancerId() {
        return freelancerId;
    }

    public Long getJobId() {
        return jobId;
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFreelancerId(Long freelancerId) {
        this.freelancerId = freelancerId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }
}
