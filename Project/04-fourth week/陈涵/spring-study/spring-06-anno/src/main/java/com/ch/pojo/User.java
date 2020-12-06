package com.ch.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//等价于<bean id = "user" class="com.ch.pojo.User">

@Component
@Scope("singleton")
public class User {

    //相当于<property name="name" value="秦疆">
    @Value("秦疆")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
