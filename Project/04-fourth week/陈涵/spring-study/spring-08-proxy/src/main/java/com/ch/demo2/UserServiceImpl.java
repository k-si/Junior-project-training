package com.ch.demo2;

public class UserServiceImpl implements UserService{
    public void add(){
        System.out.println("增加一个数据");
    }
    public void delete(){
        System.out.println("删除了一个数据");
    }
    public void update(){
        System.out.println("修改了一个数据");
    }
    public void query(){
        System.out.println("查找了一个数据");
    }
}
