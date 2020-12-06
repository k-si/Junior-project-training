package com.ch.demo4;

import com.ch.demo2.UserService;
import com.ch.demo2.UserServiceImpl;

public class Client {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        ProxyInvocationHandler pih = new ProxyInvocationHandler();

        //设置要代理的对象
        pih.setTarget(userService);

        //动态生成代理类
        UserService proxy = (UserService) pih.getProxy();
        proxy.add();
    }
}
