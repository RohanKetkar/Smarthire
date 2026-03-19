import React from 'react'

import {useState , useEffect} from "react"


import axios from "axios"
const Aplicationuser = () => {


    const [aplication , setAplication] = useState([])
    const [job , setJob] = useState([]);
    useEffect(()=>{
            
        let id = localStorage.getItem("id");
        axios.get("http://localhost:8080/jobs/v1/getaplicationofuser/"+id).then((res)=>setJob(res.data));
    },[])
  return (
    <div>Aplicationuser</div>
  )
}

export default Aplicationuser