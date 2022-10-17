package br.com.lsargus.financeiro.services;


import br.com.lsargus.financeiro.data.IncomeDto;
import br.com.lsargus.financeiro.ports.spi.IncomePersistencePort;
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

class IncomeServiceImpTest {


    private static IncomeServiceImp incomeService;

    private static IncomePersistencePort incomePersistence;

    @BeforeAll
    static void setup(){
        incomePersistence = mock(IncomePersistencePort.class);

        incomeService = new IncomeServiceImp(incomePersistence);
    }

    @Test
    void verificaDuplicidadeRegistrosReceitaPositivo() {

        when(incomePersistence.getIncomeByYearAndMonthAndDescription(2022,6,"Descricao 3")).thenReturn(getListIncome());
        IncomeDto incomeDto = new IncomeDto(3L,"Descricao 3", new BigDecimal("97.12"), 2022, 6, LocalDateTime.now());

        assertTrue(incomeService.checkIncomeDuplication(incomeDto));
    }

    @Test
    void verificaDuplicidadeRegistrosReceitaFalhaDevidoAno() {

        when(incomePersistence.getIncomeByYearAndMonthAndDescription(2022,6,"Descricao 3")).thenReturn(getListIncome());
        IncomeDto incomeDto = new IncomeDto(3L,"Descricao 3", new BigDecimal("97.12"), 2021, 6, LocalDateTime.now());

        assertFalse(incomeService.checkIncomeDuplication(incomeDto));
    }

    @Test
    void verificaDuplicidadeRegistrosReceitaFalhaDevidoMes() {

        when(incomePersistence.getIncomeByYearAndMonthAndDescription(2022,6,"Descricao 3")).thenReturn(getListIncome());
        IncomeDto incomeDto = new IncomeDto(3L,"Descricao 3", new BigDecimal("97.12"), 2022, 7, LocalDateTime.now());

        assertFalse(incomeService.checkIncomeDuplication(incomeDto));
    }

    @Test
    void verificaDuplicidadeRegistrosReceitaFalhaDevidoDescricao() {

        when(incomePersistence.getIncomeByYearAndMonthAndDescription(2022,6,"Descricao 3")).thenReturn(getListIncome());
        IncomeDto incomeDto = new IncomeDto(3L,"Descricao 3 erro", new BigDecimal("97.12"), 2022, 6, LocalDateTime.now());

        assertFalse(incomeService.checkIncomeDuplication(incomeDto));
    }


    public List<IncomeDto> getListIncome() {
        List<IncomeDto> list = new ArrayList<>();

        list.add(new IncomeDto(1L,"Descricao 1", new BigDecimal("15.12"), 2022, 4, LocalDateTime.now()));
        list.add(new IncomeDto(2L,"Descricao 2", new BigDecimal("105.12"), 2022, 5, LocalDateTime.now()));
        list.add(new IncomeDto(3L,"Descricao 3", new BigDecimal("97.12"), 2022, 6, LocalDateTime.now()));
        list.add(new IncomeDto(4L,"Descricao 4", new BigDecimal("141.67"), 2022, 7, LocalDateTime.now()));

        return list;
    }
}