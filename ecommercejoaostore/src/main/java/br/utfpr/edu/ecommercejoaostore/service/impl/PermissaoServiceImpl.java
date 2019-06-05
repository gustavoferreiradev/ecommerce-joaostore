package br.utfpr.edu.ecommercejoaostore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.utfpr.edu.ecommercejoaostore.model.Permissao;
import br.utfpr.edu.ecommercejoaostore.repository.PermissaoRepository;
import br.utfpr.edu.ecommercejoaostore.service.PermissaoService;

@Service
public class PermissaoServiceImpl extends CrudServiceImpl <Permissao, Integer>
				implements PermissaoService{
	
	@Autowired
	private PermissaoRepository permissaoRepository;

	@Override
	protected JpaRepository<Permissao, Integer> getRepository() {
		return permissaoRepository;
	}
	
	

}
