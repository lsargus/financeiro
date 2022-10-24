package br.com.lsargus.financeiro.ports.spi;

import br.com.lsargus.financeiro.data.ExpenseBO;

import java.util.List;

public interface ExpensePersistencePort {

    List<ExpenseBO> getExpense();

    ExpenseBO getExpense(Long id);

    ExpenseBO saveExpense(ExpenseBO expenseBO);

    List<ExpenseBO> getExpenseByYearAndMonthAndDescription(Integer year, Integer month, String description);

    void deleteExpense(Long id);
}
