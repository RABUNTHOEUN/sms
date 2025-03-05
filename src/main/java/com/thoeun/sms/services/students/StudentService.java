package com.thoeun.sms.services.students;

import com.thoeun.sms.models.Student;
import com.thoeun.sms.repositories.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements IStudentService {

    private final StudentRepository studentRepository;
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents() {
        try {
            return studentRepository.findAll();
        } catch (Exception e) {
            logger.error("Error retrieving all students: {}", e.getMessage(), e);
            return Collections.emptyList(); // Return empty list on failure
        }
    }

    @Override
    public Optional<Student> getStudentById(Long id) {
        try {
            return studentRepository.findById(id);
        } catch (Exception e) {
            logger.error("Error retrieving student with ID {}: {}", id, e.getMessage(), e);
            return Optional.empty(); // Return empty Optional on failure
        }
    }

    @Override
    public Student createStudent(Student student) {
        try {
            return studentRepository.save(student);
        } catch (Exception e) {
            logger.error("Error creating student: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to create student", e); // Rethrow to be handled by controller
        }
    }

    @Override
    public Optional<Student> updateStudent(Long id, Student studentDetails) {
        try {
            return studentRepository.findById(id).map(student -> {
                student.setFirstName(studentDetails.getFirstName());
                student.setLastName(studentDetails.getLastName());
                student.setDateOfBirth(studentDetails.getDateOfBirth());
                student.setGender(studentDetails.getGender());
                student.setEmail(studentDetails.getEmail());
                student.setPhone(studentDetails.getPhone());
                student.setAddress(studentDetails.getAddress());
                student.setEnrollmentDate(studentDetails.getEnrollmentDate());
                student.setGuardianName(studentDetails.getGuardianName());
                student.setGuardianContact(studentDetails.getGuardianContact());
                return studentRepository.save(student);
            });
        } catch (Exception e) {
            logger.error("Error updating student with ID {}: {}", id, e.getMessage(), e);
            return Optional.empty(); // Return empty Optional on failure
        }
    }

    @Override
    public boolean deleteStudent(Long id) {
        try {
            return studentRepository.findById(id).map(student -> {
                studentRepository.delete(student);
                return true;
            }).orElse(false);
        } catch (Exception e) {
            logger.error("Error deleting student with ID {}: {}", id, e.getMessage(), e);
            return false; // Return false on failure
        }
    }
}