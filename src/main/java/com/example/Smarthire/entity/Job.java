package com.example.Smarthire.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="jobs")
public class Job {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;


    private Long salary;

    private String location;
    private double experience;

    public Job() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String tile) {
        this.title = tile;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setExperience(double experience) {
        this.experience = experience;
    }

    public void setCreatedat(LocalDateTime createdat) {
        this.createdat = createdat;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Long getSalary() {
        return salary;
    }

    public String getLocation() {
        return location;
    }

    public double getExperience() {
        return experience;
    }

    public LocalDateTime getCreatedat() {
        return createdat;
    }

    public Job(Long id, String tile, String description, Long salary, String location, double experience, LocalDateTime createdat) {
        this.id = id;
        this.title = tile;
        this.description = description;
        this.salary = salary;
        this.location = location;
        this.experience = experience;
        this.createdat = createdat;
    }

    private LocalDateTime createdat;


    private enum status {
        OPEN,
        CLOSE
    }
    @PrePersist
    protected void Oncreate(){
        createdat = LocalDateTime.now();
    }

    public User getRecruiter() {
        return recruiter;
    }

    public void setRecruiter(User recruiter) {
        this.recruiter = recruiter;
    }

    public List<Aplication> getAplications() {
        return aplications;
    }

    public void setAplications(List<Aplication> aplications) {
        this.aplications = aplications;
    }

    @ManyToOne
    @JoinColumn(name="recruiter_id")
    private User recruiter;

    @JsonIgnore
    @OneToMany(mappedBy="job" , cascade=CascadeType.ALL , orphanRemoval=true)
    private List<Aplication> aplications;
}
