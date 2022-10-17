package br.com.lsargus.financeiro.services;

import br.com.lsargus.financeiro.data.ExpenseDto;
import br.com.lsargus.financeiro.data.IncomeDto;
import br.com.lsargus.financeiro.exceptions.RuleException;
import br.com.lsargus.financeiro.ports.api.ExpenseServicePort;
import br.com.lsargus.financeiro.ports.spi.ExpensePersistencePort;
import br.com.lsargus.financeiro.ports.spi.IncomePersistencePort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImp implements ExpenseServicePort {

    private final ExpensePersistencePort expensePersistence;

    public ExpenseServiceImp(ExpensePersistencePort expensePersistence) {
        this.expensePersistence = expensePersistence;
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
    public ExpenseDto addExpense(ExpenseDto expenseDto) throws RuleException {

		if (checkExpenseDuplication(expenseDto))
            throw new RuleException("Receita duplicada: já existe uma receitas com os dados cadastrada no mês.");

        return expensePersistence.saveExpense(expenseDto);
    }

    @Override
    public ExpenseDto updateExpense(Long id, ExpenseDto expenseDto) throws RuleException {

        if (checkExpenseDuplication(expenseDto))
            throw new RuleException("Receita duplicada: já existe uma receitas com os dados cadastrada no mês.");

        ExpenseDto newExpense = expenseDto;
        newExpense.setId(id);

        return expensePersistence.saveExpense(newExpense);
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

}
