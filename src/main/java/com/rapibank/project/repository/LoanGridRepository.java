package com.rapibank.project.repository;


import com.rapibank.project.model.LoanGrid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoanGridRepository extends JpaRepository<LoanGrid, Integer> {
    Optional<LoanGrid> findByCibilScoreFromLessThanEqualAndCibilScoreToGreaterThanEqualAndAmountGreaterThanEqualAndIsActiveTrueAndIsDeletedFalse(
            Integer cibilScoreFrom, Integer cibilScoreTo, Integer amount);

    Optional<LoanGrid> findByCibilScoreFromLessThanEqualAndCibilScoreToGreaterThanEqualAndIsActiveTrueAndIsDeletedFalse(
            Integer cibilScoreFrom, Integer cibilScoreTo);

    List<LoanGrid> findByIsActiveTrueAndIsDeletedFalse();
}

