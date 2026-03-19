import React from 'react'

import { Navigate } from "react-router-dom";


import {useState , useEffect} from "react";
import axios from "axios";
const Protectedroute = ({children}) => {
 
    const [status , setStatus] = useState(null);


        useEffect(()=>{
try{
    const token = localStorage.getItem("token");
            //  console.log("token "+ token);
          axios.get("http://localhost:8080/api/user/authenticate" , {
                headers: {
                    Authorization: token
                }
            }).then((res)=>setStatus(res.data)).catch(()=>setStatus(false));

            // console.log("status "+ status + " "+localStorage.getItem("token"));
        }catch(e){
console.log(e);
        }
        },[])
        if(status === false){
            return <Navigate to = "/sign"/>
        }


   return children;

}

export default Protectedroute