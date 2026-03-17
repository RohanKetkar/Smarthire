package com.example.Smarthire.service;

import com.example.Smarthire.dto.UserDto;
import com.example.Smarthire.entity.AplicationStatus;
import com.example.Smarthire.entity.Job;
import com.example.Smarthire.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {


    String registerUser(UserDto userDto);
    List<User> getAllUser();

    Job createJob(Long recruiterid , Job job);


    User deleteUser(Long id);
   ResponseEntity<String> changeAplicationStatus(Long id , Long aplicationid , AplicationStatus aplicationStatus);

   Long getUserId(String token);

    String findByUsername(User user);

    User getuserbyname(String username);
}
