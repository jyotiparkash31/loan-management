package com.rapibank.project.controller;

import com.rapibank.project.dto.EligibilityResponse;
import com.rapibank.project.model.LoanRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.rapibank.project.model.LoanGrid;
import com.rapibank.project.repository.LoanGridRepository;
import com.rapibank.project.repository.LoanRequestRepository;
import com.rapibank.project.repository.PincodeBlacklistingRepository;


import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/loan")
public class LoanRequestController {
    @Autowired
    private LoanRequestRepository loanRequestRepository;

    @Autowired
    private LoanGridRepository loanGridRepository;

    @Autowired
    private PincodeBlacklistingRepository pincodeBlacklistingRepository;

    @PostMapping("/submit-request")
    public ResponseEntity<?> submitRequest(@RequestBody LoanRequest loanRequest) {
        // Eligibility and pincode blacklist check
        String pincode = loanRequest.getPincode();
        int cibilScore = loanRequest.getCibilScore();

        // 1. Check pincode blacklist
        boolean isBlacklisted = pincodeBlacklistingRepository.existsByPincodeAndIsActiveTrue(pincode);
        if (isBlacklisted) {
            loanRequest.setStatus("Rejected");
            loanRequest.setRejectDeclineReason("Pincode blacklisted");
            loanRequest.setCreatedDate(LocalDateTime.now());
            loanRequest.setUpdatedDate(LocalDateTime.now());
            LoanRequest savedRequest = loanRequestRepository.save(loanRequest);
            return ResponseEntity.ok(savedRequest);
        }

        // 2. Check eligibility
        Optional<LoanGrid> eligibleGridOpt = loanGridRepository.findByCibilScoreFromLessThanEqualAndCibilScoreToGreaterThanEqualAndAmountGreaterThanEqualAndIsActiveTrueAndIsDeletedFalse(
                cibilScore, cibilScore, loanRequest.getRequestedLoanAmount());

        if (eligibleGridOpt.isEmpty()) {
            loanRequest.setStatus("Rejected");
            loanRequest.setRejectDeclineReason("No eligible loan grid found for CIBIL score and amount");
            loanRequest.setCreatedDate(LocalDateTime.now());
            loanRequest.setUpdatedDate(LocalDateTime.now());
            LoanRequest savedRequest = loanRequestRepository.save(loanRequest);
            return ResponseEntity.ok(savedRequest);
        }

        LoanGrid eligibleGrid = eligibleGridOpt.get();
        loanRequest.setEligibleLoanAmount(eligibleGrid.getAmount());
        loanRequest.setEligibleTenure(eligibleGrid.getTenure());
        loanRequest.setStatus("Pending");
        loanRequest.setCreatedDate(LocalDateTime.now());
        loanRequest.setUpdatedDate(LocalDateTime.now());

        LoanRequest savedRequest = loanRequestRepository.save(loanRequest);
        return ResponseEntity.ok(savedRequest);
    }

    @GetMapping("/eligibility-check")
    public ResponseEntity<?> checkEligibility(@RequestParam String pincode, @RequestParam Integer cibilScore) {

        boolean isBlacklisted = pincodeBlacklistingRepository.existsByPincodeAndIsActiveTrue(pincode);
        if (isBlacklisted) {
            return ResponseEntity.ok(
                    new EligibilityResponse(false, "Currently we are not offering loans in this area", null, null));
        }


        Optional<LoanGrid> eligibleGridOpt = loanGridRepository.findByCibilScoreFromLessThanEqualAndCibilScoreToGreaterThanEqualAndIsActiveTrueAndIsDeletedFalse(
                cibilScore, cibilScore);

        if (eligibleGridOpt.isEmpty()) {
            return ResponseEntity.ok(
                    new EligibilityResponse(false, "No eligible loan offer for provided CIBIL score", null, null));
        }

        LoanGrid eligibleGrid = eligibleGridOpt.get();

        return ResponseEntity.ok(
                new EligibilityResponse(true, "Eligible for loan", eligibleGrid.getAmount(), eligibleGrid.getTenure()));
    }


}

