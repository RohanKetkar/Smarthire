package com.example.Smarthire.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;


    private String username ;

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public void setAplications(List<Aplication> aplications) {
        this.aplications = aplications;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public List<Aplication> getAplications() {
        return aplications;
    }

    private String email;

    private String password;

    public User() {

    }

    @Enumerated(EnumType.STRING)
    private UserRole role;
    public User(Long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedat() {
        return createdat;
    }

    private LocalDateTime createdat;

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCreatedat (LocalDateTime time) {
        this.createdat = time;
    }
    // when i will is i do userRepository.save(user) hibernate automaticaly insert the timestamp after creating query and before inserting in table
    @PrePersist
    protected void onCreate(){
        createdat = LocalDateTime.now();
    }
// One is recruiter is i can create many jobs // It is will is i have onetomany one recruiter many job one job one recruiter

@JsonIgnore
    @OneToMany(mappedBy="recruiter" , cascade=CascadeType.ALL , orphanRemoval=true)
    private List<Job> jobs;
    // One is candidate is i can apply to many aplication why not one candidate can apply to many job because it will happen manytomany in candidate job
    @JsonIgnore
    @OneToMany(mappedBy="candidate" , cascade=CascadeType.ALL , orphanRemoval=true)
    private List<Aplication> aplications;
    public User(String username){
        this.username = username;
    }
}
