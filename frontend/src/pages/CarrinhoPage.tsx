import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import Produto from "../interfaces/Produto";
import useRecuperarProdutos from "../hooks/useRecuperarProdutos";
import "./CarrinhoPage.css";
import useUsuarioStore from "../store/UsuarioStore";

export interface ProdCarrinho {
  idProduto: number;
  quantidade: number;
}

interface ProdutoComCarrinho {
  produto: Produto;
  prodCarrinho: ProdCarrinho;
}

const CarrinhoPage = () => {
  const [carrinho, setCarrinho] = useState<ProdCarrinho[]>(() => {
    const itensDeCarrinho = localStorage.getItem("carrinho");
    return itensDeCarrinho ? JSON.parse(itensDeCarrinho) : [];
  });

  // Estado separado para edição do subtotal
  const [editingSubtotals, setEditingSubtotals] = useState<{ [idProduto: number]: string }>({});

  const usuarioId = useUsuarioStore((s) => s.usuarioLogado);

  useEffect(() => {
    localStorage.setItem("carrinho", JSON.stringify(carrinho));
  }, [carrinho]);

  const adicionarProduto = (produto: Produto) => {
    setCarrinho((prevCarrinho: ProdCarrinho[]) => {
      const existe = prevCarrinho.find((item) => item.idProduto === produto.id);
      if (existe) {
        return prevCarrinho.map((item) =>
          item.idProduto === produto.id
            ? { ...item, quantidade: item.quantidade + 1 }
            : item
        );
      } else {
        return [...prevCarrinho, { idProduto: produto.id!, quantidade: 1 }];
      }
    });
  };

  const subtrairProduto = (produto: Produto) => {
    setCarrinho((prevCarrinho: ProdCarrinho[]) => {
      const existe = prevCarrinho.find((item) => item.idProduto === produto.id);
      if (existe) {
        const novoCarrinho = prevCarrinho.map((item) =>
          item.idProduto === produto.id
            ? { ...item, quantidade: item.quantidade - 1 }
            : item
        );
        return novoCarrinho.filter((item) => item.quantidade > 0);
      } else {
        return prevCarrinho;
      }
    });
  };

  const alterarQuantidade = (produto: Produto, novaQuantidade: number) => {
    setCarrinho((prevCarrinho: ProdCarrinho[]) => {
      const existe = prevCarrinho.find((item) => item.idProduto === produto.id);
      if (existe) {
        const novoCarrinho = prevCarrinho.map((item) =>
          item.idProduto === produto.id
            ? { ...item, quantidade: novaQuantidade }
            : item
        );
        return novoCarrinho.filter((item) => item.quantidade > 0);
      } else {
        return prevCarrinho;
      }
    });
  };

  const { data: produtos, isPending: carregandoProdutos, error: errorProdutos } = useRecuperarProdutos();

  if (carregandoProdutos) return <p>Carregando carrinho...</p>;
  if (errorProdutos) throw errorProdutos;

  const produtosNoCarrinho: ProdutoComCarrinho[] = [];
  produtos.forEach((produto) => {
    const prodCarrinho = carrinho.find((item) => item.idProduto === produto.id);
    if (prodCarrinho) produtosNoCarrinho.push({ produto, prodCarrinho });
  });

  if (produtosNoCarrinho.length === 0)
    return (
      <>
        <h4 className="mb-4">Carrinho vazio!</h4>
        <div>
          <Link to="/" className="btn btn-secondary">
            Continuar Comprando
          </Link>
        </div>
      </>
    );

  return (
    <div className="container mt-4">
      <section className="carrinho-table-container">
        <h1 className="carrinho-titulo">Seu Carrinho</h1>
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
              {produtosNoCarrinho.map(({ produto, prodCarrinho }) => {
                const subtotalPadrao = (produto.preco * prodCarrinho.quantidade).toLocaleString('pt-BR', { minimumFractionDigits: 2 });
                const editingValue = editingSubtotals[produto.id!];
                return (
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
                          onClick={() => subtrairProduto(produto)}
                          disabled={prodCarrinho.quantidade <= 1}
                        >-</button>
                        <input
                          type="number"
                          className="form-control text-center carrinho-quantidade-input"
                          value={prodCarrinho.quantidade}
                          min={1}
                          onChange={(e) => {
                            const valor = e.target.valueAsNumber;
                            if (!Number.isNaN(valor) && valor > 0) {
                              alterarQuantidade(produto, valor);
                            }
                          }}
                        />
                        <button
                          className="btn btn-outline-secondary carrinho-btn"
                          onClick={() => adicionarProduto(produto)}
                        >+</button>
                      </div>
                    </td>
                    <td className="text-center carrinho-subtotal">
                    R$ {(produto.preco * prodCarrinho.quantidade).toLocaleString("pt-BR", {
                      minimumFractionDigits: 2,
                      maximumFractionDigits: 2,
                      useGrouping: true,
                    })}
                    </td>
                    <td className="text-center">
                      <button
                        className="btn btn-danger btn-sm carrinho-remover-btn"
                        onClick={() => alterarQuantidade(produto, 0)}
                      >
                        Remover
                      </button>
                    </td>
                  </tr>
                );
              })}
            </tbody>
          </table>
        </div>
        <div className="row align-items-center mt-4" style={{ maxWidth: 900, margin: '0 auto'}}>
          <div className="col-md-6 d-flex justify-content-start">
            <Link to="/" className="btn carrinho-btn-lg carrinho-btn-continuar" style={{padding: "12px 0", minHeight: 48, display: 'flex', alignItems: 'center', fontWeight: 600, fontSize: '1.15rem', borderRadius: 8, minWidth: 200, padding: "0px 15px"}}>
              Continuar Comprando
            </Link>
          </div>
          <div className="col-md-6 d-flex justify-content-end align-items-center gap-3">
            <button
              className="btn btn-primary carrinho-btn-lg"
              style={{
                padding: "0px 15px",
                minHeight: 48,
                fontWeight: 600,
                borderRadius: 8,
                minWidth: 200,
                display: 'flex',
                alignItems: 'center',
                justifyContent: 'center',
                opacity: usuarioId ? 1 : 0.6,
                backgroundColor: '#0d6efd',
                borderColor: '#0d6efd',
                color: '#fff',
              }}
              onClick={() => {
                if (!usuarioId) return;
                window.alert('Compra realizada com sucesso!');
                setCarrinho([]);
                localStorage.removeItem('carrinho');
              }}
            >
              Finalizar Compra
            </button>
            <h4 style={{ color: '#7c5e3c', fontWeight: 700, margin: 0, minWidth: 180, textAlign: 'center', display: 'flex', alignItems: 'center', height: 48 }}>
              Total: R$ {produtosNoCarrinho
                .map(({ produto, prodCarrinho }) =>
                  produto.preco * prodCarrinho.quantidade
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

export default CarrinhoPage;