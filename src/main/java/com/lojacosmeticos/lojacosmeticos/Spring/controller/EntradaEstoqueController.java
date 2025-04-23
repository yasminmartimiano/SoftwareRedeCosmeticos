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

import java.util.List;

@RestController
@RequestMapping("/entrada-estoque")
public class EntradaEstoqueController {

    @Autowired
    private EntradaEstoqueService entradaEstoqueService;

    @PostMapping
    public ResponseEntity<EntradaEstoque> registrarEntrada(@RequestBody EntradaEstoque entradaEstoque) {
        try {
            EntradaEstoque entradaRegistrada = entradaEstoqueService.registrarEntrada(entradaEstoque);
            return ResponseEntity.status(HttpStatus.CREATED).body(entradaRegistrada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping
    public List<EntradaEstoque> listarEntradas() {
        return entradaEstoqueService.listarTodas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntradaEstoque> buscarPorId(@PathVariable Long id) {
        return entradaEstoqueService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        entradaEstoqueService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}


