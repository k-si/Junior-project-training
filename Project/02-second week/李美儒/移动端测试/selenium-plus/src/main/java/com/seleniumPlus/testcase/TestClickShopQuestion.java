package com.seleniumPlus.testcase;

import com.seleniumPlus.core.BaseTest;
import com.seleniumPlus.dataprovider.NSDataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
/**
 *用户在不登陆的前提下，就可以进行购买前咨询操作，应该是登录才可以进行购买前咨询
 */
public class TestClickShopQuestion extends BaseTest {
    String url="http://www.iwebshop.com:8888/";
    //获得网页面包屑内容
    public String getBreadCrumb(){
        String result=engine.getText("xpath=//*[@class='breadcrumb']");
        return result;
    }
    @BeforeClass
    public void openUrl() throws InterruptedException {
        engine.open(url);
        Thread.sleep(2000);
    }

    //随意点击一个商品，进入商品界面，点击购买前咨询进行发表咨询
    //选择商品咨询类型为商品咨询，输入咨询内容，输入验证码，3个都有才能成功发表咨询
    @Test(dataProvider = "excel",dataProviderClass = NSDataProvider.class,priority = 1)
    public void test_click_question_login1(String question){
//        engine.slideDownToBottom();
        engine.click("xpath=//div[@class='index_card_container']/a[1]");
        engine.slideDownToBottom();
        engine.click("xpath=//label[contains(text(),'购买前咨询')]");
        engine.click("xpath=//a[text()='我要咨询']");
        engine.click("xpath=//section[@class='user_form']/form/dl[1]/dd/label[1]/input");
        engine.type("xpath=//textarea[@name='question']",question);
        engine.type("xpath=//input[@name='captcha']","aaaaa");
        engine.click("xpath=//input[@type='submit']");
        engine.isTextPresent("操作成功！");
        engine.goBack();
        engine.goBack();
    }

    //随意点击一个商品，进入商品界面，点击购买前咨询进行发表咨询
    //选择商品咨询类型为库存配送，输入咨询内容，输入验证码，3个都有才能成功发表咨询
    @Test(dataProvider = "excel",dataProviderClass = NSDataProvider.class,priority = 2)
    public void test_click_question_login2(String question){
//        engine.slideDownToBottom();
        engine.click("xpath=//div[@class='index_card_container']/a[1]");
        engine.click("xpath=//label[contains(text(),'购买前咨询')]");
        engine.click("xpath=//a[text()='我要咨询']");
        engine.click("xpath=//section[@class='user_form']/form/dl[1]/dd/label[2]/input");
        engine.type("xpath=//textarea[@name='question']",question);
        engine.type("xpath=//input[@name='captcha']","aaaaa");
        engine.click("xpath=//input[@type='submit']");
        engine.isTextPresent("操作成功！");
        engine.goBack();
        engine.goBack();
    }

    //随意点击一个商品，进入商品界面，点击购买前咨询进行发表咨询
    //选择商品咨询类型为支付问题，输入咨询内容，输入验证码，3个都有才能成功发表咨询
    @Test(dataProvider = "excel",dataProviderClass = NSDataProvider.class,priority = 3)
    public void test_click_question_login3(String question){
//        engine.slideDownToBottom();
        engine.click("xpath=//div[@class='index_card_container']/a[1]");
        engine.click("xpath=//label[contains(text(),'购买前咨询')]");
        engine.click("xpath=//a[text()='我要咨询']");
        engine.click("xpath=//section[@class='user_form']/form/dl[1]/dd/label[3]/input");
        engine.type("xpath=//textarea[@name='question']",question);
        engine.type("xpath=//input[@name='captcha']","aaaaa");
        engine.click("xpath=//input[@type='submit']");
        engine.isTextPresent("操作成功！");
        engine.goBack();
        engine.goBack();
    }

    //随意点击一个商品，进入商品界面，点击购买前咨询进行发表咨询
    //选择商品咨询类型为发送及保修，输入咨询内容，输入验证码，3个都有才能成功发表咨询
    @Test(dataProvider = "excel",dataProviderClass = NSDataProvider.class,priority = 4)
    public void test_click_question_login4(String question){
//        engine.slideDownToBottom();
        engine.click("xpath=//div[@class='index_card_container']/a[1]");
        engine.click("xpath=//label[contains(text(),'购买前咨询')]");
        engine.click("xpath=//a[text()='我要咨询']");
        engine.click("xpath=//section[@class='user_form']/form/dl[1]/dd/label[4]/input");
        engine.type("xpath=//textarea[@name='question']",question);
        engine.type("xpath=//input[@name='captcha']","aaaaa");
        engine.click("xpath=//input[@type='submit']");
        engine.isTextPresent("操作成功！");
        engine.goBack();
        engine.goBack();
    }

    //随意点击一个商品，进入商品界面，点击购买前咨询进行发表咨询
    //选择商品咨询类型为促销及商品，输入咨询内容，输入验证码，3个都有才能成功发表咨询
    @Test(dataProvider = "excel",dataProviderClass = NSDataProvider.class,priority = 5)
    public void test_click_question_login5(String question){
//        engine.slideDownToBottom();
        engine.click("xpath=//div[@class='index_card_container']/a[1]");
        engine.click("xpath=//label[contains(text(),'购买前咨询')]");
        engine.click("xpath=//a[text()='我要咨询']");
        engine.click("xpath=//section[@class='user_form']/form/dl[1]/dd/label[5]/input");
        engine.type("xpath=//textarea[@name='question']",question);
        engine.type("xpath=//input[@name='captcha']","aaaaa");
        engine.click("xpath=//input[@type='submit']");
        engine.isTextPresent("操作成功！");
        engine.goBack();
        engine.goBack();
    }

}
