package com.thoeun.sms.services.course;

import com.thoeun.sms.models.Course;
import com.thoeun.sms.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService implements ICourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    @Override
    public Optional<Course> updateCourse(Long id, Course courseDetails) {
        return courseRepository.findById(id).map(course -> {
            course.setCourseName(courseDetails.getCourseName());
            course.setCourseCode(courseDetails.getCourseCode());
            course.setDescription(courseDetails.getDescription());
            course.setCredits(courseDetails.getCredits());
            return courseRepository.save(course);
        });
    }

    @Override
    public boolean deleteCourse(Long id) {
        return courseRepository.findById(id).map(course -> {
            courseRepository.delete(course);
            return true;
        }).orElse(false);
    }
}