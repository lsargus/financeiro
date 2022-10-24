package br.com.lsargus.financeiro.adapters;

import br.com.lsargus.financeiro.repository.ExpenseCategoryRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ExpenseCategoryAdapterTest {

    private static ExpenseCategoryRepository expenseCategoryRepository;
    private static ExpenseCategoryAdapter expenseCategoryAdapter;

    @BeforeAll
    static void setup(){
        expenseCategoryRepository = mock(ExpenseCategoryRepository.class);

        expenseCategoryAdapter = new ExpenseCategoryAdapter(expenseCategoryRepository);
    }

    @Test
    void findOthersTrhowsException() {
        when(expenseCategoryRepository.findById(8L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> expenseCategoryAdapter.findOthers());

    }
}