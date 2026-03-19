// import React from 'react'

// import {Link} from "react-router-dom"
// import "./Index.css"
// const Navbar = () => {
//   return (
//     <div><ul>
//           <Link to ="/">Home</Link>
//           <Link to ="/jobs">Jobs</Link>
//           <Link to ="/aplication">Aplications</Link>
//           <Link to ="/contact">Contact</Link>
//         </ul></div>
//   )
// }

// export default Navbar

import React from 'react'
import {Link} from "react-router-dom"
import "./Index.css"


import axios from "axios"




import {useState , useEffect} from "react"
const Navbar = () => {
    const [valid , setValid] = useState(false);
    useEffect(()=>{
    


        let username = localStorage.getItem("username")
 axios.get("http://localhost:8080/api/user/getbyusername"+"/"+username).then((res)=>(res.data.role) === "RECRUITER" ? setValid(true) : setValid(false))
    },[])
  return (

    <nav className="navbar">

        <h2 className="logo">SmartHire</h2>

        <ul className="nav-links">

          <li><Link to="/">Home</Link></li>

          <li><Link to="/jobs">Jobs</Link></li>

          <li><Link to="/aplication">Applications</Link></li>

          <li><Link to="/contact">Contact</Link></li>
          {valid && <li><Link to="/postjob">postjob</Link></li>}
        </ul>

    </nav>

  )
}

export default Navbar