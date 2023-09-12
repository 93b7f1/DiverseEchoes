import React, { useState } from "react";
import Navbar from "../components/Navbar";
import homecss from "../css/login.css"
import { useRef } from "react";
import image3 from "../assets/esquerda.png"
import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { toast } from 'react-toastify';
import axios from "axios";
function Login() {

  function validar() {
    if ( usernameC.value == "" || emailC.value == "" || passC.value == "" || passCC.value == "") {
      toast.error("Preencha todos os campos para prosseguir");
      return false;
    } else if (usernameC.value.length < 5) {
      toast.error("Nome inválido");
      return false;
    } else if (emailC.value.indexOf("@") == -1 || emailC.value.indexOf(".com") == -1 || emailC.value.indexOf("@") > emailC.value.indexOf(".com")) {
      toast.error("E-mail inválido! Verifique e tente novamente.");
      return false;
    } else if (passC.value.length < 8) {
      toast.error("A senha deve conter no mínimo 8 caracteres!");
      return false;
    } else if (passC.value != passCC.value) {
      toast.error("As senhas devem ser iguais");
      return false;
    } else {
      return true;

    }
  }

  const [inputFields2, setInputFields2] = useState([
    { usernameC: '', emailC: '', passC: '', passCC: '' }
  ])

  const navigate = useNavigate();

  const [inputFields, setInputFields] = useState([
    {
      login: '', passwordLogin: ''
    }
  ]);

  const handleFormChangeC = (index, event) => {
    let data = [...inputFields2];
    data[index][event.target.name] = event.target.value;
    setInputFields2(data);

}

  const handleFormChange = (index, event) => {
    let data = [...inputFields];
    data[index][event.target.name] = event.target.value;
    setInputFields(data);
  };

  useEffect(() => {
    document.body.classList.add('login');

    return () => {
      document.body.classList.remove('login');
    };
  }, []);

  const api = axios.create({
    baseURL: 'http://localhost:8000/api/v1/'
  });

  const submit = (e) => {
    e.preventDefault();
    toast.success("Autenticando...");

    api.post('login/', {
      username: inputFields[0].login,
      password: inputFields[0].passwordLogin
    })
      .then(response => {
        console.log(response)
        sessionStorage.setItem('dados', JSON.stringify(response.data));
        navigate('/profile');
        console.log(inputFields[0].login);
        console.log(inputFields[0].passwordLogin);
        toast.success('Login bem sucedido!');
      })
      .catch(error => {
        console.error(error);
        console.log(inputFields[0].login);
        console.log(inputFields[0].passwordLogin);
        toast.error('Erro ao fazer login. Verifique suas credenciais e tente novamente.');
      });
  };

  const submitC = (e) => {
    event.preventDefault();

    if (validar() != false) {
      e.preventDefault();

      fetch("http://localhost:8000/api/v2/userprofile/create_user/",
        {
          headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
          },
          method: "POST",
          body: JSON.stringify({
            username: usernameC.value,
            password: passC.value,
            email: emailC.value,
          })
        })
        .then(function (res) {
          sessionStorage.setItem('dados', JSON.stringify({
            username: usernameC.value,
            password: passC.value,
            email: emailC.value,
          }));
          console.log(res.data)
 
          if (res.ok) {
            toast.success("Cadastro realizado com sucesso!");
            console.log(res.data)
            setTimeout(() => {
            navigate("/profile");

            }, 2000);

          } else {
            toast.error("Erro ao realizar cadastro.");

          }
        })

    }


  }


  return (
    <div>
      <Navbar />
      <ToastContainer />

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
              {inputFields.map((input, index) => {
                return (
                  <div className="void2" key={index}>

                    <div className="email-login">
                      <p className="name-email">Email</p>
                    </div>

                    <div className="input-email">
                      <input type="text" name="login" id="emaill" onChange={event => handleFormChange(index, event)} />

                    </div>

                    <div className="pass-login">
                      <p className="name-email">Password</p>

                    </div>

                    <div className="input-pass">
                      <input type="password" name="passwordLogin" id="senhaa" onChange={event => handleFormChange(index, event)} />

                    </div>

                    <div className="btn-login">
                      <button className="btncad-login" onClick={submit} >Login</button>

                    </div>


                  </div>
                );
              })}
            </div>
          </div>


      
            {inputFields.map((input, index) => {
              return (
                <div className="hello-home-direita2" >

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
                          <input type="text" name="emaill" id="usernameC" onChange={event => handleFormChangeC(index, event)}/>

                        </div>
                        <div className="n4">
                          <input type="text" name="emaill" id="emailC" onChange={event => handleFormChangeC(index, event)} />

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
                          <input type="password" name="emaill" id="passC" onChange={event => handleFormChangeC(index, event)} />

                        </div>
                        <div className="n4">
                          <input type="password" name="emaill" id="passCC"  onChange={event => handleFormChangeC(index, event)} />

                        </div>
                      </div>

                      <div className="btn-login">
                        <button className="btncad-login" onClick={submitC} >Register</button>

                      </div>
                    </div>


                  </div>
                </div>
              )
            })}

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

    </div >
  );
}

export default Login;