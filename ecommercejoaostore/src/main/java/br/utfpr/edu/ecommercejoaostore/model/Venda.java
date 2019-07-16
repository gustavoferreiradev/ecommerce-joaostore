package br.utfpr.edu.ecommercejoaostore.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "venda")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString

public class Venda implements Serializable {

	private static final long serialVersionUID = 9041642480930592442L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull (message = "Preencha o número da nota fiscal!")
	@Column(name = "nf", nullable = false)
	private Integer nf;
	
	@NotNull (message = "Preencha a data de compra!")
	@Column(name = "data", nullable = false)
	private LocalDate data;	
		
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "id.venda", fetch = FetchType.EAGER)
    private List<VendaProduto> vendaProdutos;
    
    @Transient
    private Double valorTotal;
	

}
