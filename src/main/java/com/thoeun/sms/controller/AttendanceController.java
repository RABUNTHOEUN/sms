package com.thoeun.sms.controller;

import com.thoeun.sms.dto.ApiResponse;
import com.thoeun.sms.dto.AttendanceDTO;
import com.thoeun.sms.models.Attendance;
import com.thoeun.sms.services.attendance.IAttendanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendances")
public class AttendanceController {

    private final IAttendanceService attendanceService;

    public AttendanceController(IAttendanceService attendanceService) {
        this.attendanceService = attendanceService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Attendance>>> getAllAttendances() {
        List<Attendance> attendances = attendanceService.getAllAttendances();
        return ResponseEntity.ok(ApiResponse.success(attendances));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Attendance>> getAttendanceById(@PathVariable Long id) {
        return attendanceService.getAttendanceById(id)
                .map(attendance -> ResponseEntity.ok(ApiResponse.success(attendance)))
                .orElseGet(() -> ResponseEntity.status(404)
                        .body(ApiResponse.notFound("Attendance with ID " + id + " not found")));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Attendance>> createAttendance(@RequestBody AttendanceDTO attendanceDTO) {
        try {
            Attendance createdAttendance = attendanceService.createAttendance(attendanceDTO);
            return ResponseEntity.status(201).body(ApiResponse.created(createdAttendance));
        } catch (RuntimeException e) {
            return ResponseEntity.status(400)
                    .body(ApiResponse.error(400, "Failed to create attendance: " + e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Attendance>> updateAttendance(@PathVariable Long id, @RequestBody AttendanceDTO attendanceDTO) {
        return attendanceService.updateAttendance(id, attendanceDTO)
                .map(updatedAttendance -> ResponseEntity.ok(ApiResponse.success(updatedAttendance)))
                .orElseGet(() -> ResponseEntity.status(404)
                        .body(ApiResponse.notFound("Attendance with ID " + id + " not found")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteAttendance(@PathVariable Long id) {
        if (attendanceService.deleteAttendance(id)) {
            return ResponseEntity.status(204).body(ApiResponse.noContent());
        } else {
            return ResponseEntity.status(404)
                    .body(ApiResponse.notFound("Attendance with ID " + id + " not found"));
        }
    }
}