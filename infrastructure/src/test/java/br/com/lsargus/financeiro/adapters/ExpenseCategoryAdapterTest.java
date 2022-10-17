package br.com.lsargus.financeiro.adapters;

import br.com.lsargus.financeiro.data.ExpenseCategoryDto;
import br.com.lsargus.financeiro.ports.spi.ExpenseCategoryPersistencePort;
import br.com.lsargus.financeiro.ports.spi.ExpensePersistencePort;
import br.com.lsargus.financeiro.repository.ExpenseCategoryRepository;
import br.com.lsargus.financeiro.services.ExpenseServiceImp;
import net.bytebuddy.dynamic.DynamicType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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