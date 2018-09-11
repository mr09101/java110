package bitcamp.java110.cms.dao;

import java.util.ArrayList;
import java.util.List;

import bitcamp.java110.cms.annotation.Component;
import bitcamp.java110.cms.domain.Student;

public interface StudentDao {
    int insert(Student student);
    List<Student> findAll();
    Student findByEmail(String email);
    int delete(String email) ;
}
