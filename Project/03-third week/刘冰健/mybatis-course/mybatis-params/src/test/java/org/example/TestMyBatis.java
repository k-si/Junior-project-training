package org.example;

import org.apache.ibatis.session.SqlSession;
import org.example.dao.StudentDao;
import org.example.domain.Student;
import org.example.utils.MyBatisUtils;
import org.example.vo.QueryParam;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestMyBatis {

    @Test
    public void testSelectStudentById(){
        /*
        * 使用mybatis的动态代理机制，使用sqlSession.getMapper(dao接口)
        * getMapper能获取dao接口对于实现类对象。
        *
        * 使用#{}之后，mybatis执行的是使用jdbc中的PrepareStatement对象
        * 由mybatis执行下面的代码：
        * 1.mybatis创建Connection，PrepareStatement对象
        *       String sql = "select id,name,email,age from student where id=?";
        *       PrepareStatement pst = conn.prepareStatement(sql);
        *       pst.setInt(1,1001);
        * 2.执行sql封装为
        *   ResultSet rs = ps.executeQuery();
        *   while(rs.next){
        *       //从数据库取表的一行数据，存到一个java对象属性中
        *       Student student = new Student();
        *       student.setId(rs.getInt("id"));
        *       student.setName(rs.getName("name"));
        *       student.setEmail(rs.getInt("email"));
        *       student.setAge(rs.getInt("age"));
        *   }
        *   return student;
        */
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        Student student = dao.selectStudentById(1001);
        System.out.println(student);
    }
    @Test
    public void testSelectMultiParam(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        List<Student> students = dao.selectMultiParam("张三",20);
        for (Student stu : students){
            System.out.println(stu);
        }
    }

    @Test
    public void testSelectMultiObject(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        QueryParam queryParam = new QueryParam();
        queryParam.setParamAge(20);
        queryParam.setParamName("张三");
        List<Student> students = dao.selectMultiObject(queryParam);
        for (Student stu : students){
            System.out.println(stu);
        }
    }

    @Test
    public void testSelectMultiPosition(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        List<Student> students = dao.selectMultiPosition("张三",20);
        for (Student stu : students){
            System.out.println(stu);
        }
    }

    @Test
    public void testSelectMultiByMap(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        StudentDao dao = sqlSession.getMapper(StudentDao.class);
        Map<String,Object> data = new HashMap<>();
        data.put("myname","张三");
        data.put("myage",20);
        List<Student> students = dao.selectMutiByMap(data);
        for (Student stu : students){
            System.out.println(stu);
        }
    }
}
