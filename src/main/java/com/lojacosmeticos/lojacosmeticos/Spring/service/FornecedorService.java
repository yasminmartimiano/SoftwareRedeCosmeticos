package com.lojacosmeticos.lojacosmeticos.Spring.service;


import com.lojacosmeticos.lojacosmeticos.Spring.model.EnderecoFornecedor;
import com.lojacosmeticos.lojacosmeticos.Spring.model.Fornecedor;
import com.lojacosmeticos.lojacosmeticos.Spring.repository.EnderecoFornecedorRepository;
import com.lojacosmeticos.lojacosmeticos.Spring.repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class FornecedorService {
    @Autowired
    private FornecedorRepository fornecedorRepository;
    @Autowired
    EnderecoFornecedorRepository enderecoFornecedorRepository;

    public Fornecedor salvar(Fornecedor fornecedor) {
        if (fornecedorRepository.existsByCnpj(fornecedor.getCnpj())) {
            throw new IllegalArgumentException("CNPJ já cadastrado.");
        }

        if (fornecedor.getCnpj() == null || fornecedor.getRazaoSocial() == null ||
                fornecedor.getNomeFantasia() == null || fornecedor.getEmail() == null ||
                fornecedor.getTelefone() == null ) {
            throw new IllegalArgumentException("Todos os campos obrigatórios devem ser preenchidos.");
        }

        EnderecoFornecedor endereco = fornecedor.getEnderecoFornecedor();
        if (endereco == null || !enderecoFornecedorRepository.existsById(endereco.getId())) {
            throw new IllegalArgumentException("Endereço do fornecedor inválido.");
        }

        return fornecedorRepository.save(fornecedor);

    }

    public List<Fornecedor> listarTodos() {
        return fornecedorRepository.findAll();
    }

    public Fornecedor buscarPorId(Long id) {
        Optional<Fornecedor> fornecedor = fornecedorRepository.findById(id);
        if (fornecedor.isPresent()) {
            return  fornecedor.get();
        } else {
            throw new RuntimeException("Fornecedor não encontrado com o id: " + id);
        }
    }


    public Fornecedor atualizar(Long id, Fornecedor fornecedor) {
        Fornecedor fornecedorExistente = fornecedorRepository.findById(id).orElseThrow(() -> new RuntimeException("Fornecedor não encontrado."));

        if (!fornecedorExistente.getCnpj().equals(fornecedor.getCnpj()) && fornecedorRepository.existsByCnpj(fornecedor.getCnpj())) {
            throw new IllegalArgumentException("CNPJ já cadastrado.");
        }

        if (fornecedor.getCnpj() == null || fornecedor.getRazaoSocial() == null || fornecedor.getNomeFantasia() == null ||
                fornecedor.getEmail() == null || fornecedor.getTelefone() == null ) {
            throw new IllegalArgumentException("Todos os campos obrigatórios devem ser preenchidos.");
        }

        fornecedorExistente.setCnpj(fornecedor.getCnpj());
        fornecedorExistente.setRazaoSocial(fornecedor.getRazaoSocial());
        fornecedorExistente.setNomeFantasia(fornecedor.getNomeFantasia());
        fornecedorExistente.setEmail(fornecedor.getEmail());
        fornecedorExistente.setTelefone(fornecedor.getTelefone());

        fornecedorExistente.setEnderecoFornecedor(fornecedor.getEnderecoFornecedor());

        return fornecedorRepository.save(fornecedorExistente);
    }

    public void excluir(Long id) {
        fornecedorRepository.deleteById(id);
    }
}
