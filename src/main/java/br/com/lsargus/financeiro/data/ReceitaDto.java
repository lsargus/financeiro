package br.com.lsargus.financeiro.data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceitaDto {
	
	private Long id;
	private String descricao;
	private BigDecimal valor;
	private LocalDateTime data;
}
