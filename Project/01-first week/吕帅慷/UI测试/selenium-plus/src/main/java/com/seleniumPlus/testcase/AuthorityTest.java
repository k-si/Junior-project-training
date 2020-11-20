package com.seleniumPlus.testcase;

import com.seleniumPlus.core.BaseTest;
import com.seleniumPlus.dataprovider.NSDataProvider;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * 权限管理模块，分三部分
 * - 管理员
 * - 角色
 * - 权限资源
 */
public class AuthorityTest extends BaseTest {

    /**
     * 登录页url
     */
    private static String LOGIN_URL = "http://localhost:9999/index.php?controller=admin&action=index";

    @BeforeClass
    public void openModule() {
        engine.open(LOGIN_URL);
        engine.type("name=admin_name", "admin");
        engine.type("name=password", "3.1415926");
        engine.type("name=captcha", "abc");
        engine.click("xpath=//button[@type='submit']");
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[8]");
    }

    /**
     * 根据excel信息添加管理员
     */
    @Test(dataProvider = "excel", dataProviderClass = NSDataProvider.class)
    public void testAddAdmin(String name, String pwd, String repwd, String type, String email) {
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[8]/ul/li");
        engine.click("partLink=添加管理员");
        // 单纯用name无法定位用户名输入框
        engine.clearAndType("xpath=//tbody/tr/td/input", name);
        engine.clearAndType("name=password", pwd);
        engine.clearAndType("name=repassword", repwd);
        engine.selectByVisibleText("name=role_id", type);
        engine.clearAndType("name=email", email);
        engine.click("xpath=//button[@type='submit']");
        Assert.assertTrue(engine.isDisplayed("xpath=//td[text()='" + name + "']"));
    }
}
