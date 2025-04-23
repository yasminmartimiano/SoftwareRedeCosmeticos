package com.lojacosmeticos.lojacosmeticos.Spring.service;

import com.lojacosmeticos.lojacosmeticos.Spring.model.*;
import com.lojacosmeticos.lojacosmeticos.Spring.repository.EntradaEstoqueRepository;
import com.lojacosmeticos.lojacosmeticos.Spring.repository.EstoqueRepository;
import com.lojacosmeticos.lojacosmeticos.Spring.repository.FornecedorRepository;
import com.lojacosmeticos.lojacosmeticos.Spring.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EntradaEstoqueService {
    @Autowired
    private EntradaEstoqueRepository entradaEstoqueRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired

    private FornecedorRepository fornecedorRepository;

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Transactional
    public EntradaEstoque registrarEntrada(EntradaEstoque entradaEstoque) {
        if (entradaEstoque.getQuantidade() <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }

        if (entradaEstoque.getProduto() == null || !produtoRepository.existsById(entradaEstoque.getProduto().getId())) {
            throw new IllegalArgumentException("Produto não encontrado.");
        }

        if (entradaEstoque.getEstoque() == null || !estoqueRepository.existsById(entradaEstoque.getEstoque().getId())) {
            throw new IllegalArgumentException("Estoque não encontrado.");
        }

        if (entradaEstoque.getFornecedor() == null || !fornecedorRepository.existsById(entradaEstoque.getFornecedor().getId())) {
            throw new IllegalArgumentException("Fornecedor não encontrado.");
        }

        Estoque estoque = estoqueRepository.findById(entradaEstoque.getEstoque().getId())
                .orElseThrow(() -> new IllegalArgumentException("Estoque não encontrado"));

        if (estoque.getQuantidadeAtual() == null) {
            estoque.setQuantidadeAtual(0);
        }

        estoque.setQuantidadeAtual(estoque.getQuantidadeAtual() + entradaEstoque.getQuantidade());
        estoqueRepository.save(estoque);

        return entradaEstoqueRepository.save(entradaEstoque);
    }

    public List<EntradaEstoque> listarTodas() {
        return entradaEstoqueRepository.findAll();
    }

    public Optional<EntradaEstoque> buscarPorId(Long id) {
        return entradaEstoqueRepository.findById(id);
    }

    public void deletarPorId(Long id) {
        entradaEstoqueRepository.deleteById(id);
    }

}