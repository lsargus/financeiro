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
public class Despesa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FI002_id")
    private Long id;
    @Column(name = "FI002_ds_receita")
    private String descricao;
    @Column(name = "FI002_vl_receita")
    private BigDecimal valor;
    @Column(name = "FI002_nr_ano")
    private Integer ano;
    @Column(name = "FI002_nr_mes")
    private Integer mes;
    @Column(name = "FI002_dt_despesa")
    private LocalDateTime data;
}
