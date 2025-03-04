package com.thoeun.sms.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    private String courseName;
    private String courseCode;
    private String description;
    private int credits;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Class> classes;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false) // Automatically set when the entity is created
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at") // Automatically updated when the entity is modified
    private Date updatedAt;

    // Getters and Setters
}