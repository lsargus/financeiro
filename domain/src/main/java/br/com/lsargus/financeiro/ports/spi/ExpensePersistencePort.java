package br.com.lsargus.financeiro.ports.spi;

import br.com.lsargus.financeiro.data.ExpenseDto;

import java.util.List;

public interface ExpensePersistencePort {

    List<ExpenseDto> getExpense();

    ExpenseDto getExpense(Long id);

    ExpenseDto saveExpense(ExpenseDto expenseDto);

    List<ExpenseDto> getExpenseByYearAndMonthAndDescription(Integer year, Integer month, String description);

    void deleteExpense(Long id);
}
