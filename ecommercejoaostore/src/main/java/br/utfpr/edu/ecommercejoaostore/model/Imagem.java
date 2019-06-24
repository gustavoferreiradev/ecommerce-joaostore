package br.utfpr.edu.ecommercejoaostore.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

public class Imagem implements Serializable{

	private static final long serialVersionUID = -917695490851510743L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "Insira o caminho!")
	@Column(name = "caminho", length = 100, nullable = false)
	private String caminho;	
	
	

}
