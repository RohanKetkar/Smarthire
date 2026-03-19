import React from "react";

import { useState } from "react";
import "../Style/Signup.css";
import axios from "axios";
import {useNavigate} from "react-router-dom" 
import {jwtDecode} from  "jwt-decode"
const Signup = () => {
  const [user, setUser] = useState({
    username: "",
    email: "",
    password: "",
    role: "",
  });
const navigate = useNavigate();
  const handleChange = (e) => {
    setUser({
      ...user,
      [e.target.name]: e.target.value,
    });
  };
  const handleSubmit = async (e) => {
    console.log(user);

    // e.preventDefault();
    try {
      const res = await axios.post("http://localhost:8080/api/user", user);
      console.log(res.data);
      localStorage.setItem("token",res.data);
      let subject = (jwtDecode(res.data));
      localStorage.setItem("username" , subject.sub);
      let token = localStorage.getItem("token");
    //   alert(res.data);
    const resi = await axios.get("http://localhost:8080/api/user/getid" , {
      headers :{
        Authorization: token
      }
    }).then((res)=>localStorage.setItem("id" , res.data)).catch((e)=>console.log(e));
       navigate("/jobs")
    } catch (e) {
      console.log(e);
      alert(e);
    }
  };
  return (
    <div class="signup">
      <h1 class="h1">WELCOME TO SIGNUP PAGE </h1>
      <div class="form">
        <label class="label">Enter your username </label>
        <input
          class="input"
          name="username"
          type="text"
          placeholder="username"
          onChange={handleChange}
        />
      </div>
      <div class="form">
        <label class="label">Enter your email </label>
        <input
          class="input"
          name="email"
          type="email"
          placeholder="email"
          onChange={handleChange}
        />
      </div>
      <div class="form">
        <label class="label">Enter your password </label>
        <input
          class="input"
          name="password"
          type="password"
          placeholder="password"
          onChange={handleChange}
        />
      </div>
      <div class="form">
        <label class="label">Enter your role </label>
        <select name="role" onChange={handleChange}>
          <option value="">Select role</option>
          <option value="RECRUITER">RECRUITER</option>
          <option value="CANDIDATE">CANDIDATE</option>
        </select>
      </div>
      <button class="button" onClick={() => handleSubmit()}>
        SUBMIT
      </button>
    </div>
  );
};

export default Signup;
