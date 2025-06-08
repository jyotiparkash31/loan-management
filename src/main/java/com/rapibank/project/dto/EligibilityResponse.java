package com.rapibank.project.dto;

public class EligibilityResponse {
    private boolean eligible;
    private String message;
    private Integer maxEligibleAmount;
    private Integer maxEligibleTenure;

    public EligibilityResponse(boolean eligible, String message, Integer maxEligibleAmount, Integer maxEligibleTenure) {
        this.eligible = eligible;
        this.message = message;
        this.maxEligibleAmount = maxEligibleAmount;
        this.maxEligibleTenure = maxEligibleTenure;
    }

    public boolean isEligible() {
        return eligible;
    }

    public String getMessage() {
        return message;
    }

    public Integer getMaxEligibleAmount() {
        return maxEligibleAmount;
    }

    public Integer getMaxEligibleTenure() {
        return maxEligibleTenure;
    }
}