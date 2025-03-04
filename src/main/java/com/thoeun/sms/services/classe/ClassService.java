package com.thoeun.sms.services.classe;

import com.thoeun.sms.models.Class;
import com.thoeun.sms.repositories.ClassRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassService implements IClassService {

    private final ClassRepository classRepository;

    public ClassService(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    @Override
    public List<Class> getAllClasses() {
        return classRepository.findAll();
    }

    @Override
    public Optional<Class> getClassById(Long id) {
        return classRepository.findById(id);
    }

    @Override
    public Class createClass(Class aClass) {
        return classRepository.save(aClass);
    }

    @Override
    public Optional<Class> updateClass(Long id, Class classDetails) {
        return classRepository.findById(id).map(aClass -> {
            aClass.setCourse(classDetails.getCourse());
            aClass.setTeacher(classDetails.getTeacher());
            aClass.setRoomNumber(classDetails.getRoomNumber());
            aClass.setScheduleTime(classDetails.getScheduleTime());
            aClass.setScheduleDays(classDetails.getScheduleDays());
            return classRepository.save(aClass);
        });
    }

    @Override
    public boolean deleteClass(Long id) {
        return classRepository.findById(id).map(aClass -> {
            classRepository.delete(aClass);
            return true;
        }).orElse(false);
    }
}