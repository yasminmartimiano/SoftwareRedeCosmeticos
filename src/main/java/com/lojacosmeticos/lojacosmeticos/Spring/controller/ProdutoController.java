package com.lojacosmeticos.lojacosmeticos.Spring.controller;

import com.lojacosmeticos.lojacosmeticos.Spring.model.Produto;
import com.lojacosmeticos.lojacosmeticos.Spring.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<?> salvarProduto(@RequestBody Produto produto) {
        try {
            Produto novoProduto = produtoService.salvarProduto(produto);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar produto: " + e.getMessage());
        }
    }



    @GetMapping
    public ResponseEntity<?> listarTodos() {
        try {
            return ResponseEntity.ok(produtoService.listarTodos());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao listar produtos: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            Optional<Produto> produto = produtoService.buscarPorId(id);
            if (produto.isPresent()) {
                return ResponseEntity.ok(produto.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Produto não encontrado!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao buscar produto: " + e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarProduto(@PathVariable Long id, @RequestBody Produto produto) {
        try {
            Produto atualizado = produtoService.atualizarProduto(id, produto);
            return ResponseEntity.ok(atualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarProduto(@PathVariable Long id) {
        try {
            produtoService.deletarProduto(id);
            return ResponseEntity.ok("Produto deletado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao deletar produto: " + e.getMessage());
        }
    }
}
