package com.thoeun.sms.services.course;

import com.thoeun.sms.models.Course;
import java.util.List;
import java.util.Optional;

public interface ICourseService {
    List<Course> getAllCourses();
    Optional<Course> getCourseById(Long id);
    Course createCourse(Course course);
    Optional<Course> updateCourse(Long id, Course courseDetails);
    boolean deleteCourse(Long id);
}