package com.seleniumPlus.testcase;

import com.seleniumPlus.core.BaseTest;
import com.seleniumPlus.core.WebDriverEngine;

public class Login extends BaseTest {
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
}
