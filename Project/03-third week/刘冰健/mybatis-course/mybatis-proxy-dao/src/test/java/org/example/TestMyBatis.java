package org.example;

import org.apache.ibatis.session.SqlSession;
import org.example.dao.StudentDao;
import org.example.domain.Student;
import org.example.utils.MyBatisUtils;
import org.junit.Test;

import java.util.List;

public class TestMyBatis {

    @Test
    public void testSelectStudents(){
        /*
        * 使用mybatis的动态代理机制，使用sqlSession.getMapper(dao接口)
        * getMapper能获取dao接口对于实现类对象。
        */
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        //调用dao的方法，执行数据库的操作
        List<Student> students = dao.selectStudents();
        for(Student stu:students){
            System.out.println("学生="+stu);
        }
    }

    @Test
    public int testInsertStudent(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        Student stu = new Student(1007,"王五","wangwu@qq.com",20);
        int num = dao.insertStudent(stu);
        return num;
    }
}
