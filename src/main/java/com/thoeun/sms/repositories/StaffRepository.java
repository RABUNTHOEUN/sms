package com.thoeun.sms.repositories;

import com.thoeun.sms.models.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffRepository extends JpaRepository<Staff, Long> {
}