package br.com.lsargus.financeiro.bean_config;

import br.com.lsargus.financeiro.adapters.ReceitaJpaAdapter;
import br.com.lsargus.financeiro.ports.api.ReceitaServicePort;
import br.com.lsargus.financeiro.ports.spi.ReceitaPersistencePort;
import br.com.lsargus.financeiro.repository.ReceitaRepository;
import br.com.lsargus.financeiro.services.ReceitaServiceImp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReceitaConfig {

    private final ReceitaRepository receitaRepository;

    public ReceitaConfig(ReceitaRepository receitaRepository) {
        this.receitaRepository = receitaRepository;
    }

    @Bean
    public ReceitaPersistencePort receitaPersistence() {
        return new ReceitaJpaAdapter(receitaRepository);
    }

    @Bean
    public ReceitaServicePort receitaService() {
        return new ReceitaServiceImp(receitaPersistence());
    }
}
