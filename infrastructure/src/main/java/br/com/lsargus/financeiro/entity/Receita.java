package br.com.lsargus.financeiro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity(name = "FI001")
@NoArgsConstructor
@AllArgsConstructor
public class Receita {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FI001_id")
	private Long id;
	@Column(name = "FI001_ds_receita")
	private String descricao;
	@Column(name = "FI001_vl_receita")
	private BigDecimal valor;
	@Column(name = "FI001_nr_ano")
	private Integer ano;
	@Column(name = "FI001_nr_mes")
	private Integer mes;
	@Column(name = "FI001_dt_receita")
	private LocalDateTime data;
}
