package br.utfpr.edu.ecommercejoaostore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.utfpr.edu.ecommercejoaostore.model.Produto;
import br.utfpr.edu.ecommercejoaostore.repository.ProdutoRepository;
import br.utfpr.edu.ecommercejoaostore.service.ProdutoService;
@Service
public class ProdutoServiceImpl extends CrudServiceImpl <Produto, Integer> implements ProdutoService
{

	@Autowired
	private ProdutoRepository produtoRepository;

	@Override
	protected JpaRepository<Produto, Integer> getRepository() {
		// TODO Auto-generated method stub
		return produtoRepository;
	}
	
	
}
