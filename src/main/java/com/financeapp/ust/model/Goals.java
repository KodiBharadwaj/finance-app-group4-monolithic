package com.financeapp.ust.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "goals_table")
public class Goals {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;

    @Column
    private String goalName;

    @Column
    private double currentAmount;

    @Column
    private double targetAmount;

    @Column
    private LocalDate deadLine;

    @ManyToOne
    @JsonIgnore
    private User user;
}
