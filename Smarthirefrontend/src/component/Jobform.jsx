import React, { useState } from "react";
import Navbar from "./Navbar";
import "./Jobform.css"
import {useNavigate} from "react-router-dom"

import axios from "axios"
const Jobform = () => {
const navigate = useNavigate();
  const [job, setJob] = useState({
    title: "",
    description: "",
    salary: "",
    location: "",
    experience: ""
  });

  const handleChange = (e) => {
    setJob({
      ...job,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    console.log(job);   // here you can send to backend
  };



  const createjob = () =>{
    let id = localStorage.getItem("id");
    axios.post("http://localhost:8080/api/user/createjob/"+id , job)
   navigate("/jobs")
  }
  return (
    <form className="job-form" onSubmit={handleSubmit}>
<Navbar/>
  <input
    type="text"
    name="title"
    placeholder="Job Title"
    value={job.title}
    onChange={handleChange}
  />

  <input
    type="text"
    name="description"
    placeholder="Description"
    value={job.description}
    onChange={handleChange}
  />

  <input
    type="number"
    name="salary"
    placeholder="Salary"
    value={job.salary}
    onChange={handleChange}
  />

  <input
    type="text"
    name="location"
    placeholder="Location"
    value={job.location}
    onChange={handleChange}
  />

  <input
    type="number"
    name="experience"
    placeholder="Experience"
    value={job.experience}
    onChange={handleChange}
  />

  <button type="submit" onClick={()=>createjob()}>Create Job</button>

</form>
  );
};

export default Jobform;