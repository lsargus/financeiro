package br.com.lsargus.financeiro.controller;

import br.com.lsargus.financeiro.data.ExpenseBO;
import br.com.lsargus.financeiro.dto.ExpenseDTO;
import br.com.lsargus.financeiro.exceptions.RuleException;
import br.com.lsargus.financeiro.mapper.ExpenseMapper;
import br.com.lsargus.financeiro.ports.api.ExpenseServicePort;
import org.modelmapper.ModelMapper;
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
import java.util.NoSuchElementException;

@RestController
@RequestMapping("despesa")
public class ExpenseController {

    private final ExpenseServicePort expenseService;

    private ModelMapper modelMapper = new ExpenseMapper();

    public ExpenseController(ExpenseServicePort expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public ResponseEntity<List<ExpenseDTO>> listAll() {
        List<ExpenseDTO> expenseDTOList = expenseService.getAll().stream().map(e -> modelMapper.map(e,ExpenseDTO.class)).toList();
        return ResponseEntity.ok(expenseDTOList);
    }

    @GetMapping("{id}")
    public ResponseEntity<ExpenseDTO> getExpense(@PathVariable Long id) {
        ExpenseDTO expenseDTO = modelMapper.map(expenseService.getExpense(id), ExpenseDTO.class);
        return ResponseEntity.ok(expenseDTO);
    }

    @PostMapping

    public ResponseEntity<Object> addExpense(@Valid @RequestBody ExpenseDTO expenseDTO) {
        ExpenseBO expenseBO = modelMapper.map(expenseDTO, ExpenseBO.class);
        try {
            return ResponseEntity.ok(modelMapper.map(expenseService.addExpense(expenseBO), ExpenseDTO.class));
        } catch (RuleException ex) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ex.getMessage());
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Erro ao categorizar despesa");
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateExpense(@PathVariable Long id, @Valid @RequestBody ExpenseDTO expenseDTO) {
        ExpenseBO expenseBO = modelMapper.map(expenseDTO, ExpenseBO.class);
        try {
            return ResponseEntity.ok(modelMapper.map(expenseService.updateExpense(id, expenseBO), ExpenseDTO.class));
        } catch (RuleException ex) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(ex.getMessage());
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Erro ao categorizar despesa");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long id) {

        expenseService.deleteExpense(id);
        return ResponseEntity.ok("Registro apagado com sucesso.");

    }

}
