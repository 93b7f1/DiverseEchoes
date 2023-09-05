import React, { useState } from "react";
import image from "../assets/definitive.png";
import navbar from "../css/navbar.css"

function Navbar() {
  const [showMenu, setShowMenu] = useState(false);

  const handleMenuToggle = () => {
    setShowMenu((prevShowMenu) => !prevShowMenu);
    document.body.style.overflow = showMenu ? "hidden" : "initial";
  };

  return (
    <header>
      <div className="container">
  <div className="transition-nav">
  <a href="/"><img src={image} alt="Logo" className="logo-img" /></a> 

        <input type="text" className="input-nav" placeholder="&#61442;" />
        </div>
        <div className={`menu-section ${showMenu ? "on" : ""}`}>
        
          <div className="menu-toggle" onClick={handleMenuToggle}>
            <div className="one"></div>
            <div className="two"></div>
            <div className="three"></div>
          </div>
            <div className="uls">
          <nav>
            <ul>

              <li>
                <a href="/echo" ><button className="btn-nav1">Post your echo  &#9662;</button></a>
              </li>
              <li>
                <a href="/login"><button className="btn-nav2">Sign in &#9662;</button></a>
              </li>
             
            </ul>
          </nav>
          </div>
          </div>
        </div>
    </header>
  );
}

export default Navbar;