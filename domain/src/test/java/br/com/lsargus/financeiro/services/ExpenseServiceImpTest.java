package br.com.lsargus.financeiro.services;


import br.com.lsargus.financeiro.data.ExpenseDto;
import br.com.lsargus.financeiro.data.IncomeDto;
import br.com.lsargus.financeiro.ports.spi.ExpensePersistencePort;
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

class ExpenseServiceImpTest {


    private static ExpenseServiceImp expenseService;

    private static ExpensePersistencePort expensePersistence;

    @BeforeAll
    static void setup(){
        expensePersistence = mock(ExpensePersistencePort.class);

        expenseService = new ExpenseServiceImp(expensePersistence);
    }

    @Test
    void verificaDuplicidadeRegistrosReceitaPositivo() {

        when(expensePersistence.getExpenseByYearAndMonthAndDescription(2022,6,"Descricao 3")).thenReturn(getListExpense());
        ExpenseDto expenseDto = new ExpenseDto(3L,"Descricao 3", new BigDecimal("97.12"), 2022, 6, LocalDateTime.now());

        assertTrue(expenseService.checkExpenseDuplication(expenseDto));
    }

    @Test
    void verificaDuplicidadeRegistrosReceitaFalhaDevidoAno() {

        when(expensePersistence.getExpenseByYearAndMonthAndDescription(2022,6,"Descricao 3")).thenReturn(getListExpense());
        ExpenseDto expenseDto = new ExpenseDto(3L,"Descricao 3", new BigDecimal("97.12"), 2021, 6, LocalDateTime.now());

        assertFalse(expenseService.checkExpenseDuplication(expenseDto));
    }

    @Test
    void verificaDuplicidadeRegistrosReceitaFalhaDevidoMes() {

        when(expensePersistence.getExpenseByYearAndMonthAndDescription(2022,6,"Descricao 3")).thenReturn(getListExpense());
        ExpenseDto expenseDto = new ExpenseDto(3L,"Descricao 3", new BigDecimal("97.12"), 2022, 7, LocalDateTime.now());

        assertFalse(expenseService.checkExpenseDuplication(expenseDto));
    }

    @Test
    void verificaDuplicidadeRegistrosReceitaFalhaDevidoDescricao() {

        when(expensePersistence.getExpenseByYearAndMonthAndDescription(2022,6,"Descricao 3")).thenReturn(getListExpense());
        ExpenseDto expenseDto = new ExpenseDto(3L,"Descricao 3 erro", new BigDecimal("97.12"), 2022, 6, LocalDateTime.now());

        assertFalse(expenseService.checkExpenseDuplication(expenseDto));
    }


    public List<ExpenseDto> getListExpense() {
        List<ExpenseDto> list = new ArrayList<>();

        list.add(new ExpenseDto(1L,"Descricao 1", new BigDecimal("15.12"), 2022, 4, LocalDateTime.now()));
        list.add(new ExpenseDto(2L,"Descricao 2", new BigDecimal("105.12"), 2022, 5, LocalDateTime.now()));
        list.add(new ExpenseDto(3L,"Descricao 3", new BigDecimal("97.12"), 2022, 6, LocalDateTime.now()));
        list.add(new ExpenseDto(4L,"Descricao 4", new BigDecimal("141.67"), 2022, 7, LocalDateTime.now()));

        return list;
    }
}