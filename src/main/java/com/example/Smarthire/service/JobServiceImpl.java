package com.example.Smarthire.service;

import com.example.Smarthire.entity.Job;
import com.example.Smarthire.entity.User;
import com.example.Smarthire.repository.JobRepository;
import com.example.Smarthire.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service

public class JobServiceImpl implements JobService{
    public UserRepository userRepository;
    public JobRepository jobRepository;
    public JobServiceImpl(UserRepository userRepository , JobRepository jobRepository){
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
    }
    @Override
    public List<Job> listAllJob(Long id , int page , int size) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            User useri = user.get();
            Pageable pageable = PageRequest.of(page, size);
            Page<Job> jobPage = jobRepository.findAll(pageable);
            return jobPage.getContent();
        }
        return new ArrayList<Job>();
    }

    @Override
    public List<Job> filterJobs(String title, String location, Double minSalary, Double maxSalary) {
        if(title == null && location == null && minSalary == null && maxSalary == null){
            return jobRepository.findAll();
        }
        return jobRepository.filterJobs(title, location, minSalary, maxSalary);
    }

    @Override
    public List<Job> getalljobs() {
        List<Job> jobs = jobRepository.getAllJobs();
        return jobs;
    }

    @Override
    public List<Job> getaplicationofuser(Long recruiterid) {
        return jobRepository.getjobbyrecruiterid(recruiterid);
    }

}
