package com.lojacosmeticos.lojacosmeticos.Spring.service;

import com.lojacosmeticos.lojacosmeticos.Spring.model.*;
import com.lojacosmeticos.lojacosmeticos.Spring.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class EstoqueService {
    @Autowired
    private EstoqueRepository estoqueRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public void atualizarQuantidadeEstoque(Long produtoId, int quantidade, boolean entrada) {
        Estoque estoque = estoqueRepository.findByProdutoId(produtoId)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado no estoque!"));

        if (entrada) {
            estoque.setQuantidadeAtual(estoque.getQuantidadeAtual() + quantidade);
        } else {
            if (estoque.getQuantidadeAtual() < quantidade) {
                throw new IllegalArgumentException("Estoque insuficiente para a saída!");
            }
            estoque.setQuantidadeAtual(estoque.getQuantidadeAtual() - quantidade);
        }
        estoqueRepository.save(estoque);
    }

    public Estoque criarEstoque(Produto produto, Integer quantidadeInicial) {
        Estoque estoque;
        estoque = new Estoque();
        estoque.setProduto(produto);
        estoque.setQuantidadeAtual(quantidadeInicial);
        return estoqueRepository.save(estoque);
    }
}
