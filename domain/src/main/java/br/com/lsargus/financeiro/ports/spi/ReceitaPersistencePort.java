package br.com.lsargus.financeiro.ports.spi;

import br.com.lsargus.financeiro.data.ReceitaDto;

import java.util.List;

public interface ReceitaPersistencePort {

    List<ReceitaDto> getReceitas();

    ReceitaDto getReceita(Long id);

    ReceitaDto saveReceita(ReceitaDto receita);

    List<ReceitaDto> getReceitaByAnoAndMesAndDescricao(Integer ano, Integer mes, String descricao);

    void deleteReceita(Long id);
}
