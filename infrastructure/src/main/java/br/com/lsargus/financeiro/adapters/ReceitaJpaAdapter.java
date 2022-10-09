package br.com.lsargus.financeiro.adapters;

import br.com.lsargus.financeiro.data.ReceitaDto;
import br.com.lsargus.financeiro.entity.Receita;
import br.com.lsargus.financeiro.ports.spi.ReceitaPersistencePort;
import br.com.lsargus.financeiro.repository.ReceitaRepository;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ReceitaJpaAdapter implements ReceitaPersistencePort {

    private final ReceitaRepository receitaRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public ReceitaJpaAdapter(ReceitaRepository receitaRepository) {
        this.receitaRepository = receitaRepository;
    }

    @Override
    public List<ReceitaDto> getReceitas() {
        List<Receita> receitaList = receitaRepository.findAll();

        return receitaList.stream().map( r -> modelMapper.map(r,ReceitaDto.class)).toList();
    }
}
