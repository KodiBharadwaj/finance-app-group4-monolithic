package com.financeapp.ust.repository;

import com.financeapp.ust.model.Budget;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BudgetRepository extends JpaRepository<Budget, Integer> {

    @Query("Select b From Budget b where b.user.id = :userId and b.category = :category")
    Budget findByuserIdAndCategory(int userId, String category);

    @Modifying
    @Transactional
    @Query("Delete From Budget b where b.user.id = :userId and b.category = :category")
    void deleteByuserIdAndCategory(int userId, String category);


    @Query("SELECT b FROM Budget b WHERE b.user.id = :userId")
    List<Budget> findBudgetsByUserId(int userId);

}
