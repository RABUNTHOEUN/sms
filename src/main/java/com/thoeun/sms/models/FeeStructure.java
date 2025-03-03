package com.thoeun.sms.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Setter
@Getter
public class FeeStructure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feeId;

    private String feeName;
    private double amount;
    private String frequency; // One-Time, Monthly, Termly

    @OneToMany(mappedBy = "feeStructure", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payment> payments;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false) // Automatically set when the entity is created
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at") // Automatically updated when the entity is modified
    private Date updatedAt;

    // Getters and Setters
}