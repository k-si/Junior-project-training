package org.example.dao;

import org.example.domain.Student;

import java.util.List;

public interface StudentDao {

    //动态sql，使用java对象作为参数
    List<Student> selectStudentIf(Student student);

    List<Student> selectStudentWhere(Student student);
}
