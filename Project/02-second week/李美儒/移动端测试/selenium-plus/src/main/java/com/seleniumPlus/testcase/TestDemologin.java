package com.seleniumPlus.testcase;

import com.seleniumPlus.core.BaseTest;
import com.seleniumPlus.dataprovider.NSDataProvider;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;

public class TestDemologin extends BaseTest {

    String url="http://www.iwebshop.com:8888/";
    @BeforeClass
    public void login() throws InterruptedException {
        engine.open(url);
        engine.click("xpath=//a[text()='登录']");
        engine.type("xpath=//input[@name='login_info']","admin");
        engine.type("xpath=//input[@name='password']","123456");
        engine.click("xpath=//input[@type='submit']");
        engine.click("xpath=//li[@class='user_nav_index']");
        Thread.sleep(2000);
    }
    //获得网页面包屑内容
    public String getBreadCrumb(){
        String result=engine.getText("xpath=//*[@class='breadcrumb']");
        return result;
    }
    //登陆的前提下，随意点击一个商品，进去商品界面点击立即购买，看能否正常跳转
    //正常跳转到填写核对订单信息
    @Test(priority = 1)
    public void test_buy_proccess_login() throws InterruptedException {
        engine.slideDownToBottom();
        engine.click("xpath=//div[@class='index_card_container']/a[1]");
        engine.click("xpath=//div[@id='buyNowButton']");
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 » 填写核对订单信息");
        engine.goBack();
        engine.goBack();
    }

    //登陆的前提下，随意点击一个商品，进去商品界面点击网友讨论圈进行发表话题
    //可以正常发表话题
    //数据驱动
    @Test(dataProvider = "excel",dataProviderClass = NSDataProvider.class,priority = 2)
    public void test_commont_login(String content) throws InterruptedException {
        Thread.sleep(2000);
        engine.slideDownToBottom();
        engine.click("xpath=//div[@class='index_card_container']/a[1]");
        Thread.sleep(2000);
        engine.slideDownToBottom();
        engine.click("xpath=//label[contains(text(),'网友讨论圈')]");
        engine.click("xpath=//a[@name='discussButton']");
        engine.type("xpath=//textarea[@id='discussContent']",content);
        engine.type("xpath=//input[@class='input_text w100']","1234");
        engine.click("xpath=//input[@name='sendDiscussButton']");
        Thread.sleep(2000);
        engine.isTextPresent(content);
        engine.goBack();
    }

    //登陆前提下，点击拼团活动的更多正常跳转后，选择选择点击我的订单，看能否跳转成功
    //成功跳转到我的订单界面
    @Test(priority = 3)
    public void test_click_more_order() throws InterruptedException {
        engine.slideDownToBottom();
        engine.click("xpath=//*[@class='home_floor assemble']/header/a");
        engine.isTextPresent("当前没有可以参加的拼团活动");
        engine.click("xpath=//a[text()='我的订单']");
        System.out.print(getBreadCrumb());
        Assert.assertEquals(getBreadCrumb(),"首页 \\ 我的账户");
        engine.goBack();
    }

    //登录前提下，点击拼团活动的更多正常跳转后，选择选择点击我的购物车，看能否跳转成功
    //跳转到购物车界面（如果有商品那么存在去结算按钮，如果没有则与未登录进行跳转页面是一样的，所以存在问题）
    @Test(priority = 4)
    public void test_click_more_cart_unlogin() throws InterruptedException {
        engine.slideDownToBottom();
        engine.click("xpath=//*[@class='home_floor assemble']/header/a");
        engine.isTextPresent("当前没有可以参加的拼团活动");
        engine.click("xpath=//a[text()='我的购物车']");
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 » 购物车");
        engine.click("xpath=//div[@class='go_back_btn']");
        engine.isTextPresent("当前没有可以参加的拼团活动");
        engine.goBack();
    }

    //登陆的前提下，随意点击一个商品，进入商品界面，点击收藏商品
    //提示收藏成功，如果收藏夹中存在则提示您已收藏过此件商品
    @Test(priority = 5)
    public void test_click_favorite_unlogin() throws InterruptedException {
        engine.slideDownToBottom();
        engine.click("xpath=//div[@class='index_card_container']/a[1]");
        Thread.sleep(2000);
        engine.click("xpath=//span[@class='favorite']");
        engine.isTextPresent("收藏成功");
        engine.goBack();
        engine.goBack();
    }

}
