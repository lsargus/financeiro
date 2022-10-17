package br.com.lsargus.financeiro.repository;

import br.com.lsargus.financeiro.entity.Expense;
import br.com.lsargus.financeiro.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    @Query(value = "SELECT d FROM FI002 d WHERE d.year = ?1 AND d.month = ?2 AND d.description like ?3 ")
    List<Expense> findExpenseByYearAndMonthAndDescription(Integer year, Integer month, String description);
}
