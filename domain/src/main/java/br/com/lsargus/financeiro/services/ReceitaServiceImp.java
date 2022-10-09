package br.com.lsargus.financeiro.services;

import br.com.lsargus.financeiro.data.ReceitaDto;
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

}
