package br.com.lsargus.financeiro.bean_inject;

import br.com.lsargus.financeiro.adapters.IncomeJpaAdapter;
import br.com.lsargus.financeiro.ports.api.IncomeServicePort;
import br.com.lsargus.financeiro.ports.spi.IncomePersistencePort;
import br.com.lsargus.financeiro.repository.IncomeRepository;
import br.com.lsargus.financeiro.services.IncomeServiceImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IncomeConfig {

    private final IncomeRepository incomeRepository;

    public IncomeConfig(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

    @Bean
    public IncomePersistencePort incomePersistence() {
        return new IncomeJpaAdapter(incomeRepository);
    }

    @Bean
    public IncomeServicePort incomeService() {
        return new IncomeServiceImp(incomePersistence());
    }
}
