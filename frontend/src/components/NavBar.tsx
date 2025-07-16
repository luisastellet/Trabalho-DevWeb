import { NavLink } from "react-router-dom";
// Trocar para logo do public
const logo = "/logo.png";
import useUsuarioStore from "../store/UsuarioStore";
import Produto from "../interfaces/Produto";
import useProdutoStore from "../store/ProdutoStore";
const NavBar = () => {
  const usuarioLogado = useUsuarioStore((s) => s.usuarioLogado);
  const nomeUsuario = useUsuarioStore((s) => s.nomeUsuario);
  const setProdutoSelecionado = useProdutoStore((s) => s.setProdutoSelecionado);
  
  return (
    <nav className="navbar navbar-expand-lg bg-light navbar-light" style={{ borderBottom: '2px solid #e0c3a0', background: '#e9e1d3', minHeight: 90 }}>
      <div className="container" style={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between', minHeight: 90 }}>
        <div style={{ display: 'flex', alignItems: 'center', gap: 16 }}>
          <img
            src={logo}
            alt="logo do site"
            style={{ width: "90px", height: "90px", paddingRight: "10px", objectFit: "contain" }}
          />
          <button
            className="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarNav"
            aria-controls="navbarNav"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span className="navbar-toggler-icon"></span>
          </button>
        </div>
        <div className="collapse navbar-collapse" id="navbarNav">
          <ul className="navbar-nav" style={{ gap: 18 }}>
            <li className="nav-item">
              <NavLink className="nav-link" aria-current="page" to="/">
                Home
              </NavLink>
            </li>
            <li className="nav-item">
              <NavLink className="nav-link" to="/favoritos">
                Favoritos
              </NavLink>
            </li>
            <li className="nav-item">
              <NavLink className="nav-link" to="/produtos">
                Produtos
              </NavLink>
            </li>
            <li className="nav-item">
              <NavLink onClick={() => setProdutoSelecionado({} as Produto)} className="nav-link" to="/cadastrar-produto">
                Cadastrar Produto
              </NavLink>
            </li>
          </ul>
          <ul className="navbar-nav ms-auto" style={{ gap: 18 }}>
            {usuarioLogado ? (
              <li className="nav-item d-flex align-items-center" style={{ fontWeight: 600, color: '#7c5e3c', fontSize: 18 }}>
                Olá, {nomeUsuario}
              </li>
            ) : null}
            <li className="nav-item">
              <NavLink className="nav-link" to="/carrinho">
                Carrinho
              </NavLink>
            </li>
            <li className="nav-item">
              <NavLink className="nav-link" to="/login">
                {usuarioLogado ? "Sair" : "Entrar"}
              </NavLink>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
};
export default NavBar;
