package br.com.lsargus.financeiro.ports.api;

import br.com.lsargus.financeiro.data.ExpenseDto;
import br.com.lsargus.financeiro.exceptions.RuleException;

import java.util.List;

public interface ExpenseServicePort {
	
	List<ExpenseDto> getAll();

	ExpenseDto getExpense(Long id);

	ExpenseDto addExpense(ExpenseDto incomeDto) throws RuleException;

	ExpenseDto updateExpense(Long id, ExpenseDto expenseDto) throws RuleException;

	void deleteExpense(Long id);
}
