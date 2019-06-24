package br.utfpr.edu.ecommercejoaostore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import br.utfpr.edu.ecommercejoaostore.model.Imagem;
import br.utfpr.edu.ecommercejoaostore.repository.ImagemRepository;
import br.utfpr.edu.ecommercejoaostore.service.ImagemService;

@Service
public class ImagemServiceImpl extends CrudServiceImpl <Imagem, Integer> implements ImagemService {

	@Autowired
	private ImagemRepository imagemRepository;
	
	@Override
	protected JpaRepository<Imagem, Integer> getRepository() {
		return imagemRepository;
	}

}
