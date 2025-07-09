import LoginForm from "../components/LoginForm";
import { useNavigate } from "react-router-dom";

const LoginPage = () => {
  const navigate = useNavigate();
  return (
    <>
      <div className="mb-4">
        <h5>Página de Login</h5>
        <hr className="mt-1" />
      </div>

      <LoginForm />
      <div className="mt-3 text-center">
        <span>Não tem uma conta?{' '}</span>
        <button
          className="btn btn-link p-0"
          style={{ color: '#8B4513', textDecoration: "none", fontWeight: 500 }}
          onClick={() => navigate("/cadastro")}
        >
          Cadastre-se
        </button>
      </div>
    </>
  );
};
export default LoginPage;
