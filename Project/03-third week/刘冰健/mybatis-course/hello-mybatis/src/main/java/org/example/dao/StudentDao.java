package org.example.dao;

import org.example.domain.Student;

import java.util.List;

//接口操作student表
public interface StudentDao {

    //查询student表中所有数据
    public List<Student> selectStudents();

    //插入方法
    //参数：student表示要插入到数据库的数据
    //返回值：表示执行insert操作后影响数据库的行数
    public int insertStudent(Student student);
}
