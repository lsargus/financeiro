package br.com.lsargus.financeiro.repository;

import br.com.lsargus.financeiro.entity.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {

    @Query(value = "SELECT r FROM FI001 r WHERE r.ano = ?1 AND r.mes = ?2 AND r.descricao like ?3 ")
    List<Receita> findReceitaByAnoAndMesAndDescricao(Integer ano, Integer mes, String descricao);
}
