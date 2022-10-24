package br.com.lsargus.financeiro.adapters;

import br.com.lsargus.financeiro.data.ExpenseBO;
import br.com.lsargus.financeiro.entity.Expense;
import br.com.lsargus.financeiro.ports.spi.ExpensePersistencePort;
import br.com.lsargus.financeiro.repository.ExpenseRepository;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class ExpenseJpaAdapter implements ExpensePersistencePort {

    private final ExpenseRepository expenseRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public ExpenseJpaAdapter(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public List<ExpenseBO> getExpense() {
        List<Expense> expenseList = expenseRepository.findAll();

        return expenseList.stream().map(r -> modelMapper.map(r, ExpenseBO.class)).toList();
    }

    @Override
    public ExpenseBO getExpense(Long id) {
        return  modelMapper.map(expenseRepository.findById(id), ExpenseBO.class);
    }

    @Override
    public ExpenseBO saveExpense(ExpenseBO expenseBO) {
        Expense expense = modelMapper.map(expenseBO, Expense.class);
        expense = expenseRepository.save(expense);

        return modelMapper.map(expense, ExpenseBO.class);
    }

    @Override
    public List<ExpenseBO> getExpenseByYearAndMonthAndDescription(Integer year, Integer month, String description) {
        List<ExpenseBO> expenseBOList = new ArrayList<>();

        List<Expense> expenseList = expenseRepository.findExpenseByYearAndMonthAndDescription(year, month, description);

        if (expenseList != null && !expenseList.isEmpty())
            expenseBOList = expenseList.stream().map(r -> modelMapper.map(r, ExpenseBO.class)).toList();

        return expenseBOList;
    }

    @Override
    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }
}
