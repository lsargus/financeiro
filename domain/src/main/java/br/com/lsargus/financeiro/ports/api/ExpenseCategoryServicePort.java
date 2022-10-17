package br.com.lsargus.financeiro.ports.api;

import br.com.lsargus.financeiro.data.ExpenseCategoryDto;

import java.util.List;

public interface ExpenseCategoryServicePort {

    List<ExpenseCategoryDto> getAll();

    ExpenseCategoryDto addCategory(ExpenseCategoryDto categoryDto);

}
