package org.example;

import org.apache.ibatis.session.SqlSession;
import org.example.domain.Student;
import org.example.utils.MyBatisUtils;

import java.util.List;

public class MyApp1 {
    public static void main(String[] args) {
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        String sqlId = "org.example.dao.StudentDao" + "." + "selectStudents";
        //7.执行sql语句，通过sqlId找到语句
        List<Student> studentList = sqlSession.selectList(sqlId);
        //8.输出结果
        studentList.forEach(stu -> System.out.println(stu));
        //9.关闭SqlSession对象
        sqlSession.close();
    }
}
