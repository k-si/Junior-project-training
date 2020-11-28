package org.example.ba06;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component("myStudent")
public class Student {

    /*
    * @value:简单类型的属性赋值
    *   属性：value是String类型的，表示简单类型的属性值
    *   位置：1.在属性定义的上面，无需set方法，推荐使用
    *         2.在set方法的上面
    * */
    @Value(value = "张飞")
    private String name;
    @Value(value = "22")
    private Integer age;

    /*
    * 引用类型
    * @Resource:来自jdk中的住建局，spring框架提供了对这个注解的功能支持，可以使用他给引用类型赋值
    *           使用的也是自动注入原理，支持byName，byType，默认是byName
    *
    * 位置：1.在属性定义的上面，无需set方法，推荐使用
    *       2.在set方法上面
    *  默认是byName：先使用byName自动注入，如果byName赋值失败，再使用byType
    *
    * @Resource只使用byName方式，需要增加一个属性name
    * */
    @Resource
    private School school;

    public Student(){
        System.out.println("===========无参构造方法=====");
    }

//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }


    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", school=" + school +
                '}';
    }
}
