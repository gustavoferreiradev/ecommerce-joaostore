package br.utfpr.edu.ecommercejoaostore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.utfpr.edu.ecommercejoaostore.model.Compra;
import br.utfpr.edu.ecommercejoaostore.repository.CompraRepository;
import br.utfpr.edu.ecommercejoaostore.service.CompraService;

@Service
public class CompraServiceImpl extends CrudServiceImpl <Compra, Integer> implements CompraService {

	@Autowired
	private CompraRepository compraRepository;

	@Override
	protected JpaRepository<Compra, Integer> getRepository() {
		return compraRepository;
	}
	
}
