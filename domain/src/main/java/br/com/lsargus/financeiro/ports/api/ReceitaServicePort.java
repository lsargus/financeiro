package br.com.lsargus.financeiro.ports.api;

import br.com.lsargus.financeiro.data.ReceitaDto;

import java.util.List;

public interface ReceitaServicePort {
	
	List<ReceitaDto> getAll();

}
