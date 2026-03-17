package com.example.Smarthire.controller;

import com.example.Smarthire.entity.Job;
import com.example.Smarthire.service.JobService;
import com.example.Smarthire.service.JobServiceImpl;
import com.example.Smarthire.service.UserService;
import com.example.Smarthire.service.UserServiceImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins="http://localhost:5173/")
@RestController
@RequestMapping("/jobs/v1")


public class JobController {

    private JobService jobService;
    public JobController(JobServiceImpl jobServiceImpl){
        this.jobService = jobServiceImpl;
    }
    @GetMapping("/{id}")
    public List<Job> getalljobs(@PathVariable Long id , @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "5") int size){
        Pageable pageable = PageRequest.of(page, size);
        return jobService.listAllJob(id,page,size);
    }
    @GetMapping("/filter")
    public List<Job> filterJobs(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String location,
            @RequestParam(required = false) Double minSalary,
            @RequestParam(required = false) Double maxSalary
    ) {

        return jobService.filterJobs(title, location, minSalary, maxSalary);
    }
    @GetMapping("/getalljobs")
    public List <Job> getalljobs(){
        return jobService.getalljobs();
    }
    @GetMapping("/getaplicationofuser/{recruiterid}")
    public List<Job> getaplicationofuser(@PathVariable Long recruiterid){
        System.out.println("iugeriqwbiqwbneqwneqw");
        return jobService.getaplicationofuser(recruiterid);
    }
}
