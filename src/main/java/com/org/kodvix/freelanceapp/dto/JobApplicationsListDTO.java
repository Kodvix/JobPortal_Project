package com.org.kodvix.freelanceapp.dto;

import java.time.LocalDateTime;

public class JobApplicationsListDTO {
	private Long id;
	private Long jobId;
	private String jobTitle;
	private String coverLetter;
	private Long freelancerId;
	private String freelancerName;
	private String freelancerUName;
	private Long jobseekerId;
	private String jobseekerName;
	private String JobseekerUName;

	public JobApplicationsListDTO() {
	}

	public JobApplicationsListDTO(Long id, Long jobId, String jobTitle, String coverLetter, Long freelancerId, String freelancerName, String freelancerUName) {
		this.id = id;
		this.jobId = jobId;
		this.jobTitle = jobTitle;
		this.coverLetter = coverLetter;
		this.freelancerId = freelancerId;
		this.freelancerName = freelancerName;
		this.freelancerUName = freelancerUName;
	}


	public JobApplicationsListDTO(Long id, Long jobId, String jobTitle, String coverLetter, Long freelancerId, String freelancerName, String freelancerUName, Long jobseekerId, String jobseekerName, String jobseekerUName) {
		this.id = id;
		this.jobId = jobId;
		this.jobTitle = jobTitle;
		this.coverLetter = coverLetter;
		this.freelancerId = freelancerId;
		this.freelancerName = freelancerName;
		this.freelancerUName = freelancerUName;
		this.jobseekerId = jobseekerId;
		this.jobseekerName = jobseekerName;
		this.JobseekerUName = jobseekerUName;
	}

	public JobApplicationsListDTO(Long id, Long jobId, String jobTitle, String coverLetter,
								  Long freelancerId, String freelancerName, String freelancerUName,
								  Long jobseekerId, String jobseekerName, String jobseekerUName,
								  LocalDateTime appliedDate){
		this.id = id;
		this.jobId = jobId;
		this.jobTitle = jobTitle;
		this.coverLetter = coverLetter;
		this.freelancerId = freelancerId;
		this.freelancerName = freelancerName;
		this.freelancerUName = freelancerUName;
		this.jobseekerId = jobseekerId;
		this.jobseekerName = jobseekerName;
		this.JobseekerUName = jobseekerUName;
	}




	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getCoverLetter() {
		return coverLetter;
	}

	public void setCoverLetter(String coverLetter) {
		this.coverLetter = coverLetter;
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
		return JobseekerUName;
	}

	public void setJobseekerUName(String jobseekerUName) {
		JobseekerUName = jobseekerUName;
	}
}
