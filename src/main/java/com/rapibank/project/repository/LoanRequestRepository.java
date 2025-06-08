package com.rapibank.project.repository;

import com.rapibank.project.model.LoanRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LoanRequestRepository extends JpaRepository<LoanRequest, Long> {
    List<LoanRequest> findByStatus(String status);
    List<LoanRequest> findByCreatedDateBetween(LocalDateTime startDate, LocalDateTime endDate);
}

