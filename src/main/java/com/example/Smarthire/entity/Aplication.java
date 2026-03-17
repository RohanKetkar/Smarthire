package com.example.Smarthire.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="aplications")
public class Aplication {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private Long userid;
    private Long jobid;


    private String resumeurl;

    private LocalDateTime cretedat;
    public Aplication(int id, Long userid, Long jobid, String resumeurl, AplicationStatus status) {
        this.id = id;
        this.userid = userid;
        this.jobid = jobid;
        this.resumeurl = resumeurl;
        this.status = status;
    }

    @Enumerated(EnumType.STRING)
    private AplicationStatus status;

    public Aplication() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getJobid() {
        return jobid;
    }

    public void setJobid(Long jobid) {
        this.jobid = jobid;
    }

    public String getResumeurl() {
        return resumeurl;
    }

    public void setResumeurl(String resumeurl) {
        this.resumeurl = resumeurl;
    }

    public AplicationStatus getStatus() {
        return status;
    }

    public void setStatus(AplicationStatus status) {
        this.status = status;
    }

    @PrePersist


    protected void onCreate(){
        cretedat = LocalDateTime.now();
    }

    public LocalDateTime getCretedat() {
        return cretedat;
    }

    public void setCretedat(LocalDateTime cretedat) {
        this.cretedat = cretedat;
    }

    public User getCandidate() {
        return candidate;
    }

    public void setCandidate(User candidate) {
        this.candidate = candidate;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    @ManyToOne
    @JoinColumn(name="candidate_id")
    private User candidate;

    @ManyToOne
    @JoinColumn(name="job_id")
    private Job job;
}
