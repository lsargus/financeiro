package br.com.lsargus.financeiro.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceitaDto {
	
	private Long id;
	private String descricao;
	private BigDecimal valor;
	private String data;
}
