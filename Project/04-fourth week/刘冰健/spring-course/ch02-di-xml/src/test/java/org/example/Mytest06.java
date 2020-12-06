package org.example;

import org.example.ba06.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Mytest06 {

    @Test
    public void test01(){
        String config = "ba06/total.xml";
        ApplicationContext ac = new ClassPathXmlApplicationContext(config);

        Student myStudent = (Student)ac.getBean("myStudent");
        System.out.println("student对象="+myStudent);
    }
}
