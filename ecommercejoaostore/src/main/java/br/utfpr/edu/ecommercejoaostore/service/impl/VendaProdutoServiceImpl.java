package br.utfpr.edu.ecommercejoaostore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.utfpr.edu.ecommercejoaostore.model.CompraProduto;
import br.utfpr.edu.ecommercejoaostore.model.VendaProduto;
import br.utfpr.edu.ecommercejoaostore.repository.CompraProdutoRepository;
import br.utfpr.edu.ecommercejoaostore.repository.VendaProdutoRepository;
import br.utfpr.edu.ecommercejoaostore.service.CompraProdutoService;
import br.utfpr.edu.ecommercejoaostore.service.VendaProdutoService;

@Service
public class VendaProdutoServiceImpl extends CrudServiceImpl <VendaProduto, Integer> implements VendaProdutoService
{
	@Autowired
	private VendaProdutoRepository vendaProdutoRepository;
	
	@Override
	protected JpaRepository<VendaProduto, Integer> getRepository() {
		
		return vendaProdutoRepository;
	}
	
}
