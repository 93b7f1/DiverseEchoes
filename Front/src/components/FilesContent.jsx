import React, { useState, useEffect } from "react";
import image from "../assets/definitive.png";
import navbar from "../css/home.css";

function FileContent(props) {
    const url = 'http://localhost:8000/'

  return (
   
    <div className="teste-comp2">
        <img  src={`${url}${props.echoImage}`} 
        className="imagem-hover"
        style={{ objectFit: "cover", width: "100%", height: "100%" }}
       
        />
        
    </div>
   
   
  );
}

export default FileContent;

