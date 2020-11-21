package com.seleniumPlus.testcase;

import com.seleniumPlus.core.BaseTest;
import com.seleniumPlus.dataprovider.NSDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestDataProvider extends BaseTest {

    private String url = "http://iwebshop:8889/index.php?controller=admin&action=index";

    @Test(dataProvider="excel", dataProviderClass = NSDataProvider.class)
    public void testAddMember(String name, String password, String recomfirm) {
        // 登录
        engine.open(url);
        engine.type("name=admin_name", "admin");
        engine.type("name=password", "3.1415926");
        engine.type("name=captcha", "abc");
        engine.click("xpath=//button[@type='submit']");

        // 进入会员界面
        engine.click("link=会员");
        engine.click("partLink=添加会员");

        // 填入会员信息
        engine.type("name=username", name);
        engine.type("name=password", password);
        engine.type("name=repassword", recomfirm);

        // 点击确定
        engine.click("xpath=//button[@type='submit']");

        // 断言
        boolean displayed = engine.isDisplayed("xpath=//td[@title='hello']");
        Assert.assertTrue(displayed);
    }

}
