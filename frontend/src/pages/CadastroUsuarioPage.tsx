import CadastroUsuarioForm from "../components/CadastroUsuarioForm";
import { useNavigate } from "react-router-dom";

const CadastroUsuarioPage = () => {
  const navigate = useNavigate();
  return (
    <div style={{ padding: 32 }}>
      <CadastroUsuarioForm onSuccess={() => navigate("/login")} />
    </div>
  );
};

export default CadastroUsuarioPage;
