package br.com.lsargus.financeiro.services;

import br.com.lsargus.financeiro.data.IncomeBO;
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
    public List<IncomeBO> getAll() {
        return incomePersistence.getIncome();
    }

    @Override
    public IncomeBO getIncome(Long id) {
        return incomePersistence.getIncome(id);
    }

    @Override
    public IncomeBO addIncome(IncomeBO incomeBO) throws RuleException {

		if (checkIncomeDuplication(incomeBO))
            throw new RuleException("Receita duplicada: já existe uma receitas com os dados cadastrada no mês.");

        return incomePersistence.saveIncome(incomeBO);
    }

    @Override
    public IncomeBO updateIncome(Long id, IncomeBO incomeBO) throws RuleException {

        if (checkIncomeDuplication(incomeBO))
            throw new RuleException("Receita duplicada: já existe uma receitas com os dados cadastrada no mês.");

        IncomeBO newIncome = incomeBO;
        newIncome.setId(id);

        return incomePersistence.saveIncome(newIncome);
    }

    @Override
    public void deleteIncome(Long id) {
        incomePersistence.deleteIncome(id);
    }

	/**
	 * Verifica se existe uma receita cadastrata no mesmo ano/mes com a mesma descrição
	 * @param incomeBO
	 * @return true se existir uma receita cadastrata, false caso contrário
	 */
    public boolean checkIncomeDuplication(IncomeBO incomeBO) {
        return !incomePersistence.getIncomeByYearAndMonthAndDescription(incomeBO.getYear(), incomeBO.getMonth(), incomeBO.getDescription()).isEmpty();
    }

}
