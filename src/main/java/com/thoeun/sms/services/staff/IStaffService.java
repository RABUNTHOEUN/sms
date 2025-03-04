package com.thoeun.sms.services.staff;

import com.thoeun.sms.models.Staff;
import java.util.List;
import java.util.Optional;

public interface IStaffService {
    List<Staff> getAllStaff();
    Optional<Staff> getStaffById(Long id);
    Staff createStaff(Staff staff);
    Optional<Staff> updateStaff(Long id, Staff staffDetails);
    boolean deleteStaff(Long id);
}