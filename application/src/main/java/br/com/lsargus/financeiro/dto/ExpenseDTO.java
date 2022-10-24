package br.com.lsargus.financeiro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseDTO {

    private Long id;
    @NotEmpty
    @Size(min = 1, max = 255, message = "Descrição deve ser informada")
    private String description;

    private Long expenseCategoryId;

    private String expenseCategoryDescription;

    @NotNull
    private BigDecimal value;
    @NotNull
    private Integer year;
    @NotNull
    private Integer month;
    @NotNull
    private LocalDateTime date;
}
