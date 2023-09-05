import { useState } from 'react'
import React from 'react'
import Navbar from './components/Navbar'
import { Routes, Route, Navigate } from "react-router-dom";
import Home from './pages/Home';
import Login from './pages/Login';
import Echo from './pages/Echo';
  function App() {
    return (
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/echo" element={<Echo />} />


      </Routes>
    );
  }

export default App
