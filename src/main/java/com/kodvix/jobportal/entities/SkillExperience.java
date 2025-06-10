package com.kodvix.jobportal.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class SkillExperience implements Serializable {

    private static final long serialVersionUID = -8047471705942110842L;

    @Id
    @Column(name = "skill_exp_id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "skill_exp_seq")
    @SequenceGenerator(name = "skill_exp_seq", sequenceName = "skill_exp_seq", allocationSize = 1)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Skill skill;

    private Integer years;

    @ManyToOne(targetEntity = Freelancer.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
            CascadeType.DETACH})
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "freelancer_id")
    private Freelancer freelancer;

    @ManyToOne(targetEntity = JobSeeker.class, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH,
            CascadeType.DETACH})
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "jobseeker_id")
    private JobSeeker jobSeeker;


    public SkillExperience() {
        super();
    }

    public SkillExperience(Long id, Skill skill, Integer years, Freelancer freelancer) {
        this.id = id;
        this.skill = skill;
        this.years = years;
        this.freelancer = freelancer;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public Integer getYears() {
        return years;
    }

    public void setYears(Integer years) {
        this.years = years;
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
