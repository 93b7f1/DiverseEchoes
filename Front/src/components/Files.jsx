import React, { useState, useEffect } from "react";
import image from "../assets/definitive.png";
import home from "../css/home.css";
import FileContent from "./FilesContent";
import axios from "axios";

function Files() {
  const [image, setImage] = useState(null);
  const api2 = axios.create({
    baseURL: 'http://localhost:8080/'
});  useEffect(() => {
    const fetchLyrics = async () => {
        try {
            const response = await api2.get(`/echoes/last5`);
            setImage(response.data)

        } catch (err) {
            console.error(err);

        }
    };

    fetchLyrics();
}, []);

  return (
    <div className="component-home">
      <div className="titulo-component-home2">
        <p className="art-name">Images</p>
      </div>
      <div className="component-content-home">
      {image ? (
            image.map((echo) => (
              <FileContent
                key={echo.id}
                id={echo.idEcho}
                echoImage={echo.echoImage}
              />
            ))
          ) : (
            <p>Loading...</p>
          )}

      </div>
    </div>
  );
}

export default Files;

// import React, { useState, useEffect } from "react";
// import image from "../assets/definitive.png";
// import navbar from "../css/componentHome.css";
// import axios from "axios";
// function Files(props) {
 

// const url = 'http://localhost:8000/'
//   return (
//     <div className="component-home">
//       <div className="titulo-component-home2">
//         <p className="art-name">Images</p>
//       </div>
//       <div className="component-content-home">
//         <div className="teste-comp2"><img src={`${url}${props.echoImage}`}/></div>
      
//       </div>
//     </div>
//   );
// }

// export default Files;