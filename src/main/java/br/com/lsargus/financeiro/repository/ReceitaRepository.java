package br.com.lsargus.financeiro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.lsargus.financeiro.models.Receita;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long>{

}
