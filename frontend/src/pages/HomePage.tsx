import { NavLink, Outlet } from "react-router-dom";

// Categorias conforme backend
const categorias = [
  { nome: "Sabonete", slug: "sabonetes" },
  { nome: "Hidratante", slug: "hidratantes" },
  { nome: "Vela aromática", slug: "velas-aromaticas" },
  { nome: "Home & Decor", slug: "home-decor" },
  { nome: "Shampoo", slug: "shampoos" },
];

const HomePage = () => {
  return (
    <div className="row">
      <div className="col-lg-2">
        <nav className="nav nav-pills d-flex flex-column">
          <h5>Categorias</h5>
          <NavLink className="nav-link" aria-current="page" to="/">
            Todos
          </NavLink>
          {categorias.map((cat) => (
            <NavLink key={cat.slug} className="nav-link" to={`/${cat.slug}`}>
              {cat.nome}
            </NavLink>
          ))}
        </nav>
      </div>
      <div className="col-lg-10">
        <Outlet />
      </div>
    </div>
  );
};
export default HomePage;
