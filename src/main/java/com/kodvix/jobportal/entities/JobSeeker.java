package com.kodvix.jobportal.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class JobSeeker implements Serializable {
    @Id
    @Column(name = "jobseeker_id", updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "jobseeker_seq")
    @SequenceGenerator(name = "jobseeker_seq", sequenceName = "jobseeker_seq", allocationSize = 1)
    private Long id;

    @Column(unique = true, nullable = false)
    private String  userName;
    @Column(nullable = false)
    private String firstName;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String resumePath;


    @OneToMany(mappedBy = "jobSeeker", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<JobApplication> appliedJobs;


    @OneToMany(mappedBy = "jobSeeker", targetEntity = SkillExperience.class, cascade = { CascadeType.PERSIST,
            CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH })
    private List<SkillExperience> skills;


    public JobSeeker() {
    }

    public JobSeeker(Long id, String userName, String firstName, String lastName, String password,List<JobApplication> appliedJobs) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.appliedJobs = appliedJobs;
    }

    public JobSeeker(Long id, String userName, String firstName, String lastName, String password) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public JobSeeker(Long id, String userName, String firstName, String lastName, String password, List<JobApplication> appliedJobs, List<SkillExperience> skills, String resumePath) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.appliedJobs = appliedJobs;
        this.skills = skills;
        this.resumePath = resumePath;
    }

    public JobSeeker(Long id, String userName, String firstName, String lastName, String password, List<JobApplication> appliedJobs, List<SkillExperience> skills) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.appliedJobs = appliedJobs;
        this.skills = skills;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<JobApplication> getAppliedJobs() {
        return appliedJobs;
    }

    public void setAppliedJobs(List<JobApplication> appliedJobs) {
        this.appliedJobs = appliedJobs;
    }

    public List<SkillExperience> getSkills() {
        return skills;
    }

    public void setSkills(List<SkillExperience> skills) {
        this.skills = skills;
    }

    public String getResumePath() {
        return resumePath;
    }

    public void setResumePath(String resumePath) {
        this.resumePath = resumePath;
    }
}


