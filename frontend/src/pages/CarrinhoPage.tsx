import { useEffect, useState } from "react";
import Produto from "../interfaces/Produto";
import useRecuperarProdutos from "../hooks/useRecuperarProdutos";
import "./CarrinhoPage.css";

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
          <a href="./produtos" className="btn btn-secondary">
            Continuar Comprando
          </a>
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
                      <input
                        type="text"
                        inputMode="decimal"
                        className="form-control text-center carrinho-quantidade-input"
                        style={{ fontWeight: 600, color: '#7c5e3c', fontSize: 17, background: 'transparent', border: 'none', boxShadow: 'none', display: 'inline', width: 90 }}
                        value={editingValue !== undefined ? editingValue : subtotalPadrao}
                        onFocus={e => {
                          // Só seta o valor inicial se ainda não houver edição
                          setEditingSubtotals(prev => {
                            if (prev[produto.id! ] !== undefined) return prev;
                            return { ...prev, [produto.id!]: subtotalPadrao };
                          });
                          e.target.select();
                        }}
                        onChange={e => {
                          setEditingSubtotals(prev => ({ ...prev, [produto.id!]: e.target.value }));
                        }}
                        onBlur={e => {
                          const valorStr = e.target.value.replace(/\./g, '').replace(',', '.');
                          const valor = parseFloat(valorStr);
                          if (e.target.value.trim() === '' || isNaN(valor) || valor <= 0) {
                            setEditingSubtotals(prev => {
                              const novo = { ...prev };
                              delete novo[produto.id!];
                              return novo;
                            });
                          } else {
                            const novaQtd = Math.round(valor / produto.preco);
                            alterarQuantidade(produto, novaQtd);
                            setEditingSubtotals(prev => {
                              const novo = { ...prev };
                              delete novo[produto.id!];
                              return novo;
                            });
                          }
                        }}
                        onKeyDown={e => {
                          if (e.key === 'Enter') {
                            (e.target as HTMLInputElement).blur();
                          }
                        }}
                      />
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
        <div className="row align-items-center mt-4" style={{ maxWidth: 900, margin: '0 auto' }}>
          <div className="col-md-6 d-flex justify-content-start">
            <a href="./produtos" className="btn carrinho-btn-lg carrinho-btn-continuar">
              Continuar Comprando
            </a>
          </div>
          <div className="col-md-6 d-flex justify-content-end">
            <h4 style={{ marginRight: 24, color: '#7c5e3c', fontWeight: 700 }}>
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
            <button className="btn btn-primary carrinho-btn-lg">
              Finalizar Compra
            </button>
          </div>
        </div>
      </section>
    </div>
  );
};

export default CarrinhoPage;