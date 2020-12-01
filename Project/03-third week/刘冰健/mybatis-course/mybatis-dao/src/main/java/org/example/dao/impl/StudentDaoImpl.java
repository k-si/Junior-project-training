package org.example.dao.impl;

import org.apache.ibatis.session.SqlSession;
import org.example.dao.StudentDao;
import org.example.domain.Student;
import org.example.utils.MyBatisUtils;

import java.util.List;

public class StudentDaoImpl implements StudentDao {


    @Override
    public List<Student> selectStudents() {
        //获取Session对象
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        String sqlId = "org.example.dao.StudentDao.selectStudents";
        //执行sql语句
        List<Student> students = sqlSession.selectList(sqlId);
        sqlSession.close();
        return students;
    }

    @Override
    public int insertStudent(Student student) {
        //获取Session对象
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        String sqlId = "org.example.dao.StudentDao.insertStudent";
        //执行sql语句
        int num = sqlSession.insert(sqlId,student);
        sqlSession.commit();
        sqlSession.close();
        return num;
    }
}
