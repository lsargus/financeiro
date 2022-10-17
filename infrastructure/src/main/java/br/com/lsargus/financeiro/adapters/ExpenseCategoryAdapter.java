package br.com.lsargus.financeiro.adapters;

import br.com.lsargus.financeiro.data.ExpenseCategoryDto;
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
    public List<ExpenseCategoryDto> getExpenseCategory() {
        List<ExpenseCategory> expenseCategoryList = expenseCategoryRepository.findAll();
        return expenseCategoryList.stream().map(e -> modelMapper.map(e, ExpenseCategoryDto.class)).toList();
    }

    @Override
    public ExpenseCategoryDto saveExpenseCategory(ExpenseCategoryDto expenseCategoryDto) {
        ExpenseCategory expenseCategory = modelMapper.map(expenseCategoryDto, ExpenseCategory.class);
        expenseCategory = expenseCategoryRepository.save(expenseCategory);
        return modelMapper.map(expenseCategory, ExpenseCategoryDto.class);
    }

    @Override
    public ExpenseCategoryDto findOthers() throws NoSuchElementException {
        return modelMapper.map(expenseCategoryRepository.findById(ID_OTHERS).orElseThrow(), ExpenseCategoryDto.class);
    }
}
