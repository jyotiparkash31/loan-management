package com.rapibank.project.controller;

import com.rapibank.project.model.LoanRequest;
import com.rapibank.project.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
///api/reports/loan-requests
    @Autowired
    private ReportService reportService;

    @GetMapping("/loan-requests")
    public ResponseEntity<List<LoanRequest>> getLoanRequests(@RequestParam String startDate, @RequestParam String endDate) {
        List<LoanRequest> loanRequests = reportService.getLoanRequestsWithinDateRange(startDate, endDate);
        return ResponseEntity.ok(loanRequests);
    }

    @GetMapping("/loan-requests/csv")
    public ResponseEntity<byte[]> exportLoanRequestsToCSV(@RequestParam String startDate, @RequestParam String endDate) {
        byte[] csvData = reportService.exportLoanRequestsToCSV(startDate, endDate);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=\"loan_requests.csv\"")
                .body(csvData);
    }
}
