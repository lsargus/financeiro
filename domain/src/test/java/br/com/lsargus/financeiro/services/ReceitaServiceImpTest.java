package br.com.lsargus.financeiro.services;


import br.com.lsargus.financeiro.data.ReceitaDto;
import br.com.lsargus.financeiro.ports.spi.ReceitaPersistencePort;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ReceitaServiceImpTest {


    private static ReceitaServiceImp receitaService;

    private static ReceitaPersistencePort receitaPersistence;

    @BeforeAll
    static void setup(){
        receitaPersistence = mock(ReceitaPersistencePort.class);

        receitaService = new ReceitaServiceImp(receitaPersistence);
    }

    @Test
    void verificaDuplicidadeRegistrosReceitaPositivo() {

        when(receitaPersistence.getReceitaByAnoAndMesAndDescricao(2022,6,"Descricao 3")).thenReturn(getListReceita());
        ReceitaDto receita = new ReceitaDto(3L,"Descricao 3", new BigDecimal("97.12"), 2022, 6, LocalDateTime.now());

        assertTrue(receitaService.verificarDuplicidadeReceita(receita));
    }

    @Test
    void verificaDuplicidadeRegistrosReceitaFalhaDevidoAno() {

        when(receitaPersistence.getReceitaByAnoAndMesAndDescricao(2022,6,"Descricao 3")).thenReturn(getListReceita());
        ReceitaDto receita = new ReceitaDto(3L,"Descricao 3", new BigDecimal("97.12"), 2021, 6, LocalDateTime.now());

        assertFalse(receitaService.verificarDuplicidadeReceita(receita));
    }

    @Test
    void verificaDuplicidadeRegistrosReceitaFalhaDevidoMes() {

        when(receitaPersistence.getReceitaByAnoAndMesAndDescricao(2022,6,"Descricao 3")).thenReturn(getListReceita());
        ReceitaDto receita = new ReceitaDto(3L,"Descricao 3", new BigDecimal("97.12"), 2022, 7, LocalDateTime.now());

        assertFalse(receitaService.verificarDuplicidadeReceita(receita));
    }

    @Test
    void verificaDuplicidadeRegistrosReceitaFalhaDevidoDescricao() {

        when(receitaPersistence.getReceitaByAnoAndMesAndDescricao(2022,6,"Descricao 3")).thenReturn(getListReceita());
        ReceitaDto receita = new ReceitaDto(3L,"Descricao 3 erro", new BigDecimal("97.12"), 2022, 6, LocalDateTime.now());

        assertFalse(receitaService.verificarDuplicidadeReceita(receita));
    }


    public List<ReceitaDto> getListReceita() {
        List<ReceitaDto> list = new ArrayList<>();

        list.add(new ReceitaDto(1L,"Descricao 1", new BigDecimal("15.12"), 2022, 4, LocalDateTime.now()));
        list.add(new ReceitaDto(2L,"Descricao 2", new BigDecimal("105.12"), 2022, 5, LocalDateTime.now()));
        list.add(new ReceitaDto(3L,"Descricao 3", new BigDecimal("97.12"), 2022, 6, LocalDateTime.now()));
        list.add(new ReceitaDto(4L,"Descricao 4", new BigDecimal("141.67"), 2022, 7, LocalDateTime.now()));

        return list;
    }
}