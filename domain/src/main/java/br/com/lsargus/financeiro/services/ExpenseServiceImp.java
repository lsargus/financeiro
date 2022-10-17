package br.com.lsargus.financeiro.services;

import br.com.lsargus.financeiro.data.ExpenseCategoryDto;
import br.com.lsargus.financeiro.data.ExpenseDto;
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
    public List<ExpenseDto> getAll() {
        return expensePersistence.getExpense();
    }

    @Override
    public ExpenseDto getExpense(Long id) {
        return expensePersistence.getExpense(id);
    }

    @Override
    public ExpenseDto addExpense(ExpenseDto expenseDto) throws RuleException, NoSuchElementException {

        ExpenseDto expense = checkExpenseCategory(expenseDto);

		if (checkExpenseDuplication(expense))
            throw new RuleException("Receita duplicada: já existe uma receitas com os dados cadastrada no mês.");

        return expensePersistence.saveExpense(expense);
    }

    @Override
    public ExpenseDto updateExpense(Long id, ExpenseDto expenseDto) throws RuleException, NoSuchElementException {

        ExpenseDto expense = checkExpenseCategory(expenseDto);

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
	 * @param expenseDto despesa a ser verificada
	 * @return true se existir uma receita cadastrata, false caso contrário
	 */
    public boolean checkExpenseDuplication(ExpenseDto expenseDto) {
        return !expensePersistence.getExpenseByYearAndMonthAndDescription(expenseDto.getYear(), expenseDto.getMonth(), expenseDto.getDescription()).isEmpty();
    }

    /**
     * Verifica se a ctegoria da despesa foi informada, se não adiciona a categoria 8 - Outros
     * @param expenseDto dados da despesa
     * @return expenseDto atualizado com a categoria da despesa se esta não foi fornecida
     */
    public ExpenseDto checkExpenseCategory(ExpenseDto expenseDto) throws NoSuchElementException {
        ExpenseCategoryDto category = Optional.ofNullable(expenseDto.getCategory()).orElse(expenseCategoryPersistence.findOthers());
        expenseDto.setCategory(category);
        return expenseDto;
    }

}
