package br.com.lsargus.financeiro.service.imp;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import br.com.lsargus.financeiro.data.ReceitaDto;
import br.com.lsargus.financeiro.repository.ReceitaRepository;
import br.com.lsargus.financeiro.service.ReceitaService;

@Service
public class ReceitaServiceImp implements ReceitaService {
	
	private ReceitaRepository receitaRepository;
	private ModelMapper modelMapper;
	
	public ReceitaServiceImp(ReceitaRepository receitaRepository, ModelMapper modelMapper) {
		this.receitaRepository = receitaRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public List<ReceitaDto> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
