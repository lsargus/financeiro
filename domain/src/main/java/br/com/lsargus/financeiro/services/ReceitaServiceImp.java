package br.com.lsargus.financeiro.services;

import br.com.lsargus.financeiro.data.ReceitaDto;
import br.com.lsargus.financeiro.exceptions.RuleException;
import br.com.lsargus.financeiro.ports.api.ReceitaServicePort;
import br.com.lsargus.financeiro.ports.spi.ReceitaPersistencePort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceitaServiceImp implements ReceitaServicePort {

    private final ReceitaPersistencePort receitaPersistence;

    public ReceitaServiceImp(ReceitaPersistencePort receitaPersistence) {
        this.receitaPersistence = receitaPersistence;
    }

    @Override
    public List<ReceitaDto> getAll() {
        return receitaPersistence.getReceitas();
    }

    @Override
    public ReceitaDto getReceita(Long id) {
        return receitaPersistence.getReceita(id);
    }

    @Override
    public ReceitaDto addReceita(ReceitaDto receitaDto) throws RuleException {

		if (verificarDuplicidadeReceita(receitaDto))
            throw new RuleException("Receita duplicada: já existe uma receitas com os dados cadastrada no mês.");

        return receitaPersistence.saveReceita(receitaDto);
    }

    @Override
    public ReceitaDto updateReceita(Long id, ReceitaDto receitaDto) throws RuleException {

        if (verificarDuplicidadeReceita(receitaDto))
            throw new RuleException("Receita duplicada: já existe uma receitas com os dados cadastrada no mês.");

        ReceitaDto newReceita = receitaDto;
        newReceita.setId(id);

        return receitaPersistence.saveReceita(newReceita);
    }

    @Override
    public void deleteReceita(Long id) {
        receitaPersistence.deleteReceita(id);
    }

	/**
	 * Verifica se existe uma receita cadastrata no mesmo ano/mes com a mesma descrição
	 * @param receita
	 * @return true se existir uma receita cadastrata, false caso contrário
	 */
    public boolean verificarDuplicidadeReceita(ReceitaDto receita) {
        return !receitaPersistence.getReceitaByAnoAndMesAndDescricao(receita.getAno(), receita.getMes(), receita.getDescricao()).isEmpty();
    }

}
