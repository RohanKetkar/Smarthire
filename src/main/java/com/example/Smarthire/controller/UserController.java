package com.example.Smarthire.controller;


import com.example.Smarthire.dto.UserDto;
import com.example.Smarthire.entity.AplicationStatus;
import com.example.Smarthire.entity.Job;
import com.example.Smarthire.entity.User;
import com.example.Smarthire.service.AplicationService;
import com.example.Smarthire.service.AplicationServiceImpl;
import com.example.Smarthire.service.UserService;
import com.example.Smarthire.util.JWTUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:5173/")

@RestController
@RequestMapping("/api/user")
public class UserController {
private final UserService userservice;

    private AplicationService aplicationService;
    private AplicationServiceImpl aplicationServiceImpl;
    public UserController(UserService userservice , AplicationService aplicationService) {
        this.userservice = userservice;
        this.aplicationService = aplicationService;
    }

    @PostMapping
    public String registerUser(@RequestBody UserDto userdto){

        if(userdto.getUsername() != null){
            userservice.registerUser(userdto);
            return JWTUtil.generateToken(userdto.getUsername());
        }
        return "username is not is i present";
    }
    @GetMapping
    public List<User> getUsers(){
       System.out.println("request came for signup");
        return userservice.getAllUser();
    }
    @PostMapping("/createjob/{recruiterid}")
    public Job createJob(@PathVariable Long recruiterid , @RequestBody Job job){
        return userservice.createJob(recruiterid , job);
    }
    @DeleteMapping("/deleteuser/{id}")
    public User deleteUser(@PathVariable Long id){

        return userservice.deleteUser(id);
    }
    @PutMapping("/changestatus/{id}/{aplicationid}/status")
    public ResponseEntity<String> changeAplicationStatus(@PathVariable Long id , @PathVariable Long aplicationid , @RequestParam AplicationStatus status ){
        return userservice.changeAplicationStatus(id,aplicationid,status);
    }
    @GetMapping("/authenticate")
    public Boolean authenticate(@RequestHeader("Authorization") String token){
        try {
//            System.out.println("token "+token);
            String username = JWTUtil.extractUsernameFromToken(token);
            System.out.println("username "+username);
            if (token == null && username.isEmpty()) {
                System.out.println("username "+username);
                return false;
            }
        }catch(Exception e){
            e.printStackTrace();
                  return false;
        }
        return true;
    }
    @GetMapping("/getid")
    public Long getUserId(@RequestHeader("Authorization") String token){
        return userservice.getUserId(token);
    }
    @PostMapping("/signin")
    public String signin(@RequestBody User user){
        return userservice.findByUsername(user);
    }
    @GetMapping("/getbyusername/{username}")
    public User getuserbyname(@PathVariable String username){
        System.out.println("username is "+username);
        return userservice.getuserbyname(username);
    }
}
