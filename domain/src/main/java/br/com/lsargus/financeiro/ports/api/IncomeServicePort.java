package br.com.lsargus.financeiro.ports.api;

import br.com.lsargus.financeiro.data.IncomeBO;
import br.com.lsargus.financeiro.exceptions.RuleException;

import java.util.List;

public interface IncomeServicePort {
	
	List<IncomeBO> getAll();

	IncomeBO getIncome(Long id);

	IncomeBO addIncome(IncomeBO incomeBO) throws RuleException;

	IncomeBO updateIncome(Long id, IncomeBO incomeBO) throws RuleException;

	void deleteIncome(Long id);
}
