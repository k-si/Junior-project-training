package org.example;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.domain.Student;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyApp {
    public static void main(String[] args) throws IOException {
        //访问mybatis读取student数据
        //1.定义mybatis主配置文件的名称,从类路径的根开始(target/classes)
        String config = "mybatis.xml";
        //2.读取这个config表示的文件
        //Resources类，读取主配置文件，转变为InputStream类型
        InputStream in = Resources.getResourceAsStream(config);
        //3.创建了sqlSessioFactoryBuilder对象
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //4.创建SqlSessionFactory对象，重量级对象，程序创建一个对象耗时比较长，使用资源比较多，在整个项目中有一个就够用了
        //作用：获取sqlSession对象
        SqlSessionFactory factory = builder.build(in);
        //5.【重要】获取SqlSession对象，从SqlSessionFactory中获取SqlSession
        //openSession()无参数，获取非自动提交事务的sqlSession对象
        //openSession(true):获取自动提交事务的sqlSession对象

        /*SqlSession不是线程安全的，需要在方法内部使用，在执行sql语句之前，使用openSession()获取SqlSession对象，在执行sql之后
        * 需要close()保证线程安全*/

        SqlSession sqlSession = factory.openSession();
        //6.【重要】指定要执行的sql语句的标识。sql映射文件中的namespace+"."+标签的id值
        String sqlId = "org.example.dao.StudentDao" + "." + "selectStudents";
        //7.执行sql语句，通过sqlId找到语句
        List<Student> studentList = sqlSession.selectList(sqlId);
        //8.输出结果
        studentList.forEach(stu -> System.out.println(stu));
        //9.关闭SqlSession对象
        sqlSession.close();
    }
}
