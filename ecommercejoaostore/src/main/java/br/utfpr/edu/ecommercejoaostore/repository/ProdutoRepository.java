package br.utfpr.edu.ecommercejoaostore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.utfpr.edu.ecommercejoaostore.model.Produto;

public interface ProdutoRepository extends JpaRepository <Produto, Integer> {

}
