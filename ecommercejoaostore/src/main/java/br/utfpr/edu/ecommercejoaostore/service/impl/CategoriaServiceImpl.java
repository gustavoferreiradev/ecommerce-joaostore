package br.utfpr.edu.ecommercejoaostore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.utfpr.edu.ecommercejoaostore.model.Categoria;
import br.utfpr.edu.ecommercejoaostore.repository.CategoriaRepository;
import br.utfpr.edu.ecommercejoaostore.service.CategoriaService;

@Service
public class CategoriaServiceImpl extends CrudServiceImpl <Categoria, Integer> implements CategoriaService{
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	protected JpaRepository<Categoria, Integer> getRepository() {
		return categoriaRepository;
	}
	
	

}
