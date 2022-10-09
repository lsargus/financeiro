package br.com.lsargus.financeiro.controller;

import br.com.lsargus.financeiro.data.ReceitaDto;
import br.com.lsargus.financeiro.ports.api.ReceitaServicePort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("receita")
public class ReceitaController {
	
	private ReceitaServicePort receitaService;
	
	public ReceitaController(ReceitaServicePort receitaService) {
		this.receitaService = receitaService;
	}
	
	@GetMapping
	public List<ReceitaDto> listAll() {
		return receitaService.getAll();
	}

}
