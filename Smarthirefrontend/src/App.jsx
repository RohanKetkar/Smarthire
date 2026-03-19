import React from 'react'
import Signup from './component/Signup'
import {BrowserRouter , Routes , Route} from "react-router-dom"
import Jobs from "./component/Jobs"
import Notavailable from "./component/Notavailable"
import Protectedroute from './component/Protectedroute'
import Aplication from "./component/Aplication"
import Signin from "./component/Signin"
import Index from "./component/Index"
import Navbar from './component/Navbar'
import Contact from './component/Contact'
import Jobform from './component/Jobform'
const App = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path ="/" element={<Index/>}/>
        {console.log("id "+ localStorage.getItem("id"))}
       <Route path = "/sign" element={localStorage.getItem("id") ? <Signin /> : <Signup />}   />
        
        {/* {localStorage.getItem("token")
        ?
        <Route path="/jobs" element={<Jobs />} />
        :
        <Route path ="/Notavailable" element = {<Notavailable/>} />
        } */}
        <Route path="/Notavailable" element={<Notavailable />} />


        <Route path ="/contact" element={<Contact/>}/>
        <Route path ="/postjob" element={<Jobform/>}/>
        <Route
 path="/jobs"
 element={
   <Protectedroute>
      <Jobs />
   </Protectedroute>
   }
   />
   <Route path ="/aplication" element={<Aplication/>} />
      </Routes>
    </BrowserRouter>
   
  )
}

export default App