package com.seleniumPlus.testcase;

import com.seleniumPlus.core.BaseTest;
import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestIWebShop extends BaseTest {
    private String url = "http://www.iwebshop.com:9999/";
    //登录方法
    public void login(){
        engine.open(url);
        engine.click("link=登录");
        engine.type("name=login_info","admin");
        engine.type("name=password","123456");
        engine.click("xpath=//input[@value='登录']");
    }

    //根据名字加入购物车方法
    public void addCart(String name){
        engine.click("xpath=//span[contains(text(),'首 页')]");
        engine.click("xpath=//h3[contains(text(),'"+name+"')]");
        engine.click("xpath=//span[contains(text(),'加入购物车')]");
    }

    //加入收藏夹
    public void addFavorites(String name){
        engine.click("xpath=//span[contains(text(),'首 页')]");
        engine.click("xpath=//h3[contains(text(),'"+name+"')]");
        engine.click("class=favorite");
    }

    //清空购物车
    public void emptyCart(){
        engine.click("xpath=//span[contains(text(),'清空购物车')]");
        engine.click("xpath=//button[contains(text(),'确定')]");
    }

    //测试不登录点开购物车
    @Test
    public void test1(){
        engine.open(url);
        engine.click("class=go_cart");
        Boolean flag = engine.isDisplayed("class=clear_cart_btn");
        Assert.assertFalse(flag);
    }

    //登陆之后点击购物车
    @Test
    public void test2(){
        login();
        engine.click("class=go_cart");
        Boolean flag = engine.isDisplayed("class=clear_cart_btn");
        Assert.assertTrue(flag);
    }

    @Test
    //测试加入购物车功能
    public void test11(){
        login();
        addCart("欧美进口真皮女包H型");
        engine.click("class=go_cart");
        Boolean flag = engine.isDisplayed("xpath=//a[contains(text(),'欧美进口真皮女包H型')]");
        Assert.assertTrue(flag);
    }

    //跳转结算页面
    @Test
    public void test3(){
        login();
        addCart("欧美进口真皮女包H型");
        engine.click("class=go_cart");
        engine.click("xpath=//span[contains(text(),'去结算')]");
        Boolean flag = engine.isDisplayed("xpath=//span[contains(text(),'确定无误，提交订单')]");
        Assert.assertTrue(flag);
    }

    //跳转继续购物页面
    @Test
    public void test4(){
        login();
        engine.click("class=go_cart");
        engine.click("xpath=//span[contains(text(),'继续购物')]");
        Boolean flag = engine.isDisplayed("xpath=//span[contains(text(),'继续购物')]");
        Assert.assertFalse(flag);
    }

    //测试商品详情链接
    @Test
    public void test5(){
        login();
        addCart("欧美进口真皮女包H型");
        engine.click("class=go_cart");
        engine.click("xpath=//a[contains(text(),'欧美进口真皮女包H型')]");
        Boolean flag = engine.isDisplayed("xpath=//span[contains(text(),'继续购物')]");
        Assert.assertFalse(flag);
    }

    //测试全部金额计算
    @Test
    public void test6(){
        login();
        addCart("欧美进口真皮女包H型");
        engine.click("class=go_cart");
        double price1 = Double.parseDouble(engine.getText("id=sum_87_0"));
        double price2 = Double.parseDouble(engine.getText("id=sum_price"));
        Assert.assertEquals(price1,price2);
    }

    //测试部分金额计算
    @Test
    public void test7(){
        login();
        addCart("欧美进口真皮女包H型");
        engine.pause(2000);
        addCart("藏族接待服");
        engine.pause(2000);
        addCart("真皮手提包包");
        engine.pause(2000);
        engine.click("class=go_cart");
        engine.click("value=39_0");
        double price1 = Double.parseDouble(engine.getText("id=sum_87_0"));
        double price2 = Double.parseDouble(engine.getText("id=sum_96_0"));
        double tprice = price1+price2;
        double price3 = Double.parseDouble(engine.getText("id=sum_price"));
        System.out.println(tprice);
        System.out.println(price3);
        Assert.assertEquals(tprice,price3);
    }

    //测试悬浮窗显示功能
    @Test
    public void test8(){
        login();
        engine.mouseToElement("class=go_cart");
        engine.pause(2000);
        Boolean flag = engine.isDisplayed("link=结算");
        Assert.assertTrue(flag);
    }

    //测试悬浮窗删除功能
    @Test
    public void test9(){
        login();
        addCart("欧美进口真皮女包H型");
        engine.mouseToElement("class=go_cart");
        engine.pause(2000);
        engine.mouseToElement("xpath=//i[contains(@onclick,'87')]");
        engine.click("xpath=//i[contains(@onclick,'87')]");
        engine.click("class=go_cart");
        Boolean flag = engine.isDisplayed("id=sum_87_0");
        Assert.assertFalse(flag);
    }

    //测试悬浮窗商品跳转
    @Test
    public void test10(){
        login();
        addCart("欧美进口真皮女包H型");
        engine.click("xpath=//span[contains(text(),'首 页')]");
        engine.mouseToElement("class=go_cart");
        engine.pause(2000);
        engine.mouseToElement("xpath=//i[contains(@onclick,'87')]");
        engine.mouseToElement("xpath=//a[contains(text(),'欧美进口真皮女包H型')]");
        engine.click("xpath=//a[contains(text(),'欧美进口真皮女包H型')]");
    }

    //测试一次性加入多个数量的购物车
    @Test
    public void test12(){
        login();
        engine.click("xpath=//span[contains(text(),'首 页')]");
        engine.click("xpath=//h3[contains(text(),'欧美进口真皮女包H型')]");
        engine.type("id=buyNums","5");
        engine.click("xpath=//span[contains(text(),'加入购物车')]");
        engine.click("class=go_cart");
        String num =getDriver().findElement(By.id("count_87_0")).getAttribute("value");
        Assert.assertEquals("15",num);
        emptyCart();
    }

    //测试多次点击加入购物车功能
    @Test
    public void test13(){
        login();
        engine.click("xpath=//span[contains(text(),'首 页')]");
        engine.click("xpath=//h3[contains(text(),'欧美进口真皮女包H型')]");
        for (int i = 0; i < 3; i++){
            engine.click("xpath=//span[contains(text(),'加入购物车')]");
        }
        engine.click("class=go_cart");
        String num =getDriver().findElement(By.id("count_87_0")).getAttribute("value");
        Assert.assertEquals(num,"3");
        emptyCart();
    }

    //测试购物车内删除功能
    @Test
    public void test14(){
        login();
        engine.click("class=go_cart");
        engine.click("link=删除");
        Assert.assertFalse(engine.isDisplayed("xpath=//a[contains(text(),'欧美进口真皮女包H型')]"));
    }

    //清空购物车功能
    @Test
    public void test15(){
        login();
        addCart("欧美进口真皮女包H型");
        addCart("藏族接待服");
        engine.click("class=go_cart");
        emptyCart();
        engine.click("xpath=//button[contains(text(),'确定')]");
        Assert.assertEquals(engine.getText("id=sum_price"),"0.00");

    }

    //测试购物车界面加号按钮
    @Test
    public void test16(){
        login();
        addCart("欧美进口真皮女包H型");
        engine.click("class=go_cart");
        engine.click("class=add");
        String num =getDriver().findElement(By.id("count_87_0")).getAttribute("value");
        Assert.assertEquals(num,2);
        emptyCart();
    }

    //测试购物车界面减号按钮
    @Test
    public void test17(){
        login();
        addCart("欧美进口真皮女包H型");
        engine.click("xpath=//span[contains(text(),'加入购物车')]");
        engine.click("class=go_cart");
        engine.click("class=reduce");
        String num =getDriver().findElement(By.id("count_87_0")).getAttribute("value");
        Assert.assertEquals(num,1);
    }

    //测试直接输入数量超过库存
    @Test
    public void test18(){
        login();
        addCart("欧美进口真皮女包H型");
        engine.click("class=go_cart");
        engine.type("id=count_87_0","1111111");
        engine.click("xpath=//span[contains(text(),'去结算')]");
        String num =getDriver().findElement(By.id("count_87_0")).getAttribute("value");
        engine.click("xpath=//button[contains(text(),'确定')]");
        emptyCart();
        Assert.assertEquals(num,"100");
    }

    //测试直接输入小于1
    @Test
    public void test19(){
        login();
        addCart("欧美进口真皮女包H型");
        engine.click("class=go_cart");
        engine.click("id=count_87_0");
        engine.tapType("Keys.BACK_SPACE");
        engine.type("id=count_87_0","0");
        engine.click("xpath=//span[contains(text(),'去结算')]");
        String num =getDriver().findElement(By.id("count_87_0")).getAttribute("value");
        engine.click("xpath=//button[contains(text(),'确定')]");
        emptyCart();
        Assert.assertEquals(num,"1");
    }

    //测试达到库存上限时点击加号
    @Test
    public void test20(){
        login();
        engine.click("xpath=//span[contains(text(),'首 页')]");
        engine.click("xpath=//h3[contains(text(),'欧美进口真皮女包H型')]");
        engine.type("id=buyNums","100");
        engine.click("xpath=//span[contains(text(),'加入购物车')]");
        engine.click("class=go_cart");
        engine.click("class=add");
        engine.click("xpath=//button[contains(text(),'确定')]");
        String num =getDriver().findElement(By.id("count_87_0")).getAttribute("value");
        emptyCart();
        Assert.assertEquals("100",num);
    }

    //测试数量为1时，点击减号
    @Test
    public void test21(){
        login();
        addCart("欧美进口真皮女包H型");
        engine.click("class=go_cart");
        engine.click("class=reduce");
        engine.click("xpath=//button[contains(text(),'确定')]");
        String num =getDriver().findElement(By.id("count_87_0")).getAttribute("value");
        Assert.assertEquals(num,1);
        emptyCart();
    }

    //测试合法输入
    @Test
    public void test22(){
        login();
        addCart("欧美进口真皮女包H型");
        engine.click("class=go_cart");
        engine.type("id=count_87_0","5");
        String num =getDriver().findElement(By.id("count_87_0")).getAttribute("value");
        Assert.assertEquals(num,"15");
        emptyCart();
    }

    //测试收藏夹跳转商品
    @Test
    public void test23(){
        login();
        engine.click("title=收藏夹");
        engine.click("xpath=//span[context(),'欧美进口真皮女包H型']");
        Boolean flag = engine.isDisplayed("xpath=//span[contains(text(),'加入购物车')]");
        Assert.assertTrue(flag);
    }

    //测试点击收藏收藏过的商品
    @Test
    public void test24(){
        login();
        addFavorites("欧美进口真皮女包H型");
        getDriver().findElement(By.className("favorite")).click();
        Boolean flag = getDriver().findElement(By.xpath("//div[contains(@class,'aui_state_noTitle')]")).isDisplayed();
        Assert.assertTrue(flag);
    }
}
