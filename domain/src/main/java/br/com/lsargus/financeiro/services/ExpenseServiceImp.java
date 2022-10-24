package br.com.lsargus.financeiro.services;

import br.com.lsargus.financeiro.data.ExpenseBO;
import br.com.lsargus.financeiro.data.ExpenseCategoryBO;
import br.com.lsargus.financeiro.exceptions.RuleException;
import br.com.lsargus.financeiro.ports.api.ExpenseServicePort;
import br.com.lsargus.financeiro.ports.spi.ExpenseCategoryPersistencePort;
import br.com.lsargus.financeiro.ports.spi.ExpensePersistencePort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ExpenseServiceImp implements ExpenseServicePort {

    private final ExpensePersistencePort expensePersistence;

    private final ExpenseCategoryPersistencePort expenseCategoryPersistence;

    public ExpenseServiceImp(ExpensePersistencePort expensePersistence, ExpenseCategoryPersistencePort expenseCategoryPersistence) {
        this.expensePersistence = expensePersistence;
        this.expenseCategoryPersistence = expenseCategoryPersistence;
    }

    @Override
    public List<ExpenseBO> getAll() {
        return expensePersistence.getExpense();
    }

    @Override
    public ExpenseBO getExpense(Long id) {
        return expensePersistence.getExpense(id);
    }

    @Override
    public ExpenseBO addExpense(ExpenseBO expenseBO) throws RuleException, NoSuchElementException {

        ExpenseBO expense = checkExpenseCategory(expenseBO);

		if (checkExpenseDuplication(expense))
            throw new RuleException("Receita duplicada: já existe uma receitas com os dados cadastrada no mês.");

        return expensePersistence.saveExpense(expense);
    }

    @Override
    public ExpenseBO updateExpense(Long id, ExpenseBO expenseBO) throws RuleException, NoSuchElementException {

        ExpenseBO expense = checkExpenseCategory(expenseBO);

        if (checkExpenseDuplication(expense))
            throw new RuleException("Receita duplicada: já existe uma receitas com os dados cadastrada no mês.");

        expense.setId(id);

        return expensePersistence.saveExpense(expense);
    }

    @Override
    public void deleteExpense(Long id) {
        expensePersistence.deleteExpense(id);
    }

	/**
	 * Verifica se existe uma despesa cadastrata no mesmo ano/mes com a mesma descrição
	 * @param expenseBO despesa a ser verificada
	 * @return true se existir uma receita cadastrata, false caso contrário
	 */
    public boolean checkExpenseDuplication(ExpenseBO expenseBO) {
        return !expensePersistence.getExpenseByYearAndMonthAndDescription(expenseBO.getYear(), expenseBO.getMonth(), expenseBO.getDescription()).isEmpty();
    }

    /**
     * Verifica se a ctegoria da despesa foi informada, se não adiciona a categoria 8 - Outros
     * @param expenseBO dados da despesa
     * @return expenseDto atualizado com a categoria da despesa se esta não foi fornecida
     */
    public ExpenseBO checkExpenseCategory(ExpenseBO expenseBO) throws NoSuchElementException {
        ExpenseCategoryBO category = Optional.ofNullable(expenseBO.getCategory()).orElse(expenseCategoryPersistence.findOthers());
        expenseBO.setCategory(category);
        return expenseBO;
    }

}
