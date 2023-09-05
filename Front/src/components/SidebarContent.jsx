import React, { useEffect, useState } from "react";
import "../css/sidebar.css";
import myImage from "../css/backvibnewpng.png";

function SidebarContent() {
  const dados = JSON.parse(sessionStorage.getItem("dados"));
  const divEstilo = {
    backgroundImage: `url(${myImage})`,
  };
  const [isDarkMode, setIsDarkMode] = useState(
    sessionStorage.getItem("isDarkMode") === "true"
  );

  const handleDarkModeChange = (checked) => {
    setIsDarkMode(checked);
  };

  const handleLimparSessionStorage = () => {
    sessionStorage.clear();
  };

  useEffect(() => {
    sessionStorage.setItem("isDarkMode", isDarkMode);
    if (isDarkMode) {
      document.body.classList.add("dark-mode");
    } else {
      document.body.classList.remove("dark-mode");
    }
  }, [isDarkMode]);

  useEffect(() => {
    if (isDarkMode) {
      document.body.classList.add("dark-mode");
    } else {
      document.body.classList.remove("dark-mode");
    }
  }, []);

  return (
    <div className="side-pai">
      <div id="sidebar" className="img" style={divEstilo}>
        <div className="p-4">
          <h1 className="perfil-h1">
            <a href="/" className="logo">
              Echoes <span className="usuario">Bem vindo ????</span>
            </a>
          </h1>
          <ul className="list-unstyled components mb-5">
            <li>
              <a className="sidebar-a" href="#">
                <span className="fa fa-home mr-3"></span> Home
              </a>
            </li>
            <li>
              <a className="sidebar-a" href="#">
                <span className="fa fa-user mr-3"></span> Customize profile
              </a>
            </li>
            <li>
              <a className="sidebar-a" href="#">
                <span className="fa fa-sticky-note mr-3"></span> Post Echo
              </a>
            </li>
            <li>
              <a className="sidebar-a" href="#">
                <span className="fa fa-dashboard mr-3"></span> Dashboard
              </a>
            </li>
            <li>
              <a className="sidebar-a" href="#">
                <span className="fa fa-cogs mr-3"></span>Explore Echoes
              </a>
            </li>
            <li>
              <a className="sidebar-a" href="#">
                <span className="fa fa-search mr-3"></span>AI Database
              </a>
            </li>
            <li>
              <a
                onClick={handleLimparSessionStorage}
                className="sidebar-a"
                href="/login"
              >
                <span className="fa fa-close mr-3"></span>Exit
              </a>
            </li>
          </ul>
          <div className="dark-mode-toggle">
           
         
          </div>
        </div>
      </div>
      {isDarkMode && <div className="dark-overlay"></div>}
    </div>
  );
}

export default SidebarContent;
