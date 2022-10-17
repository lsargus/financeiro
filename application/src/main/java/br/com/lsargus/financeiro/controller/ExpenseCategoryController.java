package br.com.lsargus.financeiro.controller;

import br.com.lsargus.financeiro.data.ExpenseCategoryDto;
import br.com.lsargus.financeiro.ports.api.ExpenseCategoryServicePort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("despesa/categoria")
public class ExpenseCategoryController {

    private final ExpenseCategoryServicePort expenseCategoryService;

    public ExpenseCategoryController(ExpenseCategoryServicePort expenseCategoryService) {
        this.expenseCategoryService = expenseCategoryService;
    }

    @GetMapping
    public ResponseEntity<List<ExpenseCategoryDto>> listAll() {
        return ResponseEntity.ok(expenseCategoryService.getAll());
    }

    @PostMapping
    public ResponseEntity<Object> addExpenseCategory(@Valid @RequestBody ExpenseCategoryDto expenseCategoryDto) {
        return  ResponseEntity.ok(expenseCategoryService.addCategory(expenseCategoryDto));
    }
}
