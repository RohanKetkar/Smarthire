// import React from 'react'

// import {useState ,useEffect} from "react"


// import axios from "axios"
// const Aplication = () => {
//     const [aplication ,setAplication] = useState([]);
//     useEffect( ()=>{
//              try{
//                   let userid = localStorage.getItem("id");
//                   axios.get(`http://localhost:8080/aplication/v1/getAplicationByUserId/${userid}`).then((res)=>setAplication(res.data)).catch((e)=>console.log(e));
//              }catch(e){
//                 console.log(e);
//              }
//     },[])
//   return (
//     <div>
//         <h1>Aplication</h1>
//         {
//             aplication.map((res)=>{
//                 return (
//                     <div>
//                        <h3>Title : {res.job.title}</h3>
//                        <h3>Description : {res.job.description}</h3>
//                        <h3>Location : {res.job.location}</h3>
//                        <h3>Salary : {res.job.salary}</h3>
//                        Status :{res.status == "APPLIED" ?  <label style={{color:"blue"}}>{res.status}</label> :<label style={{color:"green"}}>{res.status}</label> }
//                     </div>
//                 )
//             })
//         }
//     </div>
//   )
// }

// export default Aplication
import React from 'react'
import {useState ,useEffect} from "react"
import axios from "axios"
import "./Aplication.css"
import Navbar from './Navbar'

const Aplication = () => {

    const [aplication ,setAplication] = useState([]);

    useEffect(()=>{

        try{

            let userid = localStorage.getItem("id");

            axios.get(`http://localhost:8080/aplication/v1/getAplicationByUserId/${userid}`)
            .then((res)=>setAplication(res.data))
            .catch((e)=>console.log(e));

        }catch(e){
            console.log(e);
        }

    },[])


  return (
    <div className="application-container">
<Navbar/>
        <h1 className="application-title">My Applications</h1>

        <div className="application-grid">

        {
            aplication.map((res)=>{

                return (

                    <div className="application-card" key={res.id}>

                       <h3 className="job-title">{res.job.title}</h3>

                       <p className="job-desc">{res.job.description}</p>

                       <p className="job-info">Location : {res.job.location}</p>

                       <p className="job-info">Salary : ₹{res.job.salary}</p>

                       <p className="status">
                       Status :
                       {
                        res.status == "APPLIED"
                        ?
                        <span className="status-applied">{res.status}</span>
                        :
                        <span className="status-other">{res.status}</span>
                       }
                       </p>

                    </div>

                )

            })
        }

        </div>

    </div>
  )
}

export default Aplication