package com.thoeun.sms.repositories;

import com.thoeun.sms.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}