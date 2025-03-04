package com.thoeun.sms.services.staff;

import com.thoeun.sms.models.Staff;
import com.thoeun.sms.repositories.StaffRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StaffService implements IStaffService {

    private final StaffRepository staffRepository;

    public StaffService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }

    @Override
    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    @Override
    public Optional<Staff> getStaffById(Long id) {
        return staffRepository.findById(id);
    }

    @Override
    public Staff createStaff(Staff staff) {
        return staffRepository.save(staff);
    }

    @Override
    public Optional<Staff> updateStaff(Long id, Staff staffDetails) {
        return staffRepository.findById(id).map(staff -> {
            staff.setFirstName(staffDetails.getFirstName());
            staff.setLastName(staffDetails.getLastName());
            staff.setRole(staffDetails.getRole());
            staff.setDepartment(staffDetails.getDepartment());
            staff.setHireDate(staffDetails.getHireDate());
            staff.setEmail(staffDetails.getEmail());
            staff.setPhone(staffDetails.getPhone());
            return staffRepository.save(staff);
        });
    }

    @Override
    public boolean deleteStaff(Long id) {
        return staffRepository.findById(id).map(staff -> {
            staffRepository.delete(staff);
            return true;
        }).orElse(false);
    }
}