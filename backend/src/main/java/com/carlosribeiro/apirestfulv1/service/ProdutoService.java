package com.carlosribeiro.apirestfulv1.service;

import com.carlosribeiro.apirestfulv1.exception.EntidadeNaoEncontradaException;
import com.carlosribeiro.apirestfulv1.model.Produto;
import com.carlosribeiro.apirestfulv1.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> recuperarProdutos() {
        return produtoRepository.recuperarProdutosComCategoria();
    }

    public Produto cadastrarProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

//    public Produto alterarProduto(Produto produto) {
//        Optional<Produto> opt = produtoRepository.findById(produto.getId());
//        if (opt.isPresent()) {
//            return produtoRepository.save(produto);
//        }
//        throw new ProdutoNaoEncontradoException(
//                "Produto número " + produto.getId() + " não encontrado.");
//    }

//    @Transactional
//    public Produto alterarProduto(Produto produto) {
//        Optional<Produto> opt = produtoRepository.recuperarProdutoPorIdComLock(produto.getId());
//        if (opt.isPresent()) {
//            return produtoRepository.save(produto);
//        }
//        throw new ProdutoNaoEncontradoException(
//                "Produto número " + produto.getId() + " não encontrado.");
//    }

    @Transactional
    public Produto alterarProduto(Produto produto) {
        produtoRepository.recuperarProdutoPorIdComLock(produto.getId())
            .orElseThrow(() -> new EntidadeNaoEncontradaException(
                    "Produto número " + produto.getId() + " não encontrado."));
        return produtoRepository.save(produto);
    }

    @Transactional(rollbackFor = Exception.class)
    public void removerProduto(long id) {
        produtoRepository.deleteById(id);
//        produtoRepository.deleteById(1L);
//        if (true) {
//            throw new Exception("Deu erro!");
//        }
//        produtoRepository.deleteById(2L);
    }

    public Produto recuperarProdutoPorId(long id) {
        return produtoRepository.recuperarProdutoPorId(id)
            .orElseThrow(() -> new EntidadeNaoEncontradaException(
                "Produto número " + id + " não encontrado."));
    }

    public Page<Produto> recuperarProdutosComPaginacao(Pageable pageable, String nome) {
        return produtoRepository.recuperarProdutosComPaginacao(pageable, "%" + nome + "%");
    }

    public List<Produto> recuperarProdutosPorSlugCategoria(String slugCategoria) {
        return produtoRepository.recuperarProdutosPorSlugCategoria(slugCategoria);
    }

    public Page<Produto> recuperarProdutosPaginadosPorSlugDaCategoria(String slugCategoria, Pageable pageable) {
        if(!slugCategoria.isEmpty()) {
            return produtoRepository.recuperarProdutosPaginadosPorSlugDaCategoria(slugCategoria, pageable);
        }
        else {
            return produtoRepository.recuperarProdutosPaginados(pageable);
        }
    }
}
