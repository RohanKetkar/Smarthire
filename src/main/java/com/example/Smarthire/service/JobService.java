package com.example.Smarthire.service;

import com.example.Smarthire.entity.Aplication;
import com.example.Smarthire.entity.Job;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JobService {
    List<Job> listAllJob(Long id , int size , int page);
   List<Job> filterJobs(String title, String location, Double minSalary, Double maxSalary);

    List<Job> getalljobs();
    List<Job> getaplicationofuser(Long recruiterid);
}
