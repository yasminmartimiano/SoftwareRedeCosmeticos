package com.lojacosmeticos.lojacosmeticos.Spring.controller;

import com.lojacosmeticos.lojacosmeticos.Spring.model.Estoque;
import com.lojacosmeticos.lojacosmeticos.Spring.model.SaidaEstoque;
import com.lojacosmeticos.lojacosmeticos.Spring.repository.EstoqueRepository;
import com.lojacosmeticos.lojacosmeticos.Spring.repository.SaidaEstoqueRepository;
import com.lojacosmeticos.lojacosmeticos.Spring.service.SaidaEstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/saida_estoque")
public class SaidaEstoqueController {

    @Autowired
    private SaidaEstoqueRepository saidaEstoqueRepository;

    @Autowired
    private EstoqueRepository estoqueRepository;

    @PostMapping
    public ResponseEntity<String> registrarSaida(@RequestBody SaidaEstoque saida) {
        try {
            Estoque estoque = saida.getEstoque();
            if (estoque.getQuantidadeAtual() < saida.getQuantidade()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Estoque insuficiente para essa saída!");
            }
            estoque.setQuantidadeAtual(estoque.getQuantidadeAtual() - saida.getQuantidade());
            estoqueRepository.save(estoque);
            saidaEstoqueRepository.save(saida);
            return ResponseEntity.ok("Saída registrada e estoque atualizado!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao registrar saída: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> listarSaidas() {
        try {
            return ResponseEntity.ok(saidaEstoqueRepository.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao listar saídas: " + e.getMessage());
        }
    }
}





