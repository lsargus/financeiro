package br.com.lsargus.financeiro.ports.spi;

import br.com.lsargus.financeiro.data.ReceitaDto;

import java.util.List;

public interface ReceitaPersistencePort {

    List<ReceitaDto> getReceitas();
}
