package br.com.lsargus.financeiro.ports.api;

import br.com.lsargus.financeiro.data.ExpenseBO;
import br.com.lsargus.financeiro.exceptions.RuleException;

import java.util.List;

public interface ExpenseServicePort {
	
	List<ExpenseBO> getAll();

	ExpenseBO getExpense(Long id);

	ExpenseBO addExpense(ExpenseBO expenseBO) throws RuleException;

	ExpenseBO updateExpense(Long id, ExpenseBO expenseBO) throws RuleException;

	void deleteExpense(Long id);
}
