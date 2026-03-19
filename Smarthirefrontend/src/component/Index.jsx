import "./Index.css";

import {Link} from "react-router-dom"


import {useState , useEffect} from "react"
import axios from "axios"
function Index() {
    const [jobs , setJobs] = useState([])
    useEffect(()=>{
                 axios.get("http://localhost:8080/jobs/v1/getalljobs").then((res)=>setJobs(res.data))
    },[])
  return (
    <div>

      {/* Navbar */}
      <nav className="navbar">
        <h2 className="logo">SmartHire</h2>

        <ul>
          <Link to ="/">Home</Link>
          <Link to ="/jobs">Jobs</Link>
          <Link to ="/aplication">Aplications</Link>
          <Link to ="/contact">Contact</Link>
        </ul>

        <Link className="login-btn" to ="/sign">Login</Link>
      </nav>


      {/* Hero Section */}
      <section className="hero">

        <div className="hero-text">
          <h1>Hire Smart. Grow Fast.</h1>

          <p>
            SmartHire connects talented developers with top companies.
            Find jobs or hire talent in seconds.
          </p>

          <Link className="explore-btn" to="/jobs">Explore Jobs</Link>
        </div>

      </section>


      {/* Features */}
      <section className="features">

        <h2>Why SmartHire?</h2>

        <div className="feature-container">

          <div className="feature-card">
            <h3>Verified Recruiters</h3>
            <p>Only trusted companies can post jobs.</p>
          </div>

          <div className="feature-card">
            <h3>Fast Applications</h3>
            <p>Apply for jobs with one click.</p>
          </div>

          <div className="feature-card">
            <h3>Secure Platform</h3>
            <p>JWT authentication keeps your account safe.</p>
          </div>

        </div>

      </section>


      {/* Jobs */}
      <section className="jobs">

        <h2>Latest Jobs</h2>

        <div className="job-container">

          {
  jobs
  .sort((a,b)=> new Date(b.createdat) - new Date(a.createdat)) 
  .slice(0,3)
  .map((res)=>{
    return(
      <div className="job-card" key={res.id}>

        <h3 className="job-title">Title : {res.title}</h3>

        <p className="job-desc">Description : {res.description}</p>

        <p className="job-salary">Salary : ₹{res.salary}</p>

        <p className="job-location">Location : {res.location}</p>

      </div>
    )
  })
}

        </div>

      </section>


      {/* Footer */}
      <footer>
        <p>© 2026 SmartHire</p>
      </footer>

    </div>
  );
}

export default Index;