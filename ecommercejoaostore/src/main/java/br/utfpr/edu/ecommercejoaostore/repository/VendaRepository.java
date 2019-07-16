package br.utfpr.edu.ecommercejoaostore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.utfpr.edu.ecommercejoaostore.model.Venda;

public interface VendaRepository extends JpaRepository <Venda, Integer> {
	
	

}
