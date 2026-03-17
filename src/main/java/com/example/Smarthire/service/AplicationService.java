package com.example.Smarthire.service;

import com.example.Smarthire.entity.Aplication;

import java.util.List;

public interface AplicationService {


    public String applyAplication(Long userid , Long jobid , String resumeurl);
    public List<Aplication> getAplicationByUserId(Long userid);



}
