package com.rapibank.project.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_loan_request")
@Data
public class LoanRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer requestId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "full_name", nullable = false, length = 255)
    private String fullName;

    @Column(name = "dob", nullable = false)
    private LocalDate dob;

    @Column(name = "cibil_score", nullable = false)
    private Integer cibilScore;

    @Column(name = "address1", columnDefinition = "TEXT")
    private String address1;

    @Column(name = "pincode", nullable = false, length = 6)
    private String pincode;

    @Column(name = "city", nullable = false, length = 50)
    private String city;

    @Column(name = "requested_loan_amount", nullable = false)
    private Integer requestedLoanAmount;

    @Column(name = "requested_tenure", nullable = false)
    private Integer requestedTenure;

    @Column(name = "eligible_loan_amount")
    private Integer eligibleLoanAmount;

    @Column(name = "eligible_tenure")
    private Integer eligibleTenure;

    @Column(nullable = false, length = 8)
    private String status; // 'Rejected', 'Pending', 'Approved', 'Declined'

    @Column(name = "loan_approved_date")
    private LocalDateTime loanApprovedDate;

    @Column(name = "loan_approved_by")
    private Integer loanApprovedBy;

    @Column(name = "loan_decline_date")
    private LocalDateTime loanDeclineDate;

    @Column(name = "loan_decline_by")
    private Integer loanDeclineBy;

    @Column(name = "reject_decline_reason", columnDefinition = "TEXT")
    private String rejectDeclineReason;

    @Column(columnDefinition = "TEXT")
    private String remarks;

    @Column(name = "created_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "updated_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private LocalDateTime updatedDate;


}
