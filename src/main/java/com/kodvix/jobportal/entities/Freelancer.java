package com.kodvix.jobportal.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Freelancer implements Serializable {

    private static final long serialVersionUID = -8358203589467846311L;
    @Id
    @Column(name = "freelancer_id", updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "freelancer_seq")
    @SequenceGenerator(name = "freelancer_seq", sequenceName = "freelancer_seq", allocationSize = 1)
    private Long id;

    @Column(unique = true, nullable = false)
    private String userName;

    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String password;

    @OneToMany(targetEntity = JobApplication.class)
    private List<JobApplication> appliedJobs;

    @OneToMany(mappedBy = "createdFor", targetEntity = Feedback.class, cascade = {CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private List<Feedback> feedbacks;

    @OneToMany(mappedBy = "freelancer", targetEntity = SkillExperience.class, cascade = {CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private List<SkillExperience> skills;

    @OneToMany(mappedBy = "freelancer", targetEntity = BookmarkedJob.class, cascade = {CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private List<BookmarkedJob> bookmarkedJobs;

    public Freelancer() {
        super();
    }

    public Freelancer(String userName, String firstName, String lastName, String password,
                      List<JobApplication> appliedJobs, List<Feedback> feedbacks, List<SkillExperience> skills,
                      List<BookmarkedJob> bookmarkedJobs) {
        super();
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.appliedJobs = appliedJobs;
        this.feedbacks = feedbacks;
        this.skills = skills;
        this.bookmarkedJobs = bookmarkedJobs;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<JobApplication> getAppliedJobs() {
        return appliedJobs;
    }

    public List<BookmarkedJob> getBookmarkedJobs() {
        return bookmarkedJobs;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public String getFirstName() {
        return firstName;
    }

    public Long getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public List<SkillExperience> getSkills() {
        return skills;
    }

    public void setAppliedJobs(List<JobApplication> appliedJobs) {
        this.appliedJobs = appliedJobs;
    }

    public void setBookmarkedJobs(List<BookmarkedJob> bookmarkedJobs) {
        this.bookmarkedJobs = bookmarkedJobs;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSkills(List<SkillExperience> skills) {
        this.skills = skills;
    }

}
