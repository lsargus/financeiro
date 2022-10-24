package br.com.lsargus.financeiro.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IncomeBO {
	
	private Long id;
	private String description;
	private BigDecimal value;
	private Integer year;
	private Integer month;
	private LocalDateTime date;
}
