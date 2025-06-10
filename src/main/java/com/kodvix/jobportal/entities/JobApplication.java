package com.kodvix.jobportal.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**************************************************************************************
 * @author       Vishnuvardhan 
 * Description : This is the Entity class for JobApplication module. 
 * Created Date: 18 April, 2021 
 * Version     : v1.0.0
 *************************************************************************************/
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(uniqueConstraints=@UniqueConstraint(columnNames = {"job_app_id", "freelancer_id","jobseeker_id"}))
public class JobApplication implements Serializable {

	private static final long serialVersionUID = -3361518011946574802L;

	@Id
	@Column(name = "job_app_id", updatable = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "job_app_seq")
	@SequenceGenerator(name = "job_app_seq", sequenceName = "job_app_seq", allocationSize = 1)
	private Long id;

	@ManyToOne(targetEntity = Job.class, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.DETACH })
	@JoinColumn(name = "job_id")
	private Job job;
	
	@ManyToOne(targetEntity = Freelancer.class, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.DETACH })
	@JoinColumn(name="freelancer_id")
	private Freelancer freelancer;

	@ManyToOne(targetEntity = JobSeeker.class, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
			CascadeType.DETACH })
	@JoinColumn(name="jobseeker_id")
	private JobSeeker jobSeeker;

	private LocalDateTime appliedDate = LocalDateTime.now();

	private String coverLetter;

	public JobApplication() {
		super();
	}

	public JobApplication(Long id, Job job, Freelancer freelancer, LocalDateTime appliedDate, String coverLetter) {
		this.id = id;
		this.job = job;
		this.freelancer = freelancer;
//		this.jobSeeker = jobSeeker;
		this.appliedDate = appliedDate;
		this.coverLetter = coverLetter;
	}

	public JobApplication(Long id, Job job, Freelancer freelancer, LocalDateTime appliedDate, String coverLetter, JobSeeker jobSeeker) {
		this.id = id;
		this.job = job;
		this.freelancer = freelancer;
		this.jobSeeker = jobSeeker;
		this.appliedDate = appliedDate;
		this.coverLetter = coverLetter;
	}



	public LocalDateTime getAppliedDate() {
		return appliedDate;
	}

	public String getCoverLetter() {
		return coverLetter;
	}

	public Long getId() {
		return id;
	}

	public Job getJob() {
		return job;
	}

	public void setAppliedDate(LocalDateTime appliedDate) {
		this.appliedDate = appliedDate;
	}

	public void setCoverLetter(String coverLetter) {
		this.coverLetter = coverLetter;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Freelancer getFreelancer() {
		return freelancer;
	}

	public void setFreelancer(Freelancer freelancer) {
		this.freelancer = freelancer;
	}

	public JobSeeker getJobSeeker() {
		return jobSeeker;
	}

	public void setJobSeeker(JobSeeker jobSeeker) {
		this.jobSeeker = jobSeeker;
	}
}
