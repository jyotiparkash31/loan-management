package com.rapibank.project.repository;

import com.rapibank.project.model.PincodeBlacklisting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PincodeBlacklistingRepository extends JpaRepository<PincodeBlacklisting, Integer> {
    boolean existsByPincodeAndIsActiveTrue(String pincode);
}
