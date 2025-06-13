package com.kodvix.jobportal.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class
Feedback implements Serializable {

    private static final long serialVersionUID = -4183773630437671124L;
    @Id
    @Column(name = "feedback_id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "feedback_seq")
    @SequenceGenerator(name = "feedback_seq", sequenceName = "feedback_seq", allocationSize = 1)
    Long id;
    Integer ranges;
    String comments;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Recruiter.class)
    @JoinColumn(name = "recruiter_id")
    Recruiter createdBy;

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Freelancer.class)
    @JoinColumn(name = "freelancer_id")
    Freelancer createdFor;

    public Feedback() {
        super();
    }

    public Feedback(Integer ranges, String comments, Recruiter createdBy, Freelancer createdFor) {
        super();
        this.ranges = ranges;
        this.comments = comments;
        this.createdBy = createdBy;
        this.createdFor = createdFor;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getComments() {
        return comments;
    }

    public Recruiter getCreatedBy() {
        return createdBy;
    }

    public Freelancer getCreatedFor() {
        return createdFor;
    }

    public Long getId() {
        return id;
    }

    public Integer getRanges() {
        return ranges;
    }

    public void setComment(String comments) {
        this.comments = comments;
    }

    public void setCreatedBy(Recruiter createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedFor(Freelancer createdFor) {
        this.createdFor = createdFor;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRanges(Integer ranges) {
        this.ranges = ranges;
    }

}
