package com.carlosribeiro.apirestfulv1.controller;

import com.carlosribeiro.apirestfulv1.model.Produto;
import com.carlosribeiro.apirestfulv1.model.ResultadoPaginado;
import com.carlosribeiro.apirestfulv1.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("produtos")  // http://localhost:8080/produtos
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping   // Requisição do tipo GET para http://localhost:8080/produtos
    public List<Produto> recuperarProdutos() {
//        if (true) {
//            throw new RuntimeException("Deu erro no servidor");
//        }
        return produtoService.recuperarProdutos();
    }

    // Requisição do tipo GET para http://localhost:8080/produtos/1
    @GetMapping("{idProduto}")
    public Produto recuperarProdutoPorId(@PathVariable("idProduto") long id) {
        return produtoService.recuperarProdutoPorId(id);
    }

    // Requisição do tipo GET para http://localhost:8080/produtos/categoria/frutas
    @GetMapping("categoria/{slugCategoria}")
    public List<Produto> recuperarProdutosPorSlugCategoria(@PathVariable("slugCategoria") String slugCategoria) {
        return produtoService.recuperarProdutosPorSlugCategoria(slugCategoria);
    }

    @PostMapping
    public Produto cadastraProduto(@RequestBody Produto produto) {
        return produtoService.cadastrarProduto(produto);
    }

    @PutMapping
    public Produto alterarProduto(@RequestBody Produto produto) {
        return produtoService.alterarProduto(produto);
    }

    @DeleteMapping  ("{idProduto}")   // http://localhost:8080/produtos/1
    public void removerProduto(@PathVariable("idProduto") long id) {
        produtoService.removerProduto(id);
    }

    // Entradas
    // - pagina corrente
    // - tamanho da página
    // Saídas:
    // - total de itens
    // - total de páginas
    // - pagina corrente
    // - itens da página corrente

    // Requisição do tipo GET para
    // http://localhost:8080/produtos/paginacao?pagina=0&tamanho=5&nome=ce
    @GetMapping("paginacao")
    public ResultadoPaginado<Produto> recuperarProdutosComPaginacao(
            @RequestParam(value = "pagina", defaultValue = "0") int pagina,
            @RequestParam(value = "tamanho", defaultValue = "5") int tamanho,
            @RequestParam(value = "nome", defaultValue = "") String nome) {
        Pageable pageable = PageRequest.of(pagina, tamanho);
        Page<Produto> page = produtoService.recuperarProdutosComPaginacao(pageable, nome);
        ResultadoPaginado<Produto> resultadoPaginado = new ResultadoPaginado<>(
                page.getTotalElements(),
                page.getTotalPages(),
                page.getNumber(),
                page.getContent());
        return resultadoPaginado;
    }

    // http://localhost:8080/produtos/categoria/paginacao?pagina=0&tamanho=5&slugCategoria=frutas
    @GetMapping("categoria/paginacao")
    public ResultadoPaginado<Produto> recuperarProdutosPaginadosPorSlugDaCategoria(
            @RequestParam(value = "pagina", defaultValue = "0") int pagina,
            @RequestParam(value = "tamanho", defaultValue = "3") int tamanho,
            @RequestParam(value = "slugCategoria", defaultValue = "") String slugCategoria) {
        Pageable pageable = PageRequest.of(pagina, tamanho);
        Page<Produto> page = produtoService.recuperarProdutosPaginadosPorSlugDaCategoria(slugCategoria, pageable);
        ResultadoPaginado<Produto> resultadoPaginado = new ResultadoPaginado<>(
                page.getTotalElements(),
                page.getTotalPages(),
                page.getNumber(),
                page.getContent());
        return resultadoPaginado;
    }
}
