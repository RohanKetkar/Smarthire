package com.example.Smarthire.service;

import com.example.Smarthire.entity.*;
import com.example.Smarthire.repository.AplicationRepository;
import com.example.Smarthire.repository.JobRepository;
import com.example.Smarthire.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AplicationServiceImpl implements AplicationService {

 private AplicationRepository aplicationRepository;
 private UserRepository userRepository;
 private JobRepository jobRepository;
 public AplicationServiceImpl(AplicationRepository aplicationrepository , UserRepository userRepository , JobRepository jobRepository){
     this.aplicationRepository = aplicationrepository;
     this.userRepository = userRepository;
     this.jobRepository = jobRepository;
 }



    @Override
    public String applyAplication(Long userid , Long jobid , String resumeurl) {
        Optional<Aplication> aplication = aplicationRepository.findByCandidateIdAndJobId(userid , jobid);
       if(aplication.isPresent()){
           return "already is aply is i to job";
       }
       Optional<User> user = userRepository.findById(userid);
       Optional<Job> job = jobRepository.findById(jobid);
       if(user.isEmpty() || job.isEmpty()){
           return "invalid is userid is i jobid";
       }
       if(user.isPresent()){
           User useri = user.get();
           if(useri.getRole() == UserRole.RECRUITER || useri.getRole() == UserRole.ADMIN){
               return "you is not is i alowed to aply job";
           }
       }
       Aplication aplicationi = new Aplication();
       aplicationi.setCandidate(user.get());
       aplicationi.setJob(job.get());
       aplicationi.setStatus(AplicationStatus.APPLIED);
       aplicationi.setResumeurl(resumeurl);
       aplicationRepository.save(aplicationi);
       return "aplied";
    }

    @Override
    public List<Aplication> getAplicationByUserId(Long userid) {
              return aplicationRepository.getAplicationByUserId(userid);
    }




}
