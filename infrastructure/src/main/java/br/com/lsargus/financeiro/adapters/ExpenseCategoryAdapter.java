package br.com.lsargus.financeiro.adapters;

import br.com.lsargus.financeiro.data.ExpenseCategoryBO;
import br.com.lsargus.financeiro.entity.ExpenseCategory;
import br.com.lsargus.financeiro.ports.spi.ExpenseCategoryPersistencePort;
import br.com.lsargus.financeiro.repository.ExpenseCategoryRepository;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.NoSuchElementException;

public class ExpenseCategoryAdapter implements ExpenseCategoryPersistencePort {

    private final ExpenseCategoryRepository expenseCategoryRepository;

    private final ModelMapper modelMapper = new ModelMapper();

    private static final Long ID_OTHERS = 8L;

    public ExpenseCategoryAdapter(ExpenseCategoryRepository expenseCategoryRepository) {
        this.expenseCategoryRepository = expenseCategoryRepository;
    }

    @Override
    public List<ExpenseCategoryBO> getExpenseCategory() {
        List<ExpenseCategory> expenseCategoryList = expenseCategoryRepository.findAll();
        return expenseCategoryList.stream().map(e -> modelMapper.map(e, ExpenseCategoryBO.class)).toList();
    }

    @Override
    public ExpenseCategoryBO saveExpenseCategory(ExpenseCategoryBO expenseCategoryBO) {
        ExpenseCategory expenseCategory = modelMapper.map(expenseCategoryBO, ExpenseCategory.class);
        expenseCategory = expenseCategoryRepository.save(expenseCategory);
        return modelMapper.map(expenseCategory, ExpenseCategoryBO.class);
    }

    @Override
    public ExpenseCategoryBO findOthers() throws NoSuchElementException {
        return modelMapper.map(expenseCategoryRepository.findById(ID_OTHERS).orElseThrow(), ExpenseCategoryBO.class);
    }
}
