package br.com.lsargus.financeiro.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseCategoryBO {

    private Long id;
    @NotEmpty
    @Size(min = 1, max = 255, message = "Descrição deve ser informada")
    private String description;
}
