package com.rapibank.project.model;


import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tbl_pincode_blacklisting")
public class PincodeBlacklisting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 6)
    private String pincode;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "created_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    private LocalDateTime createdDate;

    // Getters and setters
    // ...
}
