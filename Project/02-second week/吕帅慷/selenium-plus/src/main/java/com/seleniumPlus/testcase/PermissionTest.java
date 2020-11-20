package com.seleniumPlus.testcase;

import com.seleniumPlus.core.BaseTest;
import com.seleniumPlus.dataprovider.NSDataProvider;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PermissionTest extends BaseTest {

    /**
     * 登录页url
     */
    private static String LOGIN_URL = "http://localhost:9999/index.php?controller=admin&action=index";

    /**
     * 在类测试之前先登录，打开权限模块
     */
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
     * 根据excel添加权限
     *
     * @param name   权限名称
     * @param first  权限码的第一个参数
     * @param second 权限码的第二个参数
     */
    @Test(dataProvider = "excel", dataProviderClass = NSDataProvider.class)
    public void testAddPermission(String name, String first, String second) {
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[8]/ul/li[3]");
        engine.click("partLink=添加权限");
        engine.clearAndType("name=name", name);
        engine.click("xpath=//tbody[@id='rightBox']/tr[1]/td[2]/a[1]");
        engine.selectByVisibleText("name=ctrl", first);
        engine.selectByVisibleText("name=action", second);
        engine.click("xpath=//button[text()='添加']");
        engine.click("xpath=//button[@type='submit']");
        // 验证列表中有没有新添加的权限
        Assert.assertTrue(engine.isDisplayed("xpath=//td[text()='" + name + "']"));
    }

    /**
     * 添加权限时不输入名称
     */
    @Test
    public void testAddPermissionWithoutName() {
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[8]/ul/li[3]");
        engine.click("partLink=添加权限");
        engine.clearAndType("xpath=//tbody[@id='rightBox']/tr[1]/td[1]/input", "aba aba");
        engine.click("xpath=//button[@type='submit']");
        String border = engine.getCss("name=name", "border-bottom-color");
        // 验证权限名称输入框有没有变红
        Assert.assertEquals(border, "rgb(221, 0, 0)");
    }

    /**
     * 添加权限的时候不添加权限码
     */
    @Test
    public void testAddPermissionWithoutAccessCode() {
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[8]/ul/li[3]");
        engine.click("partLink=添加权限");
        engine.clearAndType("name=name", "abc");
        engine.click("xpath=//button[@type='submit']");
        String border = engine.getCss("xpath=//tbody[@id='rightBox']/tr[1]/td[1]/input", "border-top-color");
        // 验证权限名称输入框有没有变红
        Assert.assertEquals(border, "rgb(221, 0, 0)");
    }

    /**
     * 删除一条权限
     */
    @Test
    public void testDeletePermission() {
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[8]/ul/li[3]");
        String name = engine.getAttribute("xpath=//div[@id='admin_right']/div[2]/form/table/tbody/tr[1]/td[2]", "innerHTML");
        engine.click("xpath=//tbody/tr/td/input");
        engine.click("xpath=//tbody/tr/td[4]/a[2]");
        engine.click("xpath=//button[@type='button']");
        // 验证列表中是否还有权限名
        Assert.assertTrue(engine.isDisplayed("xpath=//td[text()='" + name + "']"));
    }

    /**
     * 选中多条进行批量删除
     */
    @Test
    public void testDeleteByBatch() {
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[8]/ul/li[3]");
        String name1 = engine.getAttribute("xpath=//div[@id='admin_right']/div[2]/form/table/tbody/tr[1]/td[2]", "innerHTML");
        String name2 = engine.getAttribute("xpath=//div[@id='admin_right']/div[2]/form/table/tbody/tr[2]/td[2]", "innerHTML");
        engine.click("xpath=//tbody/tr/td/input");
        engine.click("xpath=//tbody/tr[2]/td/input");
        engine.click("partLink=批量删除");
        engine.click("xpath=//button[@type='button']");
        // 验证列表中是否还有权限名
        Assert.assertTrue(engine.isDisplayed("xpath=//td[text()='" + name1 + "']"));
        Assert.assertTrue(engine.isDisplayed("xpath=//td[text()='" + name2 + "']"));
    }

    /**
     * 编辑权限
     */
    @Test
    public void testUpdatePermission() {
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[8]/ul/li[3]");
        engine.click("xpath=//tbody/tr/td[4]/a[1]");
        engine.clearAndType("name=name", "update");
        engine.click("xpath=//button[@type='submit']");
        // 验证列表中是否还有权限名
        Assert.assertTrue(engine.isDisplayed("xpath=//td[text()='update']"));
    }

    /**
     * 还原一个权限
     */
    @Test
    public void testRestorePermission() {
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[8]/ul/li[3]");
        engine.click("partLink=回收站");
        String name = engine.getAttribute("xpath=//div[@id='admin_right']/div[2]/form/table/tbody/tr[1]/td[2]", "innerHTML");
        engine.click("xpath=//tbody/tr/td[4]/a[2]");
        engine.click("xpath=//button[@type='button']");
        // 验证列表中是否还有权限名
        Assert.assertTrue(engine.isDisplayed("xpath=//td[text()='" + name + "']"));
    }

    /**
     * 回收站回收多个权限
     */
    @Test
    public void testRestorePermissions() {
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[8]/ul/li[3]");
        engine.click("partLink=回收站");
        String name1 = engine.getAttribute("xpath=//div[@id='admin_right']/div[2]/form/table/tbody/tr[1]/td[2]", "innerHTML");
        String name2 = engine.getAttribute("xpath=//div[@id='admin_right']/div[2]/form/table/tbody/tr[2]/td[2]", "innerHTML");
        engine.click("xpath=//div[@id='admin_right']/div[2]/form/table/tbody/tr[1]/td[1]/input");
        engine.click("xpath=//div[@id='admin_right']/div[2]/form/table/tbody/tr[2]/td[1]/input");
        engine.click("partLink=批量删除");
        engine.click("xpath=//button[@type='button']");
        // 验证列表中是否还有权限名
        engine.isDisplayed("xpath=//td[text()='" + name1 + "']");
        engine.isDisplayed("xpath=//td[text()='" + name2 + "']");
    }
}
