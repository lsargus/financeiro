package br.com.lsargus.financeiro.ports.api;

import br.com.lsargus.financeiro.data.ReceitaDto;
import br.com.lsargus.financeiro.exceptions.RuleException;

import java.util.List;

public interface ReceitaServicePort {
	
	List<ReceitaDto> getAll();

	ReceitaDto getReceita(Long id);

	ReceitaDto addReceita(ReceitaDto receita) throws RuleException;

	ReceitaDto updateReceita(Long id, ReceitaDto receitaDto) throws RuleException;

	void deleteReceita(Long id);
}
