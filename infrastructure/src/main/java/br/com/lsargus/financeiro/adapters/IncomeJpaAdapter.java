package br.com.lsargus.financeiro.adapters;

import br.com.lsargus.financeiro.data.IncomeBO;
import br.com.lsargus.financeiro.entity.Income;
import br.com.lsargus.financeiro.ports.spi.IncomePersistencePort;
import br.com.lsargus.financeiro.repository.IncomeRepository;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class IncomeJpaAdapter implements IncomePersistencePort {

    private final IncomeRepository incomeRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public IncomeJpaAdapter(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

    @Override
    public List<IncomeBO> getIncome() {
        List<Income> incomeList = incomeRepository.findAll();

        return incomeList.stream().map(r -> modelMapper.map(r, IncomeBO.class)).toList();
    }

    @Override
    public IncomeBO getIncome(Long id) {
        return  modelMapper.map(incomeRepository.findById(id), IncomeBO.class);
    }

    @Override
    public IncomeBO saveIncome(IncomeBO incomeBO) {
        Income income = modelMapper.map(incomeBO, Income.class);
        income = incomeRepository.save(income);

        return modelMapper.map(income, IncomeBO.class);
    }

    @Override
    public List<IncomeBO> getIncomeByYearAndMonthAndDescription(Integer year, Integer month, String description) {
        List<IncomeBO> incomeBOList = new ArrayList<>();

        List<Income> incomeList = incomeRepository.findIncomeByYearAndMonthAndDescription(year, month, description);

        if (incomeList != null && !incomeList.isEmpty())
            incomeBOList = incomeList.stream().map(r -> modelMapper.map(r, IncomeBO.class)).toList();

        return incomeBOList;
    }

    @Override
    public void deleteIncome(Long id) {
        incomeRepository.deleteById(id);
    }
}
