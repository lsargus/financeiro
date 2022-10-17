package br.com.lsargus.financeiro.services;

import br.com.lsargus.financeiro.data.IncomeDto;
import br.com.lsargus.financeiro.exceptions.RuleException;
import br.com.lsargus.financeiro.ports.api.IncomeServicePort;
import br.com.lsargus.financeiro.ports.spi.IncomePersistencePort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeServiceImp implements IncomeServicePort {

    private final IncomePersistencePort incomePersistence;

    public IncomeServiceImp(IncomePersistencePort incomePersistence) {
        this.incomePersistence = incomePersistence;
    }

    @Override
    public List<IncomeDto> getAll() {
        return incomePersistence.getIncome();
    }

    @Override
    public IncomeDto getIncome(Long id) {
        return incomePersistence.getIncome(id);
    }

    @Override
    public IncomeDto addIncome(IncomeDto incomeDto) throws RuleException {

		if (checkIncomeDuplication(incomeDto))
            throw new RuleException("Receita duplicada: já existe uma receitas com os dados cadastrada no mês.");

        return incomePersistence.saveIncome(incomeDto);
    }

    @Override
    public IncomeDto updateIncome(Long id, IncomeDto incomeDto) throws RuleException {

        if (checkIncomeDuplication(incomeDto))
            throw new RuleException("Receita duplicada: já existe uma receitas com os dados cadastrada no mês.");

        IncomeDto newIncome = incomeDto;
        newIncome.setId(id);

        return incomePersistence.saveIncome(newIncome);
    }

    @Override
    public void deleteIncome(Long id) {
        incomePersistence.deleteIncome(id);
    }

	/**
	 * Verifica se existe uma receita cadastrata no mesmo ano/mes com a mesma descrição
	 * @param incomeDto
	 * @return true se existir uma receita cadastrata, false caso contrário
	 */
    public boolean checkIncomeDuplication(IncomeDto incomeDto) {
        return !incomePersistence.getIncomeByYearAndMonthAndDescription(incomeDto.getYear(), incomeDto.getMonth(), incomeDto.getDescription()).isEmpty();
    }

}
