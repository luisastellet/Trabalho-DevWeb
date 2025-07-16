import useFavoritosStore from "../store/FavoritosStore";
import useUsuarioStore from "../store/UsuarioStore";
import useRecuperarProdutos from "../hooks/useRecuperarProdutos";
import Produto from "../interfaces/Produto";
import { useEffect, useState } from "react";
import { Navigate, useLocation, Link } from "react-router-dom";
import "./CarrinhoPage.css";

export interface ProdCarrinho {
  idProduto: number;
  quantidade: number;
}

const FavoritosPage = () => {
  const usuarioId = useUsuarioStore((s) => s.usuarioLogado);
  const location = useLocation();
  if (!usuarioId) {
    return <Navigate to="/login" state={{ destino: location.pathname }} />;
  }

  const { favoritosPorUsuario, removerFavorito } = useFavoritosStore();
  const { data: produtos, isPending, error } = useRecuperarProdutos();

  // Estado local para quantidades dos favoritos
  const [quantidades, setQuantidades] = useState<{ [produtoId: number]: number }>({});

  // Estado do carrinho sincronizado com localStorage
  const [carrinho, setCarrinho] = useState<ProdCarrinho[]>(() => {
    const itensDeCarrinho = localStorage.getItem("carrinho");
    return itensDeCarrinho ? JSON.parse(itensDeCarrinho) : [];
  });

  useEffect(() => {
    localStorage.setItem("carrinho", JSON.stringify(carrinho));
  }, [carrinho]);

  useEffect(() => {
    if (produtos) {
      const favoritosIds = favoritosPorUsuario[usuarioId] || [];
      const itensDeCarrinho = localStorage.getItem("carrinho");
      const carrinho: ProdCarrinho[] = itensDeCarrinho ? JSON.parse(itensDeCarrinho) : [];
      const novasQuantidades: { [produtoId: number]: number } = {};
      favoritosIds.forEach((id) => {
        const itemCarrinho = carrinho.find((item) => item.idProduto === id);
        if (itemCarrinho) {
          novasQuantidades[id] = itemCarrinho.quantidade;
        } else {
          novasQuantidades[id] = quantidades[id] !== undefined ? quantidades[id] : 0;
        }
      });
      setQuantidades(novasQuantidades);
    }
    // eslint-disable-next-line
  }, [produtos, favoritosPorUsuario, usuarioId]);

  if (isPending) return <p>Carregando favoritos...</p>;
  if (error) throw error;

  const favoritosIds = favoritosPorUsuario[usuarioId] || [];
  const produtosFavoritos = produtos.filter((p: Produto) => favoritosIds.includes(p.id!));

  if (produtosFavoritos.length === 0)
    return (
      <>
        <h4 className="mb-4">Nenhum favorito ainda!</h4>
        <div>
          <Link to="/" className="btn btn-secondary">
            Ver Produtos
          </Link>
        </div>
      </>
    );

  // Função para alterar quantidade e atualizar carrinho
  const alterarQuantidade = (produto: Produto, novaQuantidade: number) => {
    setQuantidades((prev) => ({ ...prev, [produto.id!]: Math.max(0, novaQuantidade) }));
    setCarrinho((prevCarrinho) => {
      const existe = prevCarrinho.find((item) => item.idProduto === produto.id);
      if (existe) {
        if (novaQuantidade <= 0) {
          // Remove do carrinho se quantidade 0
          return prevCarrinho.filter((item) => item.idProduto !== produto.id);
        }
        // Atualiza
        return prevCarrinho.map((item) =>
          item.idProduto === produto.id
            ? { ...item, quantidade: novaQuantidade }
            : item
        );
      } else {
        if (novaQuantidade <= 0) return prevCarrinho;
        // Adiciona novo
        return [...prevCarrinho, { idProduto: produto.id!, quantidade: novaQuantidade }];
      }
    });
  };

  return (
    <div className="container mt-4">
      <section className="carrinho-table-container">
        <h1 className="carrinho-titulo">Seus Favoritos</h1>
        <div className="table-responsive carrinho-table-container">
          <table className="table table-bordered table-hover align-middle carrinho-table">
            <thead>
              <tr>
                <th style={{ width: 100 }}>Produto</th>
                <th className="text-center">Nome</th>
                <th className="text-center">Preço unitário</th>
                <th className="text-center">Quantidade</th>
                <th className="text-center">Subtotal</th>
                <th className="text-center">Ações</th>
              </tr>
            </thead>
            <tbody>
              {produtosFavoritos.map((produto) => (
                <tr key={produto.id}>
                  <td style={{ width: "100px" }}>
                    <img
                      src={produto.imagem}
                      alt={produto.nome}
                      className="img-fluid img-thumbnail carrinho-produto-img"
                    />
                  </td>
                  <td className="text-center carrinho-nome">{produto.nome}</td>
                  <td className="text-center carrinho-preco-unitario">
                    R$ {produto.preco.toLocaleString("pt-BR", {
                      minimumFractionDigits: 2,
                      maximumFractionDigits: 2,
                      useGrouping: true,
                    })}
                  </td>
                  <td className="text-center">
                    <div className="input-group input-group-sm justify-content-center carrinho-quantidade-group">
                      <button
                        className="btn btn-outline-secondary carrinho-btn"
                        onClick={() => alterarQuantidade(produto, (quantidades[produto.id!] || 0) - 1)}
                        disabled={(quantidades[produto.id!] || 0) <= 0}
                      >
                        -
                      </button>
                      <input
                        type="number"
                        className="form-control text-center carrinho-quantidade-input"
                        value={quantidades[produto.id!] ?? 0}
                        min={0}
                        onChange={(e) => {
                          const valor = e.target.valueAsNumber;
                          if (!Number.isNaN(valor) && valor >= 0) {
                            alterarQuantidade(produto, valor);
                          }
                        }}
                      />
                      <button
                        className="btn btn-outline-secondary carrinho-btn"
                        onClick={() => alterarQuantidade(produto, (quantidades[produto.id!] || 0) + 1)}
                      >
                        +
                      </button>
                    </div>
                  </td>
                  <td className="text-center carrinho-subtotal">
                    R$ {(produto.preco * (quantidades[produto.id!] || 0)).toLocaleString("pt-BR", {
                      minimumFractionDigits: 2,
                      maximumFractionDigits: 2,
                      useGrouping: true,
                    })}
                  </td>
                  <td className="text-center">
                    <button
                      className="btn btn-outline-danger btn-sm"
                      title="Remover dos favoritos"
                      onClick={() => removerFavorito(usuarioId, produto.id!)}
                    >
                      Remover ❤️
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
        <div className="row align-items-center mt-4" style={{ maxWidth: 900, margin: "0 auto" }}>
          <div className="col-md-6 d-flex justify-content-start">
            <Link to="/" className="btn carrinho-btn-lg carrinho-btn-continuar" style={{padding: "12px 0", minHeight: 48, display: 'flex', alignItems: 'center', fontWeight: 600, fontSize: '1.15rem', borderRadius: 8, minWidth: 200, padding: "0px 15px"}}>
              Continuar Comprando
            </Link>
          </div>
          <div className="col-md-6 d-flex justify-content-end align-items-center gap-3">
            <h4 style={{ color: '#7c5e3c', fontWeight: 700, margin: 0, minWidth: 180, textAlign: 'center', display: 'flex', alignItems: 'center', height: 48 }}>
              Total: R$ {produtosFavoritos
                .map((produto) =>
                  produto.preco * (quantidades[produto.id!] || 0)
                )
                .reduce((total, subtotal) => total + subtotal, 0)
                .toLocaleString("pt-BR", {
                  minimumFractionDigits: 2,
                  maximumFractionDigits: 2,
                  useGrouping: true,
                })}
            </h4>
          </div>
        </div>
      </section>
    </div>
  );
};
export default FavoritosPage;