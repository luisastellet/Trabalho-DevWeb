import Produto from "../interfaces/Produto";
import { ProdCarrinho } from "../pages/CardsPorSlugCategoriaPage";

interface Props {
  produto: Produto;
  produtoNoCarrinho: ProdCarrinho | null;
  adicionarProduto: (produto: Produto) => void;
  subtrairProduto: (produto: Produto) => void;
}

const Card = ({ produto, adicionarProduto, subtrairProduto, produtoNoCarrinho }: Props) => {
  return (
    <div className="card h-100 border-0" style={{ minHeight: 420, maxWidth: 600, margin: '0 auto', boxShadow: '0 2px 12px #e9e1d3', background: '#f8f5f2', borderRadius: 24 }}>
      <img src={produto.imagem} className="card-img-top" alt={produto.nome} style={{ maxHeight: 220, objectFit: 'contain', background: '#e9e1d3', borderTopLeftRadius: 24, borderTopRightRadius: 24 }} />
      <div className="card-body" style={{ padding: 28 }}>
        <h5 className="card-title" style={{ color: '#7c5e3c', fontWeight: 600 }}>{produto.nome}</h5>
        <p className="card-text" style={{ minHeight: 80, color: '#5c4032', fontSize: 18, lineHeight: 1.5, marginBottom: 18 }}>{produto.descricao}</p>
        <p className="card-text fw-bold" style={{color: "#a67c52", fontSize: 22, textAlign: 'center'}}>
          R${" "}
          {produto.preco.toLocaleString("pt-BR", {
            minimumFractionDigits: 2,
            maximumFractionDigits: 2,
            useGrouping: true,
          })}
        </p>
      </div>
      <div className="card-footer p-0 mb-4" style={{ background: 'transparent', border: 'none' }}>
        <div style={produtoNoCarrinho ? {display: "block"} : {display: "none"}} >
          <div className="btn-group w-100">
            <button onClick={() => subtrairProduto(produto)} type="button" className="btn btn-secondary btn-sm">-</button>
            <button type="button" className="btn btn-secondary btn-sm">{produtoNoCarrinho?.quantidade}</button>
            <button onClick={() => adicionarProduto(produto)} type="button" className="btn btn-secondary btn-sm">+</button>
          </div>
        </div>
        <button
          style={{display: produtoNoCarrinho ? "none" : "flex", alignItems: "center", justifyContent: "center", margin: "auto", width: "30vw", fontWeight: "bold", fontSize: "large"}} onClick={() => adicionarProduto(produto)} type="button" className="btn btn-success btn-sm">
          Comprar
        </button>
      </div>
    </div>
  );
};
export default Card;
