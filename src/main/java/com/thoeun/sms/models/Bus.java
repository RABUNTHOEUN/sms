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
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long busId;

    private String busNumber;
    private int capacity;

    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Staff driver;

    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BusRoute> busRoutes;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false) // Automatically set when the entity is created
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at") // Automatically updated when the entity is modified
    private Date updatedAt;

    // Getters and Setters
}