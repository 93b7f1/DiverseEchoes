import { useRef } from "react";
import { FaBars, FaTimes } from "react-icons/fa";
import { useState } from 'react';
import React, { useEffect } from 'react';
import SidebarContent from "../components/SidebarContent";
import echo from '../css/echo.css';
import IconWithInfo from "../components/Information";
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { toast } from 'react-toastify';
import { useNavigate } from "react-router-dom";
import axios from "axios";
import Dropdown from "../components/Dropdown";
function EchoMusic(props) {
  const navigate = useNavigate();
  const validar = () => {
    const { echoname } = inputFields[0];
    if (echoname === "") {
      toast.error("Nome do Echo não inserido");
      return false;
    } else {
      return true;
    }
  }

  const [inputFields, setInputFields] = useState([
    {
      echoname: '', typea: '', type: '', pixiv: ''
    }
  ]);

  // const [valor, setValor] = useState('');

  const handleFormChange = (index, event) => {
    let data = [...inputFields];
    data[index][event.target.name] = event.target.value;
    setInputFields(data);
  };

    const [text, setText] = useState('');
  useEffect(() => {
    document.body.classList.add('echo-perfil3');
  }, []);

  const handleChange = (event) => {
    setText(event.target.value);
  };

  const [imagem, setImagem] = useState(null);

  const handleImagemChange = (event) => {
    const file = event.target.files[0];

    const reader = new FileReader();
    reader.onload = () => {
      setImagem(reader.result);
    };

    if (file) {
      reader.readAsDataURL(file);
    }
  };
  const dados = JSON.parse(sessionStorage.getItem('dados'));
  console.log(dados)
  // const formRef = useRef();

  const api = axios.create({
    baseURL: 'http://localhost:8000/api/v2/'
  });

  const submit = async (e) => {
    e.preventDefault();
    toast.success("Cadastro de música em andamento");

    if (validar() !== false) {

      const imageFile = document.getElementById("imagem").files[0];

      const echo = {
        echolink: inputFields[0].echoname,
        url: "...",
        genero: inputFields[0].typea,
        tipo: inputFields[0].type,
        pixiv: inputFields[0].pixiv,
        visualizacao: 0,
        user: dados.id
        
      };

      // const formData = new FormData();
      // formData.append("profile_picture2", imageFile);
      
      // console.log(formData)
      try {
        const response = await api.post("echo/", echo, {
          headers: {
            "Content-Type": "application/json",
          },
        });

        if (response.status === 201) {
          toast.success("Música cadastrada com sucesso!");
          setTimeout(() => {
            navigate("/");
          }, 2000);
        } else {
          toast.error("Erro ao realizar cadastro.");
        }
      } catch (error) {
        console.error(error);
        console.log(formData)
        toast.error("Erro ao realizar cadastro. Verifique os campos e tente novamente.");
      }
    }
  };
//   const options = [
//     { label: "Artwork", value: "1" },
//     { label: "Music", value: "2" },
//     { label: "Video", value: "3" },
// ];

// const [selectedValue, setSelectedValue] = useState(options[0].value);

// const handleOptionSelect = (value) => {
//     setSelectedValue(value);
// };

  return (
    <div className="pai-echo">
      <SidebarContent />
      <ToastContainer />

      <div className="direita-echo">
        <div className="navbar-echo">
          <p className="text-navbar-echo">ECHO SYSTEM - UPLOAD MUSICㅤㅤ</p>
        </div>
        <div className="esquerda-filho-echo">
          <div className="content-div-esquerda">
            <div className="content-div-esquerda-filho">
              <div className="circulo-echo">
                {imagem && <img src={imagem} alt="Imagem selecionada" style={{ objectFit: "cover", width: "100%", height: "100%", borderRadius: "30px" }} />}
              </div>

              <label htmlFor="imagem" className="custom-file-upload22" >
              Insert echo image
              </label>
              <input type="file" name="imagem" id="imagem" className="hidden" onChange={handleImagemChange} />
            </div>
            {/* <Dropdown options={options} onSelect={handleOptionSelect} /> */}


            <div className="content-div-esquerda-filho3">
              <div className="direita-filho-echo">
                
                        <div className="content-div-direita">
                            <div className="content-div-direita-filho">
                            <div className="titulo1-echo"> User Data</div>

                                {inputFields.map((input, index) => {
                                    return (
                                        <div className="inputs-echo-direita" key={index}>
                                            <p className="p-direita-echo">Username
                                                <input type="text" className="input-echo-d"placeholder={`${dados.username}`}  name="username" onChange={event => handleFormChange(index, event)} />
                                                {/* <input type="text" className="input-echo-d" placeholder={`${dados.username}`} name="username" onChange={event => handleFormChange(index, event)} /> */}
                                            </p>
                                            <div className="particao-perfil">
                                                <div className="particao-e">

                                                    <p className="p-direita-echo">Pixivㅤㅤㅤ
                                                        <input type="text" className="input-echo-n" name="twitter"  onChange={event => handleFormChange(index, event)} />
                                                    </p>
                                                    <p className="p-direita-echo">Twitterㅤㅤ
                                                        <input type="text" className="input-echo-n" name="instagram" onChange={event => handleFormChange(index, event)} />
                                                    </p>
                                                </div>

                                                <div className="particao-d  ">
                                                    <p className="p-direita-echo">Spotifyㅤㅤ
                                                        <input type="text" className="input-echo-n" name="spotify" onChange={event => handleFormChange(index, event)} />
                                                    </p>
                                                    <p className="p-direita-echo">Soundcloudㅤ
                                                        <input type="text" className="input-echo-n" name="soundCloud" onChange={event => handleFormChange(index, event)} />
                                                    </p>
                                                </div>

                                            </div>


                                            <p className="p-direita-echo">Youtube Channel
                                                <input type="text" className="input-echo-d" name="genero" onChange={event => handleFormChange(index, event)} />
                                            </p>

                                        </div>
                                    );
                                })}
                                <div className="faixa-direita-echo"></div>
                            </div>
                            <div className="content-div-direita-filho2">
                                <div className="titulo1-echo"> Biography </div>
                                <div className="align-direita2">
                                    <textarea
                                        id="lyrics"
                                        name="lyrics"
                                        rows="6"
                                        cols="40"
                                        value={text}
                                        onChange={handleChange}
                                    ></textarea>
                                    <button className="btn-direita2" onClick={submit}>Save changes</button>
                                </div>
                            </div>
                        </div>
                    </div>
            </div>

          </div>

        </div>

        <div className="mid-box-perfil3"></div>
      </div>
    </div>
  );
}

export default EchoMusic;





