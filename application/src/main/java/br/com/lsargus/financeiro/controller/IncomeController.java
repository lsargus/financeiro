package br.com.lsargus.financeiro.controller;

import br.com.lsargus.financeiro.data.IncomeDto;
import br.com.lsargus.financeiro.exceptions.RuleException;
import br.com.lsargus.financeiro.ports.api.IncomeServicePort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("receita")
public class IncomeController {

    private IncomeServicePort incomeService;

    public IncomeController(IncomeServicePort incomeService) {
        this.incomeService = incomeService;
    }

    @GetMapping
    public ResponseEntity<List<IncomeDto>> listAll() {
        return ResponseEntity.ok(incomeService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<IncomeDto> getIncome(@PathVariable Long id) {
        return ResponseEntity.ok(incomeService.getIncome(id));
    }

    @PostMapping
    public ResponseEntity<Object> addIncome(@Valid @RequestBody IncomeDto incomeDto) {
        try {
            return ResponseEntity.ok(incomeService.addIncome(incomeDto));
        } catch (RuleException ex) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ex.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateIncome(@PathVariable Long id, @Valid @RequestBody IncomeDto incomeDto) {
        try {
            return ResponseEntity.ok(incomeService.updateIncome(id, incomeDto));
        } catch (RuleException ex) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ex.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteIncome(@PathVariable Long id) {

        incomeService.deleteIncome(id);
        return ResponseEntity.ok("Registro apagado com sucesso.");

    }

}
