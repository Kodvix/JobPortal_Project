package com.kodvix.jobportal.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class JobApplicationDTO {
	@NotNull(message = "Job ID cannot be blank")
	private Long jobId;
	@NotEmpty(message = "Cover Letter cannot be empty")
	private String coverLetter;
	
	private Long freelancerId;

	private Long jobseekerId;
	
	public JobApplicationDTO() {
		super();
	}

	public JobApplicationDTO(Long jobId, String coverLetter, Long freelancerId,Long jobseekerId) {
		super();
		this.jobId = jobId;
		this.coverLetter = coverLetter;
		this.freelancerId = freelancerId;
		this.jobseekerId=jobseekerId;
	}

	public String getCoverLetter() {
		return coverLetter;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setCoverLetter(String coverLetter) {
		this.coverLetter = coverLetter;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public Long getFreelancerId() {
		return freelancerId;
	}

	public void setFreelancerId(Long freelancerId) {
		this.freelancerId = freelancerId;
	}

	public Long getJobseekerId() {
		return jobseekerId;
	}

	public void setJobseekerId(Long jobseekerId) {
		this.jobseekerId = jobseekerId;
	}

	@Override
	public String toString() {
		return "JobApplicationDTO{" +
				"jobId=" + jobId +
				", coverLetter='" + coverLetter + '\'' +
				", freelancerId=" + freelancerId +
				", jobseekerId=" + jobseekerId +
				'}';
	}
}
