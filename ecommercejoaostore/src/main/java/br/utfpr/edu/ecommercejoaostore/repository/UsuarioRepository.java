package br.utfpr.edu.ecommercejoaostore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.utfpr.edu.ecommercejoaostore.model.Usuario;

public interface UsuarioRepository extends JpaRepository <Usuario, Integer> {
	
	

}
