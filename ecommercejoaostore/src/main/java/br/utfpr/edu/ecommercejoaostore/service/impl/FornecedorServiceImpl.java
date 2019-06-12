package br.utfpr.edu.ecommercejoaostore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.utfpr.edu.ecommercejoaostore.model.Fornecedor;
import br.utfpr.edu.ecommercejoaostore.repository.FornecedorRepository;
import br.utfpr.edu.ecommercejoaostore.service.FornecedorService;

@Service
public class FornecedorServiceImpl extends CrudServiceImpl <Fornecedor, Integer> implements FornecedorService  {

	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	@Override
	protected JpaRepository<Fornecedor, Integer> getRepository() {		
		return fornecedorRepository;
	}
	
	

}
