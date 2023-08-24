import React, { useState } from "react";
import image from "../assets/definitive.png";
import Navbar from "../components/Navbar";
import homecss from "../css/home.css"
import image2 from "../assets/home.png"
import Artists from "../components/Artists";
import Files from "../components/Files";
import Videos from "../components/Videos";
import Musics from "../components/Musics";
import BannerFinal from "../components/BannerFinal";
import image3 from "../assets/esquerda.png"


function Home() {

  return (
    <div>
      <Navbar />

      <div className="pai-home">
      <div className="percent-home"></div>

        <div className="hello-home">
          <div className="hello-home-esquerda">

            <div className="content-home2">
              <div className="filho-content-home2">
              


              </div>
            </div>
            <div className="content-home" >
              <img className="imagem-home" src={image3} style={{ width: "100%", height: "100%", objectFit: "cover" }} />
            </div>

          </div>
          <div className="hello-home-direita">
            <img className="imagem-home" src={image2} style={{ width: "100%", height: "100%", objectFit: "cover" }} />
          </div>
        </div>
        
        <div className="components-home">
            <Artists/>

            <Files/>

            <Videos/>

            <Musics/>

            <BannerFinal/>


        </div>
        <div className="zero"></div>
      </div>

    </div>
  );
}

export default Home;