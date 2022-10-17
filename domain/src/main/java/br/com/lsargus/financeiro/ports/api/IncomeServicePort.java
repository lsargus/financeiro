package br.com.lsargus.financeiro.ports.api;

import br.com.lsargus.financeiro.data.IncomeDto;
import br.com.lsargus.financeiro.exceptions.RuleException;

import java.util.List;

public interface IncomeServicePort {
	
	List<IncomeDto> getAll();

	IncomeDto getIncome(Long id);

	IncomeDto addIncome(IncomeDto incomeDto) throws RuleException;

	IncomeDto updateIncome(Long id, IncomeDto incomeDto) throws RuleException;

	void deleteIncome(Long id);
}
