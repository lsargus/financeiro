package br.com.lsargus.financeiro.repository;

import br.com.lsargus.financeiro.entity.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseCategoryRepository extends JpaRepository<ExpenseCategory, Long> {
}
