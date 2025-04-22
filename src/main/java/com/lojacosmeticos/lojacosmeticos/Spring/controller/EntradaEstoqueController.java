package com.lojacosmeticos.lojacosmeticos.Spring.controller;

import com.lojacosmeticos.lojacosmeticos.Spring.model.EntradaEstoque;
import com.lojacosmeticos.lojacosmeticos.Spring.model.Estoque;
import com.lojacosmeticos.lojacosmeticos.Spring.repository.EntradaEstoqueRepository;
import com.lojacosmeticos.lojacosmeticos.Spring.repository.EstoqueRepository;
import com.lojacosmeticos.lojacosmeticos.Spring.service.EntradaEstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/entrada-estoque")
public class EntradaEstoqueController {

    @Autowired
    private EntradaEstoqueRepository entradaEstoqueRepository;

    @Autowired
    private EstoqueRepository estoqueRepository;

    @PostMapping
    public ResponseEntity<String> registrarEntrada(@RequestBody EntradaEstoque entrada) {
        try {
            Estoque estoque = entrada.getEstoque();
            estoque.setQuantidadeAtual(estoque.getQuantidadeAtual() + entrada.getQuantidade());
            estoqueRepository.save(estoque);
            entradaEstoqueRepository.save(entrada);
            return ResponseEntity.ok("Entrada registrada e estoque atualizado!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao registrar entrada: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> listarEntradas() {
        try {
            return ResponseEntity.ok(entradaEstoqueRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao listar entradas: " + e.getMessage());
        }
    }
}


