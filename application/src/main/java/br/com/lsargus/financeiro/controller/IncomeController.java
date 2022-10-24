package br.com.lsargus.financeiro.controller;

import br.com.lsargus.financeiro.data.IncomeBO;
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
    public ResponseEntity<List<IncomeBO>> listAll() {
        return ResponseEntity.ok(incomeService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<IncomeBO> getIncome(@PathVariable Long id) {
        return ResponseEntity.ok(incomeService.getIncome(id));
    }

    @PostMapping
    public ResponseEntity<Object> addIncome(@Valid @RequestBody IncomeBO incomeBO) {
        try {
            return ResponseEntity.ok(incomeService.addIncome(incomeBO));
        } catch (RuleException ex) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ex.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateIncome(@PathVariable Long id, @Valid @RequestBody IncomeBO incomeBO) {
        try {
            return ResponseEntity.ok(incomeService.updateIncome(id, incomeBO));
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
