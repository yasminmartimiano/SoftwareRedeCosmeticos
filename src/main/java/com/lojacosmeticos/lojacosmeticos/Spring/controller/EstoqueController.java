package com.lojacosmeticos.lojacosmeticos.Spring.controller;

import com.lojacosmeticos.lojacosmeticos.Spring.model.Estoque;
import com.lojacosmeticos.lojacosmeticos.Spring.repository.EstoqueRepository;
import com.lojacosmeticos.lojacosmeticos.Spring.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {

    @Autowired
    private EstoqueRepository estoqueRepository;

    private EstoqueService estoqueService;

    public EstoqueController(EstoqueService estoqueService){
        this.estoqueService = estoqueService;
    }


    @GetMapping
    public List<Estoque> listarEstoque() {
        return estoqueRepository.findAll();
    }

    @PostMapping
    public Estoque criarEstoque(@RequestBody Estoque estoque) {
        return estoqueRepository.save(estoque);
    }
    @GetMapping("/deletar/{id}")
    public String deletarproduto(@PathVariable Long id) {
        estoqueRepository.deleteById(id);
        return "redirect:/estoque/lista-estoque";
    }


}
