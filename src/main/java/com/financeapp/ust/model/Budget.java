package com.financeapp.ust.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "budget_table")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;

    @Column
    private String category;

    @Column
    private double currentSpending;

    @Column
    private double moneyLimit;

    @ManyToOne
    @JsonIgnore
    private User user;

}
