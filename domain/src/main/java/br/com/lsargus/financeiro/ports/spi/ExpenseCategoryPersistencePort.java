package br.com.lsargus.financeiro.ports.spi;

import br.com.lsargus.financeiro.data.ExpenseCategoryDto;

import java.util.List;
import java.util.NoSuchElementException;

public interface ExpenseCategoryPersistencePort {

    List<ExpenseCategoryDto> getExpenseCategory();

    ExpenseCategoryDto saveExpenseCategory(ExpenseCategoryDto expenseCategoryDto);

    ExpenseCategoryDto findOthers() throws NoSuchElementException;
}
