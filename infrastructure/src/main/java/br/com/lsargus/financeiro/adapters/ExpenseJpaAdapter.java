package br.com.lsargus.financeiro.adapters;

import br.com.lsargus.financeiro.data.ExpenseDto;
import br.com.lsargus.financeiro.data.IncomeDto;
import br.com.lsargus.financeiro.entity.Expense;
import br.com.lsargus.financeiro.entity.Income;
import br.com.lsargus.financeiro.ports.spi.ExpensePersistencePort;
import br.com.lsargus.financeiro.repository.ExpenseRepository;
import br.com.lsargus.financeiro.repository.IncomeRepository;
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
    public List<ExpenseDto> getExpense() {
        List<Expense> expenseList = expenseRepository.findAll();

        return expenseList.stream().map(r -> modelMapper.map(r, ExpenseDto.class)).toList();
    }

    @Override
    public ExpenseDto getExpense(Long id) {
        return  modelMapper.map(expenseRepository.findById(id), ExpenseDto.class);
    }

    @Override
    public ExpenseDto saveExpense(ExpenseDto expenseDto) {
        Expense expense = modelMapper.map(expenseDto, Expense.class);
        expense = expenseRepository.save(expense);

        return modelMapper.map(expense, ExpenseDto.class);
    }

    @Override
    public List<ExpenseDto> getExpenseByYearAndMonthAndDescription(Integer year, Integer month, String description) {
        List<ExpenseDto> expenseDtoList = new ArrayList<>();

        List<Expense> expenseList = expenseRepository.findExpenseByYearAndMonthAndDescription(year, month, description);

        if (expenseList != null && !expenseList.isEmpty())
            expenseDtoList = expenseList.stream().map(r -> modelMapper.map(r, ExpenseDto.class)).toList();

        return expenseDtoList;
    }

    @Override
    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }
}
