package br.com.lsargus.financeiro.controller;

import br.com.lsargus.financeiro.data.ExpenseCategoryBO;
import br.com.lsargus.financeiro.dto.ExpenseCategoryDTO;
import br.com.lsargus.financeiro.ports.api.ExpenseCategoryServicePort;
import org.modelmapper.ModelMapper;
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

    private ModelMapper modelMapper = new ModelMapper();

    public ExpenseCategoryController(ExpenseCategoryServicePort expenseCategoryService) {
        this.expenseCategoryService = expenseCategoryService;
    }

    @GetMapping
    public ResponseEntity<List<ExpenseCategoryDTO>> listAll() {
        List<ExpenseCategoryDTO> expenseCategoryDTOList = expenseCategoryService.getAll().stream().map(e -> modelMapper.map(e,ExpenseCategoryDTO.class)).toList();
        return ResponseEntity.ok(expenseCategoryDTOList);
    }

    @PostMapping
    public ResponseEntity<Object> addExpenseCategory(@Valid @RequestBody ExpenseCategoryDTO expenseCategoryDTO) {
        ExpenseCategoryBO expenseCategoryBO = modelMapper.map(expenseCategoryDTO, ExpenseCategoryBO.class);
        ExpenseCategoryDTO newExpenseCategoryDTO = modelMapper.map(expenseCategoryService.addCategory(expenseCategoryBO), ExpenseCategoryDTO.class);
        return  ResponseEntity.ok(newExpenseCategoryDTO);
    }
}
