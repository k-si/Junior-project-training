package org.example;

import com.sun.xml.internal.bind.v2.runtime.output.StAXExStreamWriterOutput;
import org.example.dao.StudentDao;
import org.example.dao.impl.StudentDaoImpl;
import org.example.domain.Student;
import org.junit.Test;

import java.util.List;

public class TestMyBatis {

    @Test
    public void testSelectStudents(){
        StudentDao dao = new StudentDaoImpl();
        List<Student> studentList = dao.selectStudents();
        for(Student stu:studentList){
            System.out.println(stu);
        }
    }

    @Test
    public void testInsert(){
        StudentDao dao = new StudentDaoImpl();
        Student student = new Student();
        student.setName("关羽");
        student.setId(1005);
        student.setEmail("guanyu@163.com");
        student.setAge(20);
        int num = dao.insertStudent(student);
        System.out.println("添加对象的数量："+num);
    }
}
