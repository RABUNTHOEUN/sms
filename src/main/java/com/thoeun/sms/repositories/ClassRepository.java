package com.thoeun.sms.repositories;

import com.thoeun.sms.models.Class;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassRepository extends JpaRepository<Class, Long> {
}