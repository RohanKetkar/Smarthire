// import React from 'react'

// import {useState , useEffect} from "react"
// import axios from "axios"
// import {useNavigate} from "react-router-dom"
// import Swal from "sweetalert2";
// const Jobs = () => {
//   const [jobs , setJobs] = useState([])
//   const navigate = useNavigate();
//   useEffect(()=>{
    
     
//     try{
//       const userid = localStorage.getItem("id");
//       const page = 0 ;
      
//       const size = 2;
//       axios.get(`http://localhost:8080/jobs/v1/${userid}`,{
//             params:{
//               page:page,
//               size:size
//             }        
//       }).then((res)=>setJobs(res.data)).catch((e)=>console.log(e))
//      }catch(e){
      
//      }
//   },[])
// const applyToJob = async(e) =>{
// let jobid = e;
// let userid = localStorage.getItem("id");
// try{
// let res = await axios.post(`http://localhost:8080/aplication/v1?userid=${userid}&jobid=${jobid}&resumeurl=http:/google.com:8181/resume.pdf`).then((res)=>{return (res.data)}).catch((e)=>console.log(e));
//  Swal.fire({
//         title: "Success!",
//         text: res,
//         icon: "success",
//         confirmButtonText: "OK"
//       });
//       console.log(res);
//       navigate("/aplication")
// }catch(e){
//   console.log(e);
// }
// }
//   return (

//     <div>
//       <div>
//         {
//           jobs.map((res)=>{
//             return (
//               <div id = {res.id}>
//               <h3>Title : {res.title}</h3>
//               <h3>Description : {res.description}</h3>
//               <h3>Salary : {res.salary}</h3>
//               <h3>Location : {res.location}</h3>
//               <button onClick={(e)=>applyToJob(res.id)}>Apply</button>
//               </div>
//             )
//           })
//         }
//       </div>
//     </div>
//   )
// }

// export default Jobs
import React from 'react'
import {useState , useEffect} from "react"
import axios from "axios"
import {useNavigate} from "react-router-dom"
import Swal from "sweetalert2";
import "./Jobs.css"
import Navbar from './Navbar';

const Jobs = () => {

  const [jobs , setJobs] = useState([])
  const navigate = useNavigate();
const [page , setPage] = useState(0);
const [search , setSearch] = useState("");
  useEffect(()=>{

    try{
      const userid = localStorage.getItem("id");
      const size = 2;

      axios.get(`http://localhost:8080/jobs/v1/${userid}?page=${page}&size=${2}`,{
            params:{
              page:page,
              size:size
            }        
      }).then((res)=>setJobs(res.data)).catch((e)=>console.log(e))
     }catch(e){
     }

  },[page])

const increase = () =>{
  if(jobs.length == 1){
    setPage((prev)=>prev);
  }else{
  setPage((prev)=>prev+1);
  }
}
const decrease = () =>{
  if(page >=1)
setPage((prev)=>prev-1)
}

const applyToJob = async(e) =>{

let jobid = e;
let userid = localStorage.getItem("id");

try{

let res = await axios.post(`http://localhost:8080/aplication/v1?userid=${userid}&jobid=${jobid}&resumeurl=http:/google.com:8181/resume.pdf`)
.then((res)=>{return (res.data)})
.catch((e)=>console.log(e));

 Swal.fire({
        title: "Success!",
        text: res,
        icon: "success",
        confirmButtonText: "OK"
      });

      console.log(res);
      navigate("/aplication")

}catch(e){
  console.log(e);
}

}
const filteredjobs = jobs.filter((job)=>job.title.toLowerCase().includes(search.toLowerCase()));
  return (

    <div className="jobs-container">
<Navbar/>
<input
  className="search-input"
  type="text"
  placeholder="Search job..."
  value={search}
  onChange={(e) => setSearch(e.target.value)}
/>
      <h1 className="jobs-title">Available Jobs</h1>

      <div className="jobs-grid">

        {
          filteredjobs.map((res)=>{

            return (

              <div className="job-card" key={res.id}>

              <h3 className="job-title">{res.title}</h3>

              <p className="job-desc">{res.description}</p>

              <p className="job-info">
                Salary : ₹{res.salary}
              </p>

              <p className="job-info">
                Location : {res.location}
              </p>

              <button
                className="apply-btn"
                onClick={(e)=>applyToJob(res.id)}
              >
                Apply
              </button>
              </div>

            )

          })
        }

      </div>
      
      <div className="counter-container">
  <button className="counter-btn increase-btn" onClick={()=>increase()}>
    +
  </button>

  <button className="counter-btn decrease-btn" onClick={()=>decrease()}>
    -
  </button>
</div>
    </div>
  )
}

export default Jobs