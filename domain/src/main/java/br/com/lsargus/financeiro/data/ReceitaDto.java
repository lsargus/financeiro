package br.com.lsargus.financeiro.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceitaDto {
	
	private Long id;
	@NotEmpty
	@Size(min = 1, max = 255, message = "Descrição deve ser informada")
	private String descricao;
	@NotNull
	private BigDecimal valor;
	@NotNull
	private Integer ano;
	@NotNull
	private Integer mes;
	@NotNull
	private LocalDateTime data;
}
