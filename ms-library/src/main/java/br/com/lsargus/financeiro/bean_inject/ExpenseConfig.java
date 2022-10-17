package br.com.lsargus.financeiro.bean_inject;

import br.com.lsargus.financeiro.adapters.ExpenseJpaAdapter;
import br.com.lsargus.financeiro.ports.api.ExpenseServicePort;
import br.com.lsargus.financeiro.ports.spi.ExpenseCategoryPersistencePort;
import br.com.lsargus.financeiro.ports.spi.ExpensePersistencePort;
import br.com.lsargus.financeiro.repository.ExpenseRepository;
import br.com.lsargus.financeiro.services.ExpenseServiceImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ExpenseConfig {

    private final ExpenseRepository expenseRepository;

    private final ExpenseCategoryPersistencePort expenseCategoryPersistence;

    public ExpenseConfig(ExpenseRepository expenseRepository, ExpenseCategoryPersistencePort expenseCategoryPersistence) {
        this.expenseRepository = expenseRepository;
        this.expenseCategoryPersistence = expenseCategoryPersistence;
    }

    @Bean
    public ExpensePersistencePort expensePersistence() {
        return new ExpenseJpaAdapter(expenseRepository);
    }

    @Bean
    public ExpenseServicePort expenseService() {
        return new ExpenseServiceImp(expensePersistence(), expenseCategoryPersistence);
    }
}
