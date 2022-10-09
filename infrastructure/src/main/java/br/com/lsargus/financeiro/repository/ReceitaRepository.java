package br.com.lsargus.financeiro.repository;

import br.com.lsargus.financeiro.entity.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long>{

}
