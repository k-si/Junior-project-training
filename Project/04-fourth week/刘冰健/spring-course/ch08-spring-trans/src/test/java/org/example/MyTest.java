package org.example;

import org.example.service.BuyGoodsService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void test01(){
        String config = "ApplicationContext.xml";
        ApplicationContext ctx = new ClassPathXmlApplicationContext(config);
        //从容器中获取service
        BuyGoodsService service = (BuyGoodsService) ctx.getBean("buyService");

        //调用方法
        service.buy(1005,10);
    }
}
