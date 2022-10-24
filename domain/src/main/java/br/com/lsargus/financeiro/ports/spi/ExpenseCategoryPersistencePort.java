package br.com.lsargus.financeiro.ports.spi;

import br.com.lsargus.financeiro.data.ExpenseCategoryBO;

import java.util.List;
import java.util.NoSuchElementException;

public interface ExpenseCategoryPersistencePort {

    List<ExpenseCategoryBO> getExpenseCategory();

    ExpenseCategoryBO saveExpenseCategory(ExpenseCategoryBO expenseCategoryBO);

    ExpenseCategoryBO findOthers() throws NoSuchElementException;
}
