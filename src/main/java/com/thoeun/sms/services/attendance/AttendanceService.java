package com.thoeun.sms.services.attendance;

import com.thoeun.sms.dto.AttendanceDTO;
import com.thoeun.sms.models.Attendance;
import com.thoeun.sms.models.Class;
import com.thoeun.sms.models.Student;
import com.thoeun.sms.repositories.AttendanceRepository;
import com.thoeun.sms.repositories.ClassRepository;
import com.thoeun.sms.repositories.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService implements IAttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final StudentRepository studentRepository;
    private final ClassRepository classRepository;
    private static final Logger logger = LoggerFactory.getLogger(AttendanceService.class);

    public AttendanceService(AttendanceRepository attendanceRepository,
                             StudentRepository studentRepository,
                             ClassRepository classRepository) {
        this.attendanceRepository = attendanceRepository;
        this.studentRepository = studentRepository;
        this.classRepository = classRepository;
    }

    @Override
    public List<Attendance> getAllAttendances() {
        try {
            return attendanceRepository.findAll();
        } catch (Exception e) {
            logger.error("Error retrieving all attendances: {}", e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    @Override
    public Optional<Attendance> getAttendanceById(Long id) {
        try {
            return attendanceRepository.findById(id);
        } catch (Exception e) {
            logger.error("Error retrieving attendance with ID {}: {}", id, e.getMessage(), e);
            return Optional.empty();
        }
    }

    @Override
    public Attendance createAttendance(AttendanceDTO attendanceDTO) {
        try {
            Attendance attendance = new Attendance();
            // Fetch Student and Class entities
            Student student = studentRepository.findById(attendanceDTO.getStudentId())
                    .orElseThrow(() -> new RuntimeException("Student with ID " + attendanceDTO.getStudentId() + " not found"));
            Class aClass = classRepository.findById(attendanceDTO.getClassId())
                    .orElseThrow(() -> new RuntimeException("Class with ID " + attendanceDTO.getClassId() + " not found"));

            attendance.setStudent(student);
            attendance.setAClass(aClass);
            attendance.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(attendanceDTO.getDate()));
            attendance.setStatus(attendanceDTO.getStatus());
            attendance.setRemarks(attendanceDTO.getRemarks());

            return attendanceRepository.save(attendance);
        } catch (Exception e) {
            logger.error("Error creating attendance: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to create attendance", e);
        }
    }

    @Override
    public Optional<Attendance> updateAttendance(Long id, AttendanceDTO attendanceDTO) {
        try {
            return attendanceRepository.findById(id).map(attendance -> {
                Student student = studentRepository.findById(attendanceDTO.getStudentId())
                        .orElseThrow(() -> new RuntimeException("Student with ID " + attendanceDTO.getStudentId() + " not found"));
                Class aClass = classRepository.findById(attendanceDTO.getClassId())
                        .orElseThrow(() -> new RuntimeException("Class with ID " + attendanceDTO.getClassId() + " not found"));

                attendance.setStudent(student);
                attendance.setAClass(aClass);
                try {
                    attendance.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(attendanceDTO.getDate()));
                } catch (Exception e) {
                    throw new RuntimeException("Invalid date format", e);
                }
                attendance.setStatus(attendanceDTO.getStatus());
                attendance.setRemarks(attendanceDTO.getRemarks());
                return attendanceRepository.save(attendance);
            });
        } catch (Exception e) {
            logger.error("Error updating attendance with ID {}: {}", id, e.getMessage(), e);
            return Optional.empty();
        }
    }

    @Override
    public boolean deleteAttendance(Long id) {
        try {
            return attendanceRepository.findById(id).map(attendance -> {
                attendanceRepository.delete(attendance);
                return true;
            }).orElse(false);
        } catch (Exception e) {
            logger.error("Error deleting attendance with ID {}: {}", id, e.getMessage(), e);
            return false;
        }
    }
}