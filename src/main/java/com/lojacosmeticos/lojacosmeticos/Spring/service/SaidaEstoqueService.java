package com.lojacosmeticos.lojacosmeticos.Spring.service;

import com.lojacosmeticos.lojacosmeticos.Spring.model.*;
import com.lojacosmeticos.lojacosmeticos.Spring.repository.EstoqueRepository;
import com.lojacosmeticos.lojacosmeticos.Spring.repository.FornecedorRepository;
import com.lojacosmeticos.lojacosmeticos.Spring.repository.ProdutoRepository;
import com.lojacosmeticos.lojacosmeticos.Spring.repository.SaidaEstoqueRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SaidaEstoqueService {
    @Autowired
    private SaidaEstoqueRepository saidaEstoqueRepository;

    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired

    private ProdutoRepository produtoRepository;
    @Autowired

    private FornecedorRepository fornecedorRepository;
    @Transactional


    public SaidaEstoque registrarSaida(SaidaEstoque saidaEstoque) throws Exception {
        if (saidaEstoque.getQuantidade() <= 0) {
            throw new IllegalArgumentException("A quantidade deve ser maior que zero.");
        }

        if (saidaEstoque.getProduto() == null || !produtoRepository.existsById(saidaEstoque.getProduto().getId())) {
            throw new IllegalArgumentException("Produto não encontrado.");
        }

        if (saidaEstoque.getEstoque() == null || !estoqueRepository.existsById(saidaEstoque.getEstoque().getId())) {
            throw new IllegalArgumentException("Estoque não encontrado.");
        }


        if (saidaEstoque.getFornecedor() == null || !fornecedorRepository.existsById(saidaEstoque.getFornecedor().getId())) {
            throw new IllegalArgumentException("Fornecedor não encontrado.");
        }

        Estoque estoque = estoqueRepository.findById(saidaEstoque.getEstoque().getId())
                .orElseThrow(() -> new IllegalArgumentException("Estoque não encontrado."));

        if (estoque.getQuantidadeAtual() == null || estoque.getQuantidadeAtual() < saidaEstoque.getQuantidade()) {
            throw new IllegalArgumentException("Quantidade em estoque insuficiente.");
        }

        estoque.setQuantidadeAtual(estoque.getQuantidadeAtual() - saidaEstoque.getQuantidade());
        estoqueRepository.save(estoque);

        return saidaEstoqueRepository.save(saidaEstoque);
    }


    public List<SaidaEstoque> listarTodas() {
        return saidaEstoqueRepository.findAll();
    }

    public Optional<SaidaEstoque> buscarPorId(Long id) {
        return saidaEstoqueRepository.findById(id);
    }

    public void deletarPorId(Long id) {
        saidaEstoqueRepository.deleteById(id);
    }
}
