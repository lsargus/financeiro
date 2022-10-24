package br.com.lsargus.financeiro.ports.api;

import br.com.lsargus.financeiro.data.ExpenseCategoryBO;

import java.util.List;

public interface ExpenseCategoryServicePort {

    List<ExpenseCategoryBO> getAll();

    ExpenseCategoryBO addCategory(ExpenseCategoryBO categoryDto);

}
