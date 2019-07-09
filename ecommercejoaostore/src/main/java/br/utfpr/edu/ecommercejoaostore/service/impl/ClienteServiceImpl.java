package br.utfpr.edu.ecommercejoaostore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.utfpr.edu.ecommercejoaostore.model.Cidade;
import br.utfpr.edu.ecommercejoaostore.model.Cliente;
import br.utfpr.edu.ecommercejoaostore.repository.CidadeRepository;
import br.utfpr.edu.ecommercejoaostore.repository.ClienteRepository;
import br.utfpr.edu.ecommercejoaostore.service.ClienteService;

@Service
public class ClienteServiceImpl extends CrudServiceImpl <Cliente, Integer> implements ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	protected JpaRepository <Cliente, Integer> getRepository(){
		return clienteRepository;
	}
}
