package com.financeapp.ust.repository;

import com.financeapp.ust.model.Goals;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface GoalsRepository extends JpaRepository<Goals, Integer> {

    @Query("Select g From Goals g where g.user.id = :userId and g.goalName = :goalName")
    Goals getGoalsByUserIdAndGoalName(int userId, String goalName);

    @Modifying
    @Transactional
    @Query("Delete From Goals g where g.user.id = :userId and g.goalName = :goalName")
    void deleteByUserIdAndGoalName(int userId, String goalName);
}
