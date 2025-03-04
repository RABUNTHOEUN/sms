package com.thoeun.sms.services.students;

import com.thoeun.sms.models.Student;

import java.util.List;
import java.util.Optional;

public interface IStudentService {
    List<Student> getAllStudents();
    Optional<Student> getStudentById(Long id);
    Student createStudent(Student student);
    Optional<Student> updateStudent(Long id, Student studentDetails);
    boolean deleteStudent(Long id);
}
