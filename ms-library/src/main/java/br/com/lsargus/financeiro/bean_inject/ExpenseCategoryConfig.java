package br.com.lsargus.financeiro.bean_inject;

import br.com.lsargus.financeiro.adapters.ExpenseCategoryAdapter;
import br.com.lsargus.financeiro.ports.api.ExpenseCategoryServicePort;
import br.com.lsargus.financeiro.ports.spi.ExpenseCategoryPersistencePort;
import br.com.lsargus.financeiro.repository.ExpenseCategoryRepository;
import br.com.lsargus.financeiro.services.ExpenseCategoryServiceImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ExpenseCategoryConfig {

    private final ExpenseCategoryRepository expenseCategoryRepository;

    public ExpenseCategoryConfig(ExpenseCategoryRepository expenseCategoryRepository) {
        this.expenseCategoryRepository = expenseCategoryRepository;
    }

    @Bean
    @Primary
    public ExpenseCategoryPersistencePort expenseCategoryPersistence() {
        return new ExpenseCategoryAdapter(expenseCategoryRepository);
    }

    @Bean
    public ExpenseCategoryServicePort expenseCategoryService() {
        return new ExpenseCategoryServiceImp(expenseCategoryPersistence());
    }
}
