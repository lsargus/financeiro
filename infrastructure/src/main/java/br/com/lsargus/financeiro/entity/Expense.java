package br.com.lsargus.financeiro.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity(name = "FI002")
@NoArgsConstructor
@AllArgsConstructor
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FI002_id")
    private Long id;
    @Column(name = "FI002_ds_despesa")
    private String description;
    @Column(name = "FI002_vl_despesa")
    private BigDecimal value;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="FI002_id_categoria", nullable=false)
    private ExpenseCategory category;
    @Column(name = "FI002_nr_ano")
    private Integer year;
    @Column(name = "FI002_nr_mes")
    private Integer month;
    @Column(name = "FI002_dt_despesa")
    private LocalDateTime date;
}
