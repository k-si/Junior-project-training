package org.example;

import org.apache.ibatis.session.SqlSession;
import org.example.dao.StudentDao;
import org.example.domain.Student;
import org.example.utils.MyBatisUtils;
import org.junit.Test;

import java.util.List;

public class TestMyBatis {

    @Test
    public void TestSelectStudentIf(){

        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        Student student = new Student();
        student.setAge(18);
        student.setName("李四");
        List<Student> students = dao.selectStudentIf(student);
        for(Student stu : students){
            System.out.println(stu);
        }
    }

    @Test
    public void TestSelectStudentWhere(){

        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);

        Student student = new Student();
        student.setAge(18);
//        student.setName("李四");
        List<Student> students = dao.selectStudentWhere(student);
        for(Student stu : students){
            System.out.println(stu);
        }
    }
}
