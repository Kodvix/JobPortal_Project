package com.org.kodvix.freelanceapp.dto;

public class FeedbackDTO {

	private Long id;
	private Integer ranges;
	private String comments;
	private Long createdById;        // Recruiter ID
	private String recruiterUname;   // Recruiter username
	private Long createdForId;       // Freelancer ID
	private String freelancerUname;  // Freelancer username

	public FeedbackDTO() {}

	public FeedbackDTO(Long id, Integer ranges, String comments,
					   Long createdById, String recruiterUname,
					   Long createdForId, String freelancerUname) {
		this.id = id;
		this.ranges = ranges;
		this.comments = comments;
		this.createdById = createdById;
		this.recruiterUname = recruiterUname;
		this.createdForId = createdForId;
		this.freelancerUname = freelancerUname;
	}

	public Long getId() {
		return id;
	}

	public Integer getRanges() {
		return ranges;
	}

	public String getComments() {
		return comments;
	}

	public Long getCreatedById() {
		return createdById;
	}

	public String getRecruiterUname() {
		return recruiterUname;
	}

	public Long getCreatedForId() {
		return createdForId;
	}

	public String getFreelancerUname() {
		return freelancerUname;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setRanges(Integer ranges) {
		this.ranges = ranges;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public void setCreatedById(Long createdById) {
		this.createdById = createdById;
	}

	public void setRecruiterUname(String recruiterUname) {
		this.recruiterUname = recruiterUname;
	}

	public void setCreatedForId(Long createdForId) {
		this.createdForId = createdForId;
	}

	public void setFreelancerUname(String freelancerUname) {
		this.freelancerUname = freelancerUname;
	}
}
