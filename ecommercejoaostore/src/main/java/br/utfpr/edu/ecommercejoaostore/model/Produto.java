package br.utfpr.edu.ecommercejoaostore.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "produto")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Produto implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "Preencha o campo nome!")
	@Column(name = "nome", length = 100, nullable = false)
	private String nome;
	
	@NotEmpty(message = "Preencha o resumo!")
	@Column(name = "resumo", length = 100, nullable = false)
	private String resumo;
	
	@NotEmpty(message = "Preencha a descrição!")
	@Column(name = "descricao", length = 100, nullable = false)
	private String descricao;
	
	@NotEmpty(message = "Preencha o tamanho!")
	@Column(name = "nome", length = 100, nullable = false)
	private String tamanho;
	
	@NotNull(message = "Selecione uma categoria!")
	@JoinColumn(name = "categoria_id", referencedColumnName = "id")
	private Categoria categoria;
	
	@NotNull(message = "Selecione uma marca!")
	@JoinColumn(name = "marca_id", referencedColumnName="id")
	private Marca marca;
	

}
