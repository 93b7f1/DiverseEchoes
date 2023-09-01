import React, { useState } from "react";
import image from "../assets/definitive.png";
import Navbar from "../components/Navbar";
import homecss from "../css/login.css"
import image2 from "../assets/home.png"
import Artists from "../components/Artists";
import Files from "../components/Files";
import Videos from "../components/Videos";
import Musics from "../components/Musics";
import BannerFinal from "../components/BannerFinal";
import image3 from "../assets/esquerda.png"


function Login() {

  return (
    <div>
      <Navbar />

      <div className="pai-home">
        <div className="percent-home"></div>

        <div className="hello-home2">
          <div className="hello-home-esquerda">


            <div className="content-home2" >
              <img className="imagem-home" src={image3} style={{ width: "100%", height: "100%", objectFit: "cover" }} />
            </div>
            <div className="texto-sobre-imagem">
              <div className="void">
                <div className="login-name">LOGIN

                </div>
              </div>

              <div className="void2">
                <div className="email-login">

                </div>
                <div className="input-email">

                </div>
                <div className="pass-login">

                </div>
                <div className="input-pass">

                </div>
                <div className="btn-login">

                </div>
              </div>

              <div className="void3">

              </div>


            </div>
          </div>

          <div className="hello-home-direita2">
            <div className="fake-white">

            </div>
            <div className="content-login">

            </div>
          </div>
        </div>

        <div>

          aaaaa

        </div>

      </div>

    </div>
  );
}

export default Login;