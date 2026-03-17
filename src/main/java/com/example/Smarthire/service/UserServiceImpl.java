package com.example.Smarthire.service;

import com.example.Smarthire.dto.UserDto;
import com.example.Smarthire.entity.*;
import com.example.Smarthire.repository.AplicationRepository;
import com.example.Smarthire.repository.JobRepository;
import com.example.Smarthire.repository.UserRepository;
import com.example.Smarthire.util.JWTUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    private final JobRepository jobRepository;
    private final AplicationRepository aplicationRepository;

    public UserServiceImpl(UserRepository userRepository , JobRepository jobRepository , AplicationRepository aplicationRepository){
        this.userRepository = userRepository;
        this.jobRepository = jobRepository;
        this.aplicationRepository = aplicationRepository;
    }
    @Override
    public String registerUser(UserDto userDto) {
           User user = new User();
           user.setUsername(userDto.username);

           user.setEmail(userDto.email);
           user.setPassword(userDto.password);
           user.setRole(userDto.role);
           Optional<User> useri = userRepository.findByEmail(userDto.email);
           if(useri.isPresent()){
               User finduser = useri.get();
               User finduseri =  new User("user is already is i present with that email "+ finduser.getEmail());
               return "";
           }
           userRepository.save(user);




           return "";
    }

    @Override
    public List<User> getAllUser() {

         List<User> users = userRepository.findAll();
//         List<UserDto> userDto = new ArrayList<>();
//         for(User user : users){
//             UserDto dtoi = new UserDto();
//             dtoi.email = user.getEmail();
//             dtoi.username = user.getUsername();
//             dtoi.password = user.getPassword();
//             userDto.add(dtoi);
//         }
         return users;
    }

    @Override
    public Job createJob(Long recruiterid , Job job) {
            Optional<User> user = userRepository.findById(recruiterid);
        User recruiter = user.get();
            if(user.isPresent() && user != null){
                if(recruiter.getRole() == UserRole.RECRUITER){
                    job.setRecruiter(recruiter);
                    return jobRepository.save(job);
                }
            }
                Job job1 = new Job();
            job1.setTitle("You is not is valid recruiter" + recruiter.getRole());
                return job1;

    }
    @Override
    public User deleteUser(Long id){
        Optional <User> user = userRepository.findById(id);

        if(user.isPresent()){
            User useri = user.get();
            userRepository.deleteById(id);
            return useri;
        }
        User useri = new User();
        return useri;
    }
    @Override
    public ResponseEntity<String> changeAplicationStatus(Long id , Long aplicationid , AplicationStatus aplicationStatus){
       Optional <User> user = userRepository.findById(id);
       if(user.isPresent()){
           User useri = user.get();
           if(useri.getRole() == UserRole.RECRUITER || useri.getRole() == UserRole.ADMIN){
             Optional<Aplication> aplication = aplicationRepository.findById(aplicationid);
             if(aplication.isPresent()){
                 Aplication aplicationi = aplication.get();
                 Long recruiterid = aplicationi.getJob().getRecruiter().getId();
                 if(recruiterid.equals(useri.getId())){
                     aplicationi.setStatus(aplicationStatus);
                     aplicationRepository.save(aplicationi);
                     return ResponseEntity.ok("Status changed");
                 }
             }
           }
       }
        return ResponseEntity.status(403).body("Status is not is i change");

    }
    @Override
    public Long getUserId(String token){
      String username =   JWTUtil.extractUsernameFromToken(token);
         if(token == null || username.isEmpty()){
             return 0L;
         }
         Optional <User> user = userRepository.findByUsername(username);
         if(user.isPresent()){
             User useri = user.get();
             return useri.getId();
         }


         return 0L;
    }

    @Override
    public String findByUsername(User user) {
       Optional <User> useri = userRepository.findByUsername(user.getUsername());
       if(useri.isPresent()){
           User userii = useri.get();
           if(userii.getPassword().equals(user.getPassword())){
               return JWTUtil.generateToken(userii.getUsername());
           }
       }
       return "";
    }

    @Override
    public User getuserbyname(String username) {
       Optional <User> user = userRepository.findByUsername(username);
       return user.get();
    }
}
