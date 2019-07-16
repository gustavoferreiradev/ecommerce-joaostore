package br.utfpr.edu.ecommercejoaostore.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor

public class VendaProdutoPK implements Serializable {

	@ManyToOne
	@JoinColumn(name = "produto_id", referencedColumnName = "id")
	private Produto produto;

	@ManyToOne
	@JoinColumn(name = "venda_id", referencedColumnName = "id")
	private Venda venda;

}
