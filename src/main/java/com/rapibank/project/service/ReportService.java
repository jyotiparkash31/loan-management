package com.rapibank.project.service;

import com.rapibank.project.model.LoanRequest;
import com.rapibank.project.repository.LoanRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private LoanRequestRepository loanRequestRepository;

    public List<LoanRequest> getLoanRequestsWithinDateRange(String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        return loanRequestRepository.findByCreatedDateBetween(start.atStartOfDay(), end.atTime(23, 59, 59));
    }

    public byte[] exportLoanRequestsToCSV(String startDate, String endDate) {
        List<LoanRequest> loanRequests = getLoanRequestsWithinDateRange(startDate, endDate);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintWriter writer = new PrintWriter(outputStream);

        // Write CSV header
        writer.println("Request ID,Full Name,CIBIL Score,Requested Amount,Status");

        // Write loan request data
        for (LoanRequest request : loanRequests) {
            writer.printf("%d,%s,%d,%d,%s%n",
                    request.getRequestId(),
                    request.getFullName(),
                    request.getCibilScore(),
                    request.getRequestedLoanAmount(),
                    request.getStatus());
        }

        writer.flush();
        return outputStream.toByteArray();
    }
}
