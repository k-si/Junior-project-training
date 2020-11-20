package com.seleniumPlus.testcase;

import com.seleniumPlus.core.BaseTest;
import com.seleniumPlus.dataprovider.NSDataProvider;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;



/**
 * 权限管理模块，分三部分。这里只负责角色
 */
public class RoleTest extends BaseTest {

    /**
     * 登录页url
     */
    private static String LOGIN_URL = "http://localhost:9999/index.php?controller=admin&action=index";

    /**
     * 在类测试之前先登录，打开管理员模块
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
     * 根据excel信息添加角色
     */
    @Test(dataProvider = "excel", dataProviderClass = NSDataProvider.class)
    public void testAddMultiRoles(String name) {
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[8]/ul/li[2]");
        engine.click("partLink=添加角色");
        engine.clearAndType("name=name", name);
        engine.click("id=checkbox_会员");
        engine.click("xpath=//button[@type='submit']");
        // 检测是否列表出现角色姓名
        Assert.assertTrue(engine.isDisplayed("xpath=//td[text()='" + name + "']"));
    }


    /**
     * 添加角色时不添加名称
     */
    @Test
    public void testAddRolesWithoutName() {
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[8]/ul/li[2]");
        engine.click("partLink=添加角色");
        engine.click("id=checkbox_会员");
        engine.click("xpath=//button[@type='submit']");
        String border = engine.getCss("name=name", "border-bottom-color");
        // 验证姓名输入框变红
        Assert.assertEquals(border, "rgb(221, 0, 0)");
    }

    /**
     * 删除单个角色
     */
    @Test
    public void testDeleteRole() {
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[8]/ul/li[2]");
        String roleName = engine.getAttribute("xpath=//div[@id='admin_right']/div[2]/form/table/tbody/tr[1]/td[2]", "innerHTML");
        engine.click("xpath=//div[@id='admin_right']/div[2]/form/table/tbody/tr[1]/td[1]/input");
        engine.click("xpath=//div[@id='admin_right']/div[2]/form/table/tbody/tr[1]/td[3]/a[2]");
        engine.click("xpath=//button[@type='button']");
        // 验证当前不存在原来的角色名称
        Assert.assertFalse(engine.getHtmlSource().contains(roleName));
    }

    /**
     * 选中多个角色，点击批量删除
     */
    @Test
    public void testDeleteRoles() {
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[8]/ul/li[2]");
        String roleName1 = engine.getAttribute("xpath=//div[@id='admin_right']/div[2]/form/table/tbody/tr[1]/td[2]", "innerHTML");
        String roleName2 = engine.getAttribute("xpath=//div[@id='admin_right']/div[2]/form/table/tbody/tr[2]/td[2]", "innerHTML");
        engine.click("xpath=//div[@id='admin_right']/div[2]/form/table/tbody/tr[1]/td[1]/input");
        engine.click("xpath=//div[@id='admin_right']/div[2]/form/table/tbody/tr[2]/td[1]/input");
        engine.click("partLink=批量删除");
        boolean displayed1 = engine.isDisplayed("xpath=//td[text()='" + roleName1 + "']");
        boolean displayed2 = engine.isDisplayed("xpath=//td[text()='" + roleName2 + "']");
        // 验证当前不存在原来的角色名称
        Assert.assertFalse(displayed1);
        Assert.assertFalse(displayed2);
    }

    /**
     * 编辑角色
     */
    @Test
    public void testUpdateRole() {
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[8]/ul/li[2]");
        engine.click("xpath=//div[@id='admin_right']/div[2]/form/table/tbody/tr[1]/td[3]/a[1]");
        engine.clearAndType("name=name", "update");
        engine.click("xpath=//button[@type='submit']");
        boolean displayed = engine.isDisplayed("xpath=//td[text()='update']");
        Assert.assertTrue(displayed);
    }

    /**
     * 回收站回收一个角色
     */
    @Test
    public void testRecycleRole() {
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[8]/ul/li[2]");
        engine.click("partLink=回收站");
        String name = engine.getAttribute("xpath=//div[@id='admin_right']/div[2]/form/table/tbody/tr[1]/td[2]", "innerHTML");
        engine.click("xpath=//div[@id='admin_right']/div[2]/form/table/tbody/tr[1]/td[3]/a[2]");
        engine.click("xpath=//button[@type='button']");
        boolean displayed = engine.isDisplayed("xpath=//td[text()='" + name + "']");
        Assert.assertTrue(displayed);
    }

    /**
     * 回收站批量回收多个角色
     */
    @Test
    public void testRecycleRoles() {
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[8]/ul/li[2]");
        engine.click("partLink=回收站");
        String roleName1 = engine.getAttribute("xpath=//div[@id='admin_right']/div[2]/form/table/tbody/tr[1]/td[2]", "innerHTML");
        String roleName2 = engine.getAttribute("xpath=//div[@id='admin_right']/div[2]/form/table/tbody/tr[2]/td[2]", "innerHTML");
        engine.click("xpath=//div[@id='admin_right']/div[2]/form/table/tbody/tr[1]/td[1]");
        engine.click("xpath=//div[@id='admin_right']/div[2]/form/table/tbody/tr[2]/td[1]");
        engine.click("partLink=恢复");
        engine.click("xpath=//button[@type='button']");
        boolean displayed1 = engine.isDisplayed("xpath=//td[text()='" + roleName1 + "']");
        boolean displayed2 = engine.isDisplayed("xpath=//td[text()='" + roleName2 + "']");
        Assert.assertTrue(displayed1);
        Assert.assertTrue(displayed2);
    }


}
