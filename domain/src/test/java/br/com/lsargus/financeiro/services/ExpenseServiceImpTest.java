package br.com.lsargus.financeiro.services;


import br.com.lsargus.financeiro.data.ExpenseCategoryBO;
import br.com.lsargus.financeiro.data.ExpenseBO;
import br.com.lsargus.financeiro.ports.spi.ExpenseCategoryPersistencePort;
import br.com.lsargus.financeiro.ports.spi.ExpensePersistencePort;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ExpenseServiceImpTest {


    private static ExpenseServiceImp expenseService;

    private static ExpensePersistencePort expensePersistence;

    private static ExpenseCategoryPersistencePort expenseCategoryPersistence;

    @BeforeAll
    static void setup(){
        expensePersistence = mock(ExpensePersistencePort.class);
        expenseCategoryPersistence = mock(ExpenseCategoryPersistencePort.class);

        expenseService = new ExpenseServiceImp(expensePersistence, expenseCategoryPersistence);
    }

    @Test
    void verificaDuplicidadeRegistrosReceitaPositivo() {

        when(expensePersistence.getExpenseByYearAndMonthAndDescription(2022,6,"Descricao 3")).thenReturn(getListExpense());
        ExpenseBO expenseBO = new ExpenseBO(3L,"Descricao 3", new ExpenseCategoryBO(2L,"Saúde"), new BigDecimal("97.12"), 2022, 6, LocalDateTime.now());

        assertTrue(expenseService.checkExpenseDuplication(expenseBO));
    }

    @Test
    void verificaDuplicidadeRegistrosReceitaFalhaDevidoAno() {

        when(expensePersistence.getExpenseByYearAndMonthAndDescription(2022,6,"Descricao 3")).thenReturn(getListExpense());
        ExpenseBO expenseBO = new ExpenseBO(3L,"Descricao 3", new ExpenseCategoryBO(2L,"Saúde"), new BigDecimal("97.12"), 2021, 6, LocalDateTime.now());

        assertFalse(expenseService.checkExpenseDuplication(expenseBO));
    }

    @Test
    void verificaDuplicidadeRegistrosReceitaFalhaDevidoMes() {

        when(expensePersistence.getExpenseByYearAndMonthAndDescription(2022,6,"Descricao 3")).thenReturn(getListExpense());
        ExpenseBO expenseBO = new ExpenseBO(3L,"Descricao 3", new ExpenseCategoryBO(2L,"Saúde"), new BigDecimal("97.12"), 2022, 7, LocalDateTime.now());

        assertFalse(expenseService.checkExpenseDuplication(expenseBO));
    }

    @Test
    void verificaDuplicidadeRegistrosReceitaFalhaDevidoDescricao() {

        when(expensePersistence.getExpenseByYearAndMonthAndDescription(2022,6,"Descricao 3")).thenReturn(getListExpense());
        ExpenseBO expenseBO = new ExpenseBO(3L,"Descricao 3 erro", new ExpenseCategoryBO(2L,"Saúde"), new BigDecimal("97.12"), 2022, 6, LocalDateTime.now());

        assertFalse(expenseService.checkExpenseDuplication(expenseBO));
    }


    public List<ExpenseBO> getListExpense() {
        List<ExpenseBO> list = new ArrayList<>();

        list.add(new ExpenseBO(1L,"Descricao 1", new ExpenseCategoryBO(8L,"Outros"), new BigDecimal("15.12"), 2022, 4, LocalDateTime.now()));
        list.add(new ExpenseBO(2L,"Descricao 2", new ExpenseCategoryBO(1L,"Alimentação"), new BigDecimal("105.12"), 2022, 5, LocalDateTime.now()));
        list.add(new ExpenseBO(3L,"Descricao 3", new ExpenseCategoryBO(2L,"Saúde"), new BigDecimal("97.12"), 2022, 6, LocalDateTime.now()));
        list.add(new ExpenseBO(4L,"Descricao 4", new ExpenseCategoryBO(3L,"Moradia"), new BigDecimal("141.67"), 2022, 7, LocalDateTime.now()));

        return list;
    }
}