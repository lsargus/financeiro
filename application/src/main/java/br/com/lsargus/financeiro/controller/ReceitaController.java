package br.com.lsargus.financeiro.controller;

import br.com.lsargus.financeiro.data.ReceitaDto;
import br.com.lsargus.financeiro.exceptions.RuleException;
import br.com.lsargus.financeiro.ports.api.ReceitaServicePort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("receita")
public class ReceitaController {

    private ReceitaServicePort receitaService;

    public ReceitaController(ReceitaServicePort receitaService) {
        this.receitaService = receitaService;
    }

    @GetMapping
    public ResponseEntity<List<ReceitaDto>> listAll() {
        return ResponseEntity.ok(receitaService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<ReceitaDto> listAll(@PathVariable Long id) {
        return ResponseEntity.ok(receitaService.getReceita(id));
    }

    @PostMapping
    public ResponseEntity<Object> addReceita(@Valid @RequestBody ReceitaDto receitaDto) {
        try {
            return ResponseEntity.ok(receitaService.addReceita(receitaDto));
        } catch (RuleException ex) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ex.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateReceita(@PathVariable Long id, @Valid @RequestBody ReceitaDto receitaDto) {
        try {
            return ResponseEntity.ok(receitaService.updateReceita(id, receitaDto));
        } catch (RuleException ex) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ex.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteReceita(@PathVariable Long id) {

        receitaService.deleteReceita(id);
        return ResponseEntity.ok("Registro apagado com sucesso.");

    }

}
