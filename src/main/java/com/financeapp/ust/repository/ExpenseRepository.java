package com.financeapp.ust.repository;

import com.financeapp.ust.model.Expense;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {

    @Query("Select e From Expense e where e.user.id = :userId and e.category = :category")
    Expense findByuserIdAndCategory(int userId, String category);

    @Modifying
    @Transactional
    @Query("Delete From Expense e where e.user.id = :userId and e.category = :category")
    void deleteByIdAndCategory(int userId, String category);


    @Query("SELECT e.category AS category, SUM(e.amount) AS totalSpent " +
            "FROM Expense e WHERE e.user.id = :userId AND e.date BETWEEN :startDate AND :endDate " +
            "GROUP BY e.category")
    List<CategoryExpense> findTotalExpensesByCategoryAndPeriod(
            @Param("userId") int userId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    interface CategoryExpense {
        String getCategory();

        double getTotalSpent();
    }

}
