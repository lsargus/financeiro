package br.com.lsargus.financeiro.ports.spi;

import br.com.lsargus.financeiro.data.IncomeDto;

import java.util.List;

public interface IncomePersistencePort {

    List<IncomeDto> getIncome();

    IncomeDto getIncome(Long id);

    IncomeDto saveIncome(IncomeDto incomeDto);

    List<IncomeDto> getIncomeByYearAndMonthAndDescription(Integer year, Integer month, String description);

    void deleteIncome(Long id);
}
