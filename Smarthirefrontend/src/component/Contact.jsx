import React from 'react'
import "./Contact.css"
import Navbar from './Navbar'

const Contact = () => {
  return (

    <div className="contact-container">
        <Navbar/>
        <h1 className="contact-title">Contact Us</h1>

        <div className="contact-card">

            <p>Email : support@smarthire.com</p>

            <p>Phone : +91 9876543210</p>

            <p>Location : Mumbai, India</p>

        </div>

    </div>

  )
}

export default Contact