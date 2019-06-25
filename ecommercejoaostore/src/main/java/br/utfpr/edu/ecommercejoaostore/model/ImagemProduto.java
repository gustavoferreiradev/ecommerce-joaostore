package br.utfpr.edu.ecommercejoaostore.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

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
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name="produto_id", referencedColumnName = "id")
	private Produto produto;
	
	@NotEmpty(message = "Insira o caminho!")
	@Column(name = "caminho", length = 100, nullable = false)
	private String caminho;	
	
	@Column(name = "title", length = 100, nullable = true)
	private String title;
	
	@Column(name = "alt", length = 100, nullable = true)
	private String alt;	

}
