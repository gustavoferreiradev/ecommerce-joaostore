package br.utfpr.edu.ecommercejoaostore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.utfpr.edu.ecommercejoaostore.model.Usuario;
import br.utfpr.edu.ecommercejoaostore.repository.UsuarioRepository;
import br.utfpr.edu.ecommercejoaostore.service.UsuarioService;

@Service
public class UsuarioServiceImpl extends CrudServiceImpl <Usuario,Integer> implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	protected JpaRepository<Usuario, Integer> getRepository() {
		return usuarioRepository;
	}

}
