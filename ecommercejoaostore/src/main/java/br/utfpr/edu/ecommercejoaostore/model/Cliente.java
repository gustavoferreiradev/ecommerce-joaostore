package br.utfpr.edu.ecommercejoaostore.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "cliente")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Cliente implements Serializable{
	
private static final long serialVersionUID = -8900218684789817676L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull(message = "Preencha o CPF!")
	@Column(name = "cpf", length = 14, nullable = false)
	private Long cpf;
	
	@NotEmpty(message = "Preencha o Endereço!")
	@Column(name = "rua", length = 100, nullable = false)
	private String rua;
	
	@NotEmpty(message = "Preencha o Bairro!")
	@Column(name = "bairro", length = 100, nullable = false)
	private String bairro;
	
	@NotNull(message = "Preencha o CEP!")
	@Column(name = "cep", length = 8, nullable = false)
	private Long cep;
	
	@Column(name = "complemento", length = 100, nullable = true)
	private String complemento;
	
	@Column(name = "referencia", length = 100, nullable = true)
	private String referencia;
	
	@NotNull(message = "Preencha a Cidade!")
	@ManyToOne
	@JoinColumn(name = "cidade_id", referencedColumnName = "id")
	private Cidade cidade;

}
