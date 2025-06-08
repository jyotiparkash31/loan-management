package com.rapibank.project.controller;


import com.rapibank.project.model.LoanRequest;
import com.rapibank.project.service.LoanRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private LoanRequestService loanRequestService;

    @GetMapping("/pending-requests")
    public ResponseEntity<List<LoanRequest>> getPendingRequests() {
        List<LoanRequest> pendingRequests = loanRequestService.getPendingRequests();
        return ResponseEntity.ok(pendingRequests);
    }

    @PutMapping("/approve-request/{id}")
    public ResponseEntity<LoanRequest> approveRequest(@PathVariable Integer id) {
        LoanRequest approvedRequest = loanRequestService.approveRequest(id);
        return ResponseEntity.ok(approvedRequest);
    }

    @PutMapping("/decline-request/{id}")
    public ResponseEntity<LoanRequest> declineRequest(@PathVariable Integer id, @RequestParam String reason) {
        LoanRequest declinedRequest = loanRequestService.declineRequest(id, reason);
        return ResponseEntity.ok(declinedRequest);
    }
}
