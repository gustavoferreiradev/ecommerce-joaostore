package br.utfpr.edu.ecommercejoaostore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.utfpr.edu.ecommercejoaostore.model.Estado;
import br.utfpr.edu.ecommercejoaostore.repository.EstadoRepository;
import br.utfpr.edu.ecommercejoaostore.service.EstadoService;

@Service
public class EstadoServiceImpl extends CrudServiceImpl  <Estado, Integer>
		implements EstadoService{

	@Autowired
	private EstadoRepository estadoRepository;
	
	@Override
	protected JpaRepository<Estado, Integer> getRepository() {
		return estadoRepository;
	}

}
