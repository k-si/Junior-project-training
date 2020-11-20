package com.seleniumPlus.testcase;

import com.seleniumPlus.core.BaseTest;
import com.seleniumPlus.dataprovider.NSDataProvider;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestDataProvider extends BaseTest {

    private String url = "http://localhost:8083/index.php?controller=admin&action=index";
       protected void loginMember(){
        // 登录
        engine.open(url);
        engine.type("name=admin_name", "admin");
        engine.type("name=password", "password");
        engine.type("name=captcha", "abc");
        engine.click("xpath=//button[@type='submit']");

        // 进入会员界面
        engine.click("link=会员");
    }

    protected void loginAdvertisement(){
        // 登录
        engine.open(url);
        engine.type("name=admin_name", "admin");
        engine.type("name=password", "password");
        engine.type("name=captcha", "abc");
        engine.click("xpath=//button[@type='submit']");

        // 进入广告界面
        engine.click("link=工具");
    }

    //    1.添加会员
/*   @Test(dataProvider="excel", dataProviderClass = NSDataProvider.class)
    public void testAddMember(String name, String password, String recomfirm) {
        loginMember();
        //点击添加会员
        engine.click("partLink=添加会员");
        // 填入会员信息
        engine.type("name=username", name);
        engine.type("name=password", password);
        engine.type("name=repassword", recomfirm);

        // 点击确定
        engine.click("xpath=//button[@type='submit']");

        // 断言
        boolean displayed = engine.isDisplayed("xpath=//td[@title='admin']");
        Assert.assertTrue(displayed);
    }
//2.重复添加会员
    @Test(priority = 2,dataProvider="excel", dataProviderClass = NSDataProvider.class)
    public void testAddMemberAgain(String name, String password, String recomfirm) throws InterruptedException {
        loginMember();

        //点击添加会员
        engine.click("partLink=添加会员");
        // 填入会员信息
        engine.type("name=username", name);
        engine.type("name=password", password);
        engine.type("name=repassword", recomfirm);

        // 点击确定
        engine.click("xpath=//button[@type='submit']");

        // 断言
        String str="用户名重复";
        Assert.assertTrue(engine.isTextPresent(str));
    }
//3.成功充值余额为100元
    @Test
    public void testRecharge() throws InterruptedException {

        loginMember();

        //点击全选
        engine.click("partLink=全选");
        //点击预付款管理
        engine.click("partLink=预付款管理");
        //选择充值账户余额100
        WebElement element = driver.findElement(By.tagName("iframe"));
        engine.enterFrame(element);
        engine.click("class=form-control");
        engine.click("tags=option1");
        engine.type("xpath=//input[@type='text']", "100");
        engine.leaveFrame();
        driver.findElement(By.tagName("button")).click();

        //断言
        String str = "操作成功";
        Assert.assertTrue(engine.isTextPresent(str));
    }

    //4.充值余额为-100元
    @Test
    public void testNegativeRecharge() throws InterruptedException {

        loginMember();

        //点击全选
        engine.click("partLink=全选");
        //点击预付款管理
        engine.click("partLink=预付款管理");
        //选择充值账户余额100
        WebElement element = driver.findElement(By.tagName("iframe"));
        engine.enterFrame(element);
        engine.click("class=form-control");
        engine.click("tags=option1");
        engine.type("xpath=//input[@type='text']", "-100");
        engine.leaveFrame();
        driver.findElement(By.tagName("button")).click();

        //断言
        String str = "操作成功";
        Assert.assertTrue(engine.isTextPresent(str));
    }

    //5.成功提现
    @Test
    public void testWithdrawal() throws InterruptedException {

        loginMember();

        //点击全选
        engine.click("partLink=全选");
        //点击预付款管理
        engine.click("partLink=预付款管理");
        //选择充值账户余额100
        WebElement element = driver.findElement(By.tagName("iframe"));
        engine.enterFrame(element);
        engine.click("class=form-control");
        engine.click("tags=option2");
        engine.type("xpath=//input[@type='text']", "100");
        engine.leaveFrame();
        driver.findElement(By.tagName("button")).click();

        //断言
        String str = "操作成功";
        Assert.assertTrue(engine.isTextPresent(str));
    }

    //6.超出余额提现
    @Test
    public void testWithdrawal() throws InterruptedException {

        loginMember();

        //点击全选
        engine.click("partLink=全选");
        //点击预付款管理
        engine.click("partLink=预付款管理");
        //选择充值账户余额100
        WebElement element = driver.findElement(By.tagName("iframe"));
        engine.enterFrame(element);
        engine.click("class=form-control");
        engine.click("tags=option2");
        engine.type("xpath=//input[@type='text']", "100");
        engine.leaveFrame();
        driver.findElement(By.tagName("button")).click();

        //断言
        String str = "用户余额不足";
        Assert.assertTrue(engine.isTextPresent(str));
    }*/
    //7.批量删除成功
    @Test
    public void testDelete() throws InterruptedException {

        loginMember();

        //点击全选
        engine.click("partLink=全选");
        //点击批量删除
        engine.click("partLink=批量删除");
        engine.click("tag=button");
        //断言
        WebElement element = driver.findElement(By.tagName("tbody")).findElement(By.tagName("tr"));
        boolean b =true;
        if(element.isDisplayed()){
            b=false;
        }
        Assert.assertTrue(!b);
    }
}
