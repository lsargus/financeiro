package br.com.lsargus.financeiro.adapters;

import br.com.lsargus.financeiro.data.IncomeDto;
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
    public List<IncomeDto> getIncome() {
        List<Income> incomeList = incomeRepository.findAll();

        return incomeList.stream().map(r -> modelMapper.map(r, IncomeDto.class)).toList();
    }

    @Override
    public IncomeDto getIncome(Long id) {
        return  modelMapper.map(incomeRepository.findById(id), IncomeDto.class);
    }

    @Override
    public IncomeDto saveIncome(IncomeDto incomeDto) {
        Income income = modelMapper.map(incomeDto, Income.class);
        income = incomeRepository.save(income);

        return modelMapper.map(income, IncomeDto.class);
    }

    @Override
    public List<IncomeDto> getIncomeByYearAndMonthAndDescription(Integer year, Integer month, String description) {
        List<IncomeDto> incomeDtoList = new ArrayList<>();

        List<Income> incomeList = incomeRepository.findIncomeByYearAndMonthAndDescription(year, month, description);

        if (incomeList != null && !incomeList.isEmpty())
            incomeDtoList = incomeList.stream().map(r -> modelMapper.map(r, IncomeDto.class)).toList();

        return incomeDtoList;
    }

    @Override
    public void deleteIncome(Long id) {
        incomeRepository.deleteById(id);
    }
}
