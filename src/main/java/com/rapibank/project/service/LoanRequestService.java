package com.rapibank.project.service;

import com.rapibank.project.model.LoanGrid;
import com.rapibank.project.model.LoanRequest;
import com.rapibank.project.repository.LoanGridRepository;
import com.rapibank.project.repository.LoanRequestRepository;
import com.rapibank.project.repository.PincodeBlacklistingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LoanRequestService {

    private final LoanRequestRepository loanRequestRepository;
    private final LoanGridRepository loanGridRepository;
    private final PincodeBlacklistingRepository pincodeBlacklistingRepository;

    @Autowired
    public LoanRequestService(
            LoanRequestRepository loanRequestRepository,
            LoanGridRepository loanGridRepository,
            PincodeBlacklistingRepository pincodeBlacklistingRepository) {
        this.loanRequestRepository = loanRequestRepository;
        this.loanGridRepository = loanGridRepository;
        this.pincodeBlacklistingRepository = pincodeBlacklistingRepository;
    }

    @Transactional
    public LoanRequest submitLoanRequest(LoanRequest request) {
        // 1. Pincode Blacklist Check
        boolean isBlacklisted = pincodeBlacklistingRepository.existsByPincodeAndIsActiveTrue(request.getPincode());
        if (isBlacklisted) {
            request.setStatus("Rejected");
            request.setRejectDeclineReason("Pincode blacklisted");
            LocalDateTime now = LocalDateTime.now();
            request.setCreatedDate(now);
            request.setUpdatedDate(now);
            return loanRequestRepository.save(request);
        }

        // 2. Eligibility Assessment Based on CIBIL & LoanGrid
        Optional<LoanGrid> gridOptional = loanGridRepository
                .findByCibilScoreFromLessThanEqualAndCibilScoreToGreaterThanEqualAndAmountGreaterThanEqualAndIsActiveTrueAndIsDeletedFalse(
                        request.getCibilScore(), request.getCibilScore(), request.getRequestedLoanAmount());

        if (gridOptional.isEmpty()) {
            request.setStatus("Rejected");
            request.setRejectDeclineReason("No eligible loan grid found for given CIBIL score and amount");
            LocalDateTime now = LocalDateTime.now();
            request.setCreatedDate(now);
            request.setUpdatedDate(now);
            return loanRequestRepository.save(request);
        }

        LoanGrid grid = gridOptional.get();
        request.setEligibleLoanAmount(grid.getAmount());
        request.setEligibleTenure(grid.getTenure());
        request.setStatus("Pending");

        LocalDateTime now = LocalDateTime.now();
        request.setCreatedDate(now);
        request.setUpdatedDate(now);

        return loanRequestRepository.save(request);
    }

    public List<LoanRequest> getPendingRequests() {
        return loanRequestRepository.findByStatus("Pending");
    }
    @Transactional
    public LoanRequest approveRequest(Integer id) {
        LoanRequest request = loanRequestRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new RuntimeException("Loan request not found"));
        request.setStatus("Approved");
        request.setLoanApprovedDate(LocalDateTime.now());
        return loanRequestRepository.save(request);
    }
    @Transactional
    public LoanRequest declineRequest(Integer id, String reason) {
        LoanRequest request = loanRequestRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new RuntimeException("Loan request not found"));
        request.setStatus("Declined");
        request.setRejectDeclineReason(reason);
        request.setLoanDeclineDate(LocalDateTime.now());
        return loanRequestRepository.save(request);
    }
}
