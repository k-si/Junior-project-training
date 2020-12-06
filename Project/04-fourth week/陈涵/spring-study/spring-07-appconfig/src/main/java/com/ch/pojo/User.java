package com.ch.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//这个注释说明这个类被spring接管，注册到容器中
@Component
public class User {

    @Value("秦疆")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
