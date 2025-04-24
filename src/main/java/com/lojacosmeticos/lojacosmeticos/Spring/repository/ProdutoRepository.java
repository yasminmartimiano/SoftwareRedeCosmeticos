package com.lojacosmeticos.lojacosmeticos.Spring.repository;

import com.lojacosmeticos.lojacosmeticos.Spring.model.Estoque;
import com.lojacosmeticos.lojacosmeticos.Spring.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {
}
