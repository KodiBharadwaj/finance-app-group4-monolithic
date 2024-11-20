package com.financeapp.ust.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.financeapp.ust.model.User;

import java.time.LocalDate;

public record GoalsDto(int id, String goalName, double currentAmount, double targetAmount, LocalDate deadLine,
                       User user) {
}
