package com.rapibank.project.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tbl_loan_grid")
public class LoanGrid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "cibil_score_from", nullable = false)
    private Integer cibilScoreFrom;

    @Column(name = "cibil_score_to", nullable = false)
    private Integer cibilScoreTo;

    @Column(nullable = false)
    private Integer amount;

    @Column(nullable = false)
    private Integer tenure;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    // Getters and setters
    // ...
}
