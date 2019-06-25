package br.utfpr.edu.ecommercejoaostore.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
@Embeddable
@Data
public class ImagemProdutoPK implements Serializable{
	
	@ManyToOne
	@JoinColumn (name = "produto_id", referencedColumnName = "id")
	private Produto produto;
	    
    @ManyToOne
	@JoinColumn (name = "imagem_id", referencedColumnName = "id")
    private Imagem imagem;

}
