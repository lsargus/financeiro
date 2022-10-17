package br.com.lsargus.financeiro.controller;

import br.com.lsargus.financeiro.data.ExpenseDto;
import br.com.lsargus.financeiro.data.IncomeDto;
import br.com.lsargus.financeiro.exceptions.RuleException;
import br.com.lsargus.financeiro.ports.api.ExpenseServicePort;
import br.com.lsargus.financeiro.ports.api.IncomeServicePort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("despesa")
public class ExpenseController {

    private ExpenseServicePort expenseService;

    public ExpenseController(ExpenseServicePort expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public ResponseEntity<List<ExpenseDto>> listAll() {
        return ResponseEntity.ok(expenseService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<ExpenseDto> getExpense(@PathVariable Long id) {
        return ResponseEntity.ok(expenseService.getExpense(id));
    }

    @PostMapping
    public ResponseEntity<Object> addExpense(@Valid @RequestBody ExpenseDto expenseDto) {
        try {
            return ResponseEntity.ok(expenseService.addExpense(expenseDto));
        } catch (RuleException ex) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ex.getMessage());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateExpense(@PathVariable Long id, @Valid @RequestBody ExpenseDto expenseDto) {
        try {
            return ResponseEntity.ok(expenseService.updateExpense(id, expenseDto));
        } catch (RuleException ex) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ex.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long id) {

        expenseService.deleteExpense(id);
        return ResponseEntity.ok("Registro apagado com sucesso.");

    }

}
