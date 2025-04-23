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

import java.util.List;

@RestController
@RequestMapping("/saida_estoque")
public class SaidaEstoqueController {

    @Autowired
    private SaidaEstoqueService saidaEstoqueService;

    @PostMapping
    public ResponseEntity<SaidaEstoque> registrarSaida(@RequestBody SaidaEstoque saidaEstoque) {
        try {
            SaidaEstoque saidaRegistrada = saidaEstoqueService.registrarSaida(saidaEstoque);
            return ResponseEntity.status(HttpStatus.CREATED).body(saidaRegistrada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping
    public List<SaidaEstoque> listarSaidas() {
        return saidaEstoqueService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaidaEstoque> buscarPorId(@PathVariable Long id) {
        return saidaEstoqueService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        saidaEstoqueService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}





