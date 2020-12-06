package com.ch;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ch.mapper.UserMapper;
import com.ch.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class MybatisPlusApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        //参数是一个wrapper,条件构建器
        //查询全部用户
        List<User> userList =  userMapper.selectList(null);
        userList.forEach(System.out::println);
    }

    //测试添加
    @Test
    public void testInsert(){
        User user = new User();
        user.setName("武仪人");
        user.setAge(20);
        user.setEmail("123456@qq.com");

        int result = userMapper.insert(user);
        System.out.println(result);
        System.out.println(user);
    }

    //测试更新
    @Test
    public void testUpdate(){
        User user = new User();
        //动态拼接sql
        user.setId(1334768941351866371L);
        user.setName("武仪人");
        user.setEmail("654321@qq.com");
        //注意：updateById参数是一个 对象
        int i  = userMapper.updateById(user);
        System.out.println(i);
    }

    //测试成功
    @Test
    public void testOptimisticLocker(){
        //查询用户信息
        User user = userMapper.selectById(1L);
        //修改用户信息
        user.setName("武仪人");
        user.setEmail("147258@qq.com");
        //执行更新操作
        userMapper.updateById(user);
    }

    //测试乐观锁失败  多线程
    @Test
    public void testOptimisticLocker2(){
        User user1 = userMapper.selectById(1L);
        user1.setName("张政");
        user1.setEmail("147258@qq.com");

        User user2 = userMapper.selectById(1L);
        user2.setName("黄书园");
        user2.setEmail("147258@qq.com");
        userMapper.updateById(user2);

        userMapper.updateById(user1);
    }

    //查询操作
    @Test
    public void testSelectById(){
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }

    //批量查询
    @Test
    public void testSelectByBatchId(){
        List<User> userList = userMapper.selectBatchIds(Arrays.asList(1,2,3));
        userList.forEach(System.out::println);
    }

    //条件查询 map
    @Test
    public void testSelectByMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","黄书园");
        map.put("id",1L);

        List<User> userList = userMapper.selectByMap(map);
        userList.forEach(System.out::println);
    }

    //测试分页查询
    @Test
    public void testPage(){
        //参数1：当前页  参数2：页面大小
        Page<User> page = new Page<>(1,5);
        userMapper.selectPage(page,null);

        page.getRecords().forEach(System.out::println);
        System.out.println(page.getTotal());
    }

    //测试删除
    @Test
    public void testDeleteById(){
        userMapper.deleteById(2L);
    }

    //批量删除
    @Test
    public void testDeleteByBatchId(){
        userMapper.deleteBatchIds(Arrays.asList(1,2,3));
    }

    //通过map删除
    public void testDeleteByMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("id",2L);

        userMapper.deleteByMap(map);
    }


}
