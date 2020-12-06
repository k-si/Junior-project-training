package com.ch;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ch.mapper.UserMapper;
import com.ch.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WrapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper
                .isNotNull("name")
                .isNotNull("email")
                .ge("age",12);
        userMapper.selectList(wrapper).forEach(System.out::println);
    }

    @Test
    public void test2(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper
                .eq("name","黄书园");
        User user = userMapper.selectOne(wrapper);
        System.out.println(user);
    }
}
