package br.utfpr.edu.ecommercejoaostore.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "compra_produto")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class CompraProduto implements Serializable {

private static final long serialVersionUID = -401323038784266407L;

@EmbeddedId
private CompraProdutoPK id;

@NotNull (message = "preencha a quantidade!")
@Column(name = "quantidade", nullable = false)
private Integer quantidade;

@NotNull (message = "Favor informar o valor!")
@Column(name = "valor", nullable = false)
private Double valor;	
	

}
