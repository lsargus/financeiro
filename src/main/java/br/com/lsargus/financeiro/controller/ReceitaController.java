package br.com.lsargus.financeiro.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lsargus.financeiro.data.ReceitaDto;
import br.com.lsargus.financeiro.models.Receita;
import br.com.lsargus.financeiro.repository.ReceitaRepository;

@RestController
@RequestMapping("receita")
public class ReceitaController {
	
	private ReceitaRepository receitaRepository;
	private ModelMapper modelMapper;
	
	public ReceitaController(ReceitaRepository receitaRepository, ModelMapper modelMapper) {
		this.receitaRepository = receitaRepository;
		this.modelMapper = modelMapper;
	}
	
	@GetMapping
	public List<ReceitaDto> listAll() {
		List<Receita> receitas = receitaRepository.findAll();
		
		return receitas.stream().map(receita -> modelMapper.map(receitas, ReceitaDto.class)).collect(Collectors.toList());
	}
	
	@PostMapping
	public ReceitaDto addReceita(@RequestBody ReceitaDto receitaDto) {
		
	}

}
