package com.example.Smarthire.controller;

import com.example.Smarthire.entity.Aplication;
import com.example.Smarthire.service.AplicationService;
import com.example.Smarthire.service.AplicationServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins="http://localhost:5173")
@RestController
@RequestMapping("/aplication/v1")
public class AplicationController {
    private AplicationService aplicationService;
    private AplicationServiceImpl aplicationServiceImpl;

    public AplicationController(AplicationServiceImpl aplicationServiceImpl){
        this.aplicationService = aplicationServiceImpl;
    }

    @PostMapping("")
    public String aplytoaplication(@RequestParam Long userid , @RequestParam Long jobid , @RequestParam String resumeurl){

        return aplicationService.applyAplication(userid,jobid,resumeurl);
    }
    @GetMapping("/getAplicationByUserId/{userid}")

    public List<Aplication> getAplicationByUserId(@PathVariable Long userid){
        System.out.println("userid "+ userid);
        return aplicationService.getAplicationByUserId(userid);
    }

}
