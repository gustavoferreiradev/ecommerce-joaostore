package br.utfpr.edu.ecommercejoaostore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.utfpr.edu.ecommercejoaostore.model.Marca;
import br.utfpr.edu.ecommercejoaostore.repository.MarcaRepository;
import br.utfpr.edu.ecommercejoaostore.service.MarcaService;

@Service
public class MarcaServiceImpl extends CrudServiceImpl <Marca, Integer> implements MarcaService{
	
	@Autowired
	private MarcaRepository marcaRepository;
	
	@Override
	protected JpaRepository <Marca, Integer> getRepository(){
		return marcaRepository;
	}

}
