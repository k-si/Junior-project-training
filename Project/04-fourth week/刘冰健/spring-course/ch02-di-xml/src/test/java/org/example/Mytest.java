package org.example;

import org.example.ba04.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Mytest {

    @Test
    public void test01(){
        String config = "ba03/applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        //从容器中获取student对象
        Student myStudent = (Student) ac.getBean("myStudent1");
        System.out.println(myStudent);
    }

    @Test
    public void test04(){
        String config = "ba04/applicationContext.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);
        //从容器中获取student对象
        Student myStudent = (Student) ac.getBean("myStudent");
        System.out.println(myStudent);
    }
}
