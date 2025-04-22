package com.lojacosmeticos.lojacosmeticos.Spring.service;

import com.lojacosmeticos.lojacosmeticos.Spring.model.*;
import com.lojacosmeticos.lojacosmeticos.Spring.repository.EntradaEstoqueRepository;
import com.lojacosmeticos.lojacosmeticos.Spring.repository.FornecedorRepository;
import com.lojacosmeticos.lojacosmeticos.Spring.repository.ProdutoRepository;
import com.lojacosmeticos.lojacosmeticos.Spring.repository.SaidaEstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private FornecedorRepository fornecedorRepository;

    public Produto salvarProduto(Produto produto) {
        if (produto.getNomeProduto() == null || produto.getPrecoProduto() == null || produto.getPrecoProduto() <= 0) {
            throw new IllegalArgumentException("Nome do produto e preço são obrigatórios e o preço deve ser maior que zero.");
        }

        if (produto.getFornecedor() == null || !fornecedorRepository.existsById(produto.getFornecedor().getId())) {
            throw new IllegalArgumentException("Fornecedor não encontrado.");
        }

        return produtoRepository.save(produto);
    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscarPorId(Long id) {
        return produtoRepository.findById(id);
    }

    public Produto atualizarProduto(Long id, Produto produto) throws Exception {
        Produto produtoExistente = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado."));

        if (produto.getNomeProduto() == null || produto.getPrecoProduto() == null || produto.getPrecoProduto() <= 0) {
            throw new IllegalArgumentException("Nome do produto e preço são obrigatórios e o preço deve ser maior que zero.");
        }

        if (produto.getFornecedor() == null || !fornecedorRepository.existsById(produto.getFornecedor().getId())) {
            throw new IllegalArgumentException("Fornecedor não encontrado.");
        }

        produtoExistente.setNomeProduto(produto.getNomeProduto());
        produtoExistente.setDescricao(produto.getDescricao());
        produtoExistente.setPrecoProduto(produto.getPrecoProduto());
        produtoExistente.setCategoriaProdutos(produto.getCategoriaProdutos());
        produtoExistente.setFornecedor(produto.getFornecedor());

        return produtoRepository.save(produtoExistente);
    }

    public void deletarProduto(Long id) {
        produtoRepository.deleteById(id);
    }
    }


