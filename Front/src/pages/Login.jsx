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
import { useEffect } from "react";

function Login() {
  useEffect(() => {
    document.body.classList.add('login');
  
    return () => {
      document.body.classList.remove('login');
    };
  }, []);
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
                  <p className="name-email">Email</p>
                </div>

                <div className="input-email">
                  <input type="text" name="emaill" id="emaill" />

                </div>

                <div className="pass-login">
                  <p className="name-email">Password</p>

                </div>

                <div className="input-pass">
                  <input type="password" name="senhaa" id="senhaa" />

                </div>

                <div className="btn-login">
                  <button className="btncad-login" >Login</button>

                </div>
              </div>
            </div>
          </div>



          <div className="hello-home-direita2">

            <div className="fake-white">
            </div>
            <div className="content-login">
              <div className="caixa-login">
                <p className="login-name2">REGISTER</p>
              </div>

              <div className="caixa-names-register">

                <div className="email-login2">
                  <div className="n1">
                    <p className="name-email2">Username</p>

                  </div>
                  <div className="n2">
                    <p className="name-email2">Email</p>

                  </div>
                </div>

                <div className="input-email">
                  <div className="n3">
                    <input type="text" name="emaill" id="emaill2" />

                  </div>
                  <div className="n4">
                    <input type="text" name="emaill" id="emaill2" />

                  </div>
                </div>

                <div className="pass-login">
                  <div className="n1">
                    <p className="name-email2">Password</p>

                  </div>
                  <div className="n2">
                    <p className="name-email2">Confirm password</p>

                  </div>
                </div>

                <div className="input-pass">
                  <div className="n3">
                    <input type="password" name="emaill" id="emaill2" />

                  </div>
                  <div className="n4">
                    <input type="password" name="emaill" id="emaill2" />

                  </div>
                </div>

                <div className="btn-login">
                  <button className="btncad-login" >Register</button>

                </div>
              </div>


            </div>
          </div>
        </div>

        <div>


        </div>
        <div className="footer">
          <div className="footer2">
            About Us
          </div>
          <div className="footer2">
            Developers
          </div>
          <div className="footer2">
            Terms of Use
          </div>
          <div className="footer2">
            Privacy
          </div>
          <div className="footer2">
            Help
          </div>
        </div>
      </div>

    </div>
  );
}

export default Login;