package com.lojacosmeticos.lojacosmeticos.Spring.model;

import jakarta.persistence.*;

@Entity
@Table(name = "endereco_fornecedor")
public class EnderecoFornecedor extends Endereco {
    @OneToOne(mappedBy = "enderecoFornecedor")
    private Fornecedor fornecedor;

    public EnderecoFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public EnderecoFornecedor(Long id, String rua, String numero, String bairro, String cidade, String estado, String cep, Fornecedor fornecedor) {
        super(id, rua, numero, bairro, cidade, estado, cep);
        this.fornecedor = fornecedor;
    }

    public EnderecoFornecedor() {
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
}
