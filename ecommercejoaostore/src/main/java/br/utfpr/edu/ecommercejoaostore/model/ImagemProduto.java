package br.utfpr.edu.ecommercejoaostore.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "imagem_produto")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class ImagemProduto {
	
	@EmbeddedId
	private ImagemProdutoPK id;
	
	@Column(name = "resumo", length = 100, nullable = true)
	private String title;
	
	@Column(name = "resumo", length = 100, nullable = true)
	private String alt;	

}
