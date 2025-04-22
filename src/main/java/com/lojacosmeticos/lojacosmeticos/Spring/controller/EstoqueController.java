package com.lojacosmeticos.lojacosmeticos.Spring.controller;

import com.lojacosmeticos.lojacosmeticos.Spring.model.Estoque;
import com.lojacosmeticos.lojacosmeticos.Spring.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

        @Autowired
        private EstoqueRepository estoqueRepository;

        @PostMapping
        public ResponseEntity<?> criarEstoque(@RequestBody Estoque estoque) {
            try {
                return ResponseEntity.ok(estoqueRepository.save(estoque));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao criar estoque: " + e.getMessage());
            }
        }

        @GetMapping
        public ResponseEntity<?> listarEstoques() {
            try {
                return ResponseEntity.ok(estoqueRepository.findAll());
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao listar estoques: " + e.getMessage());
            }
        }

        @PutMapping("/{id}")
        public ResponseEntity<?> atualizarEstoque(@PathVariable Long id, @RequestBody Estoque estoque) {
            try {
                Estoque existente = estoqueRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Estoque não encontrado"));
                estoque.setId(id);
                return ResponseEntity.ok(estoqueRepository.save(estoque));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atualizar estoque: " + e.getMessage());
            }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<?> deletarEstoque(@PathVariable Long id) {
            try {
                estoqueRepository.deleteById(id);
                return ResponseEntity.ok("Estoque deletado com sucesso!");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao deletar estoque: " + e.getMessage());
            }
        }
    }


