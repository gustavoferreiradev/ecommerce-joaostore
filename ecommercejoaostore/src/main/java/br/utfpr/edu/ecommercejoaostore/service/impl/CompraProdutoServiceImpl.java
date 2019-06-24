package br.utfpr.edu.ecommercejoaostore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.utfpr.edu.ecommercejoaostore.model.CompraProduto;
import br.utfpr.edu.ecommercejoaostore.repository.CompraProdutoRepository;
import br.utfpr.edu.ecommercejoaostore.service.CompraProdutoService;

@Service
public class CompraProdutoServiceImpl extends CrudServiceImpl <CompraProduto, Integer> implements CompraProdutoService
{
	@Autowired
	private CompraProdutoRepository compraProdutoRepository;
	
	@Override
	protected JpaRepository<CompraProduto, Integer> getRepository() {
		
		return compraProdutoRepository;
	}
	
}
