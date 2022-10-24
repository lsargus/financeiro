package br.com.lsargus.financeiro.services;

import br.com.lsargus.financeiro.data.ExpenseCategoryBO;
import br.com.lsargus.financeiro.ports.api.ExpenseCategoryServicePort;
import br.com.lsargus.financeiro.ports.spi.ExpenseCategoryPersistencePort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseCategoryServiceImp implements ExpenseCategoryServicePort {

    private final ExpenseCategoryPersistencePort expenseCategoryPersistence;

    public ExpenseCategoryServiceImp(ExpenseCategoryPersistencePort expenseCategoryPersistence) {
        this.expenseCategoryPersistence = expenseCategoryPersistence;
    }

    @Override
    public List<ExpenseCategoryBO> getAll() {
        return expenseCategoryPersistence.getExpenseCategory();
    }

    @Override
    public ExpenseCategoryBO addCategory(ExpenseCategoryBO categoryDto) {
        return expenseCategoryPersistence.saveExpenseCategory(categoryDto);
    }
}
