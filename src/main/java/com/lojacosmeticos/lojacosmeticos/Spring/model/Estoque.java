package com.lojacosmeticos.lojacosmeticos.Spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "estoque")
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
    @Column(name = "quantidade_atual", nullable = false)
    private Integer quantidadeAtual;

    public Estoque() {
        this.quantidadeAtual = 0;
    }


    public Estoque(Long id, Produto produto, Integer quantidadeAtual) {
        this.id = id;
        this.produto = produto;
        this.quantidadeAtual = quantidadeAtual != null ? quantidadeAtual : 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidadeAtual() {
        return quantidadeAtual;
    }

    public void setQuantidadeAtual(Integer quantidadeAtual) {
        this.quantidadeAtual = quantidadeAtual;
    }
}
