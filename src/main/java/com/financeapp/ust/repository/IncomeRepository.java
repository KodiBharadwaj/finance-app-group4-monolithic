package com.financeapp.ust.repository;


import com.financeapp.ust.model.Income;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IncomeRepository extends JpaRepository<Income, Integer> {

    @Query("Select i from Income i where i.user.id = :userId and i.source = :source")
    Income findByIdAndSource(int userId, String source);

    @Modifying
    @Transactional
    @Query("Delete From Income i where i.user.id = :userId and i.source = :source")
    void deleteByIdAndSource(int userId, String source);

}
