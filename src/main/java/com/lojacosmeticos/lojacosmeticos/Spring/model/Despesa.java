package com.lojacosmeticos.lojacosmeticos.Spring.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "despesa")
public class Despesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "valor_despesa", nullable = false)
    private Double valor;

    @NotNull
    @Column(name = "data_pagamento", nullable = false)
    private Date dataPagamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_pagamento", nullable = true)
    private StatusPagamento statusPagamento;

    @NotNull
    @Column(name = "pagamento_id", nullable = false)
    private Pagamento pagamentoId;

    @ManyToOne
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;

    @Column(name = "funcionario_id")
    private Funcionario funcionario;


    public Despesa(Long id, Double valor, Date dataPagamento, StatusPagamento statusPagamento, Pagamento pagamentoId, Fornecedor fornecedor, Funcionario funcionario) {
        this.id = id;
        this.valor = valor;
        this.dataPagamento = dataPagamento;
        this.statusPagamento = statusPagamento;
        this.pagamentoId = pagamentoId;
        this.fornecedor = fornecedor;
        this.funcionario = funcionario;
    }


}
