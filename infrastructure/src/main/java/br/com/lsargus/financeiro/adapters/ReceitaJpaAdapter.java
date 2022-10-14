package br.com.lsargus.financeiro.adapters;

import br.com.lsargus.financeiro.data.ReceitaDto;
import br.com.lsargus.financeiro.entity.Receita;
import br.com.lsargus.financeiro.ports.spi.ReceitaPersistencePort;
import br.com.lsargus.financeiro.repository.ReceitaRepository;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class ReceitaJpaAdapter implements ReceitaPersistencePort {

    private final ReceitaRepository receitaRepository;

    private ModelMapper modelMapper = new ModelMapper();

    public ReceitaJpaAdapter(ReceitaRepository receitaRepository) {
        this.receitaRepository = receitaRepository;
    }

    @Override
    public List<ReceitaDto> getReceitas() {
        List<Receita> receitaList = receitaRepository.findAll();

        return receitaList.stream().map(r -> modelMapper.map(r, ReceitaDto.class)).toList();
    }

    @Override
    public ReceitaDto getReceita(Long id) {
        return  modelMapper.map(receitaRepository.findById(id), ReceitaDto.class);
    }

    public ReceitaDto saveReceita(ReceitaDto receitaDto) {
        Receita receita = modelMapper.map(receitaDto, Receita.class);
        receita = receitaRepository.save(receita);

        return modelMapper.map(receita, ReceitaDto.class);
    }

    @Override
    public List<ReceitaDto> getReceitaByAnoAndMesAndDescricao(Integer ano, Integer mes, String descricao) {
        List<ReceitaDto> receitasDto = new ArrayList<>();

        List<Receita> receitas = receitaRepository.findReceitaByAnoAndMesAndDescricao(ano, mes, descricao);

        if (receitas != null && !receitas.isEmpty())
            receitasDto = receitas.stream().map(r -> modelMapper.map(r, ReceitaDto.class)).toList();

        return receitasDto;
    }

    @Override
    public void deleteReceita(Long id) {
        receitaRepository.deleteById(id);
    }
}
