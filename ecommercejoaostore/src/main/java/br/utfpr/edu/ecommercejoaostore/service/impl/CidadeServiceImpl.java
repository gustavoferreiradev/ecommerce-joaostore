package br.utfpr.edu.ecommercejoaostore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.utfpr.edu.ecommercejoaostore.model.Cidade;
import br.utfpr.edu.ecommercejoaostore.repository.CidadeRepository;
import br.utfpr.edu.ecommercejoaostore.service.CidadeService;

@Service
public class CidadeServiceImpl extends CrudServiceImpl <Cidade, Integer> implements CidadeService{
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	protected JpaRepository <Cidade, Integer> getRepository(){
		return cidadeRepository;
	}
	
}
