package org.example.service.impl;

import org.example.dao.StudentDao;
import org.example.domain.Student;
import org.example.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    //引用类型
    private StudentDao studentDao;
    //使用set注入，赋值


    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public int addStudent(Student student) {
        int nums = studentDao.insertStudent(student);
        return nums;
    }

    @Override
    public List<Student> queryStudents() {
        List<Student> students = studentDao.selectStudents();
        return students;
    }
}
