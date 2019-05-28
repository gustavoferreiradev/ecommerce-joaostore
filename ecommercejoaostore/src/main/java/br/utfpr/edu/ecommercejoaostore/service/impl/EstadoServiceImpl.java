package br.utfpr.edu.ecommercejoaostore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import br.utfpr.edu.ecommercejoaostore.model.Estado;
import br.utfpr.edu.ecommercejoaostore.repository.EstadoRepository;

public class EstadoServiceImpl extends CrudServiceImpl<Estado, Integer> {

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Override
	protected JpaRepository<Estado, Integer> getRepository() {
		return estadoRepository;
	}

}
