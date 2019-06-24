package br.utfpr.edu.ecommercejoaostore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.utfpr.edu.ecommercejoaostore.model.ImagemProduto;
import br.utfpr.edu.ecommercejoaostore.repository.ImagemProdutoRepository;
import br.utfpr.edu.ecommercejoaostore.service.ImagemProdutoService;

@Service
public class ImagemProdutoServiceImpl extends CrudServiceImpl <ImagemProduto, Integer> implements ImagemProdutoService {

	@Autowired
	private ImagemProdutoRepository imagemProdutoRepository;

	@Override
	protected JpaRepository<ImagemProduto, Integer> getRepository() {
		return imagemProdutoRepository;
	}
	
	
	
}
