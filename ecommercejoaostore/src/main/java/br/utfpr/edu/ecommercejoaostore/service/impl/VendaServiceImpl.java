package br.utfpr.edu.ecommercejoaostore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.utfpr.edu.ecommercejoaostore.model.Compra;
import br.utfpr.edu.ecommercejoaostore.model.Venda;
import br.utfpr.edu.ecommercejoaostore.repository.CompraRepository;
import br.utfpr.edu.ecommercejoaostore.repository.VendaRepository;
import br.utfpr.edu.ecommercejoaostore.service.CompraService;
import br.utfpr.edu.ecommercejoaostore.service.VendaService;

@Service
public class VendaServiceImpl extends CrudServiceImpl <Venda, Integer> implements VendaService {

	@Autowired
	private VendaRepository vendaRepository;

	@Override
	protected JpaRepository<Venda, Integer> getRepository() {
		return vendaRepository;
	}
	
}
