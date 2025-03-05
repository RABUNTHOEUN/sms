package com.thoeun.sms.services.attendance;

import com.thoeun.sms.dto.AttendanceDTO;
import com.thoeun.sms.models.Attendance;
import java.util.List;
import java.util.Optional;

public interface IAttendanceService {
    List<Attendance> getAllAttendances();
    Optional<Attendance> getAttendanceById(Long id);
    Attendance createAttendance(AttendanceDTO attendance);
    Optional<Attendance> updateAttendance(Long id, AttendanceDTO attendanceDetails);
    boolean deleteAttendance(Long id);
}