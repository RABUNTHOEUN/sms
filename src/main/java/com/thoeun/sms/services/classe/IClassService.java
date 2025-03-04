package com.thoeun.sms.services.classe;
import com.thoeun.sms.models.Class;
import java.util.List;
import java.util.Optional;

public interface IClassService {
    List<Class> getAllClasses();
    Optional<Class> getClassById(Long id);
    Class createClass(Class aClass);
    Optional<Class> updateClass(Long id, Class classDetails);
    boolean deleteClass(Long id);
}
