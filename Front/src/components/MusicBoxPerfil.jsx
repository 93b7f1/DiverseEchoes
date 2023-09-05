import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { RiDeleteBinLine } from 'react-icons/ri';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import AlertDialog from './Dialogo';

function MusicBoxPerfil(props) {
  const navigate = useNavigate();
  const url = "..."
  const api = axios.create({
    baseURL: '...'
  });
  const [isDeleted, setIsDeleted] = useState(false);
  const [openDialog, setOpenDialog] = useState(false);

  const handleMusicBoxClick = () => {
    navigate(`/echolink/${props.id}`);
  };

  const handleDeleteIconClick = (e) => {
    e.stopPropagation();
    setOpenDialog(true);
  };

  const handleDeleteConfirmation = async () => {
    try {
      const response = await api.delete(`/echo/deletar/echo/${props.id}`);

      if (response.status === 204) {
        toast.success("Música excluída com sucesso!");
        setIsDeleted(true);
        setTimeout(() => {
          navigate("/perfil");
        }, 2000);
      } else {
        toast.error("Erro ao realizar exclusão.");
      }
    } catch (error) {
      console.error(error);
      toast.error("Erro ao realizar exclusão.");
    }

    setOpenDialog(false);
  };

  const handleDeleteCancel = () => {
    setOpenDialog(false);
  };

  if (isDeleted) {
    return null;
  }

  return (
    <div className="boxmusic2222" onClick={handleMusicBoxClick}>
      <ToastContainer />
      <img
        src={`${url}${props.blob}`}
        className="imagem-hover2"
        style={{ objectFit: "cover", width: "100%", height: "100%" }}
        alt=""
      />
      <div className="delete-icon" onClick={handleDeleteIconClick}>
        <RiDeleteBinLine style={{ fontSize: "25px", color: "red" }} />
      </div>
      {openDialog && (
        <AlertDialog
          handleConfirmation={handleDeleteConfirmation}
          handleCancel={handleDeleteCancel}
          setOpenDialog={setOpenDialog}
        />
      )}
    </div>
  );
}

export default MusicBoxPerfil;