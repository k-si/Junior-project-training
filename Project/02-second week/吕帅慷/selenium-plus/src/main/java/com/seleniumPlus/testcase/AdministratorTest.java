package com.seleniumPlus.testcase;

import com.seleniumPlus.core.BaseTest;
import com.seleniumPlus.dataprovider.NSDataProvider;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


/**
 * 权限管理模块，分三部分。这里只负责管理员
 */
public class AdministratorTest extends BaseTest {

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
     * 根据excel信息添加管理员
     */
    @Test(dataProvider = "excel_administrator", dataProviderClass = NSDataProvider.class)
    public void testAddMultiAdmin(String name, String pwd, String repwd, String type, String email) {
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[8]/ul/li");
        engine.click("partLink=添加管理员");
        // 单纯用name无法定位用户名输入框
        engine.clearAndType("xpath=//tbody/tr/td/input", name);
        engine.clearAndType("name=password", pwd);
        engine.clearAndType("name=repassword", repwd);
        engine.selectByVisibleText("name=role_id", type);
        engine.clearAndType("name=email", email);
        engine.click("xpath=//button[@type='submit']");
        // 验证列表出现添加的管理员名称
        Assert.assertTrue(engine.isDisplayed("xpath=//td[text()='" + name + "']"));
    }

    /**
     * 根据正确的规则添加管理员
     * 输入用户名abc_123；
     * 输入密码ab_cd_12_34；
     * 输入重复密码ab_cd_12_34；
     * 选择超级管理员；
     * 输入邮箱zhangsan@163.com
     */
    @Test
    public void testAddAdminSuccess() {
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[8]/ul/li");
        engine.click("partLink=添加管理员");
        engine.clearAndType("xpath=//tbody/tr/td/input", "abc_123");
        engine.clearAndType("name=password", "ab_cd_12_34");
        engine.clearAndType("name=repassword", "ab_cd_12_34");
        engine.selectByVisibleText("name=role_id", "超级管理员");
        engine.clearAndType("name=email", "zhangsan@163.com");
        engine.click("xpath=//button[@type='submit']");
        // 验证列表出现添加的管理员名称
        Assert.assertTrue(engine.isDisplayed("xpath=//td[text()='abc_123']"));
    }

    /**
     * 对用户名进行边界值测试，规定四个字符，输入三个字符
     * 输入用户名a_b；
     * 输入密码ab_cd_12_34；
     * 输入重复密码ab_cd_12_34；
     * 选择超级管理员；
     * 输入邮箱zhangsan@163.com
     */
    @Test
    public void testAddAdminByWrongName() {
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[8]/ul/li");
        engine.click("partLink=添加管理员");
        engine.clearAndType("xpath=//tbody/tr/td/input", "a_b");
        engine.clearAndType("name=password", "ab_cd_12_34");
        engine.clearAndType("name=repassword", "ab_cd_12_34");
        engine.selectByVisibleText("name=role_id", "超级管理员");
        engine.clearAndType("name=email", "zhangsan@163.com");
        engine.click("xpath=//button[@type='submit']");
        String border = engine.getCss("xpath=//tbody/tr/td/input", "border-bottom-color");
        // 验证输入框变红
        Assert.assertEquals(border, "rgb(221, 0, 0)");
    }

    /**
     * 对用户名进行边界值测试，规定4-32个字符，输入35个字符
     * 输入用户名11111111111111111111111111111111111；
     * 输入密码ab_cd_12_34；
     * 输入重复密码ab_cd_12_34；
     * 选择超级管理员；
     * 输入邮箱zhangsan@163.com
     */
    @Test
    public void testAddAdminByWrongName2() {
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[8]/ul/li");
        engine.click("partLink=添加管理员");
        engine.clearAndType("xpath=//tbody/tr/td/input", "11111111111111111111111111111111111");
        engine.clearAndType("name=password", "ab_cd_12_34");
        engine.clearAndType("name=repassword", "ab_cd_12_34");
        engine.selectByVisibleText("name=role_id", "超级管理员");
        engine.clearAndType("name=email", "zhangsan@163.com");
        engine.click("xpath=//button[@type='submit']");
        String border = engine.getCss("xpath=//tbody/tr/td/input", "border-bottom-color");
        // 验证输入框变红
        Assert.assertEquals(border, "rgb(221, 0, 0)");
    }

    /**
     * 用户名英文字符或下划线，输入特殊符号@.
     * 输入用户名abc@.456；
     * 输入密码ab_cd_12_34；
     * 输入重复密码ab_cd_12_34；
     * 选择超级管理员；
     * 输入邮箱zhangsan@163.com
     */
    @Test
    public void testAddAdminByWrongName3() {
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[8]/ul/li");
        engine.click("partLink=添加管理员");
        engine.clearAndType("xpath=//tbody/tr/td/input", "abc@.456");
        engine.clearAndType("name=password", "ab_cd_12_34");
        engine.clearAndType("name=repassword", "ab_cd_12_34");
        engine.selectByVisibleText("name=role_id", "超级管理员");
        engine.clearAndType("name=email", "zhangsan@163.com");
        engine.click("xpath=//button[@type='submit']");
        String border = engine.getCss("xpath=//tbody/tr/td/input", "border-bottom-color");
        // 验证输入框变红
        Assert.assertEquals(border, "rgb(221, 0, 0)");
    }

    /**
     * 密码至少六位，输入五位
     * 输入用户名abc_123；
     * 输入密码ab_12；
     * 输入重复密码ab_12；
     * 选择超级管理员；
     * 输入邮箱zhangsan@163.com
     */
    @Test
    public void testAddAdminByWrongPassword() {
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[8]/ul/li");
        engine.click("partLink=添加管理员");
        engine.clearAndType("xpath=//tbody/tr/td/input", "abc_123");
        engine.clearAndType("name=password", "ab_12");
        engine.clearAndType("name=repassword", "ab_12");
        engine.selectByVisibleText("name=role_id", "超级管理员");
        engine.clearAndType("name=email", "zhangsan@163.com");
        engine.click("xpath=//button[@type='submit']");
        String border = engine.getCss("name=password", "border-bottom-color");
        // 验证输入框变红
        Assert.assertEquals(border, "rgb(221, 0, 0)");
    }

    /**
     * 密码英文字母，数字或下划线，输入*
     * 输入用户名abc_123；
     * 输入密码ab_*12；
     * 输入重复密码ab_*12；
     * 选择超级管理员；
     * 输入邮箱zhangsan@163.com
     */
    @Test
    public void testAddAdminByWrongPassword2() {
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[8]/ul/li");
        engine.click("partLink=添加管理员");
        engine.clearAndType("xpath=//tbody/tr/td/input", "abc_123");
        engine.clearAndType("name=password", "ab_*12");
        engine.clearAndType("name=repassword", "ab_*12");
        engine.selectByVisibleText("name=role_id", "超级管理员");
        engine.clearAndType("name=email", "zhangsan@163.com");
        engine.click("xpath=//button[@type='submit']");
        String border = engine.getCss("name=password", "border-bottom-color");
        // 验证输入框变红
        Assert.assertEquals(border, "rgb(221, 0, 0)");
    }

    /**
     * 密码规定规定4-32个字符，输入32个字符
     * 输入用户名abc_123；
     * 输入密码aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa；
     * 输入重复密码aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa；
     * 选择超级管理员；
     * 输入邮箱zhangsan@163.com
     */
    @Test
    public void testAddAdminByWrongPassword3() {
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[8]/ul/li");
        engine.click("partLink=添加管理员");
        engine.clearAndType("xpath=//tbody/tr/td/input", "abc_123");
        engine.clearAndType("name=password", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        engine.clearAndType("name=repassword", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        engine.selectByVisibleText("name=role_id", "超级管理员");
        engine.clearAndType("name=email", "zhangsan@163.com");
        engine.click("xpath=//button[@type='submit']");
        String border = engine.getCss("name=password", "border-bottom-color");
        // 验证输入框变红
        Assert.assertEquals(border, "rgb(221, 0, 0)");
    }

    /**
     * 密码和重复密码不一致
     * 输入用户名abc_123；
     * 输入密码ab_cd_12_34；
     * 输入重复密码ab_cd_12_35；
     * 选择超级管理员；
     * 输入邮箱zhangsan@163.com
     */
    @Test
    public void testAddAdminByWrongRePassword() {
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[8]/ul/li");
        engine.click("partLink=添加管理员");
        engine.clearAndType("xpath=//tbody/tr/td/input", "abc_123");
        engine.clearAndType("name=password", "ab_cd_12_34");
        engine.clearAndType("name=repassword", "ab_cd_12_35");
        engine.selectByVisibleText("name=role_id", "超级管理员");
        engine.clearAndType("name=email", "zhangsan@163.com");
        engine.click("xpath=//button[@type='submit']");
        String border = engine.getCss("name=repassword", "border-bottom-color");
        // 验证输入框变红
        Assert.assertEquals(border, "rgb(221, 0, 0)");
    }

    /**
     * 不选择角色
     * 输入用户名abc_123；
     * 输入密码ab_cd_12_34；
     * 输入重复密码ab_cd_12_34；
     * 不选择角色；
     * 输入邮箱zhangsan@163.com
     */
    @Test
    public void testAddAdminByWrongRole() {
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[8]/ul/li");
        engine.click("partLink=添加管理员");
        engine.clearAndType("xpath=//tbody/tr/td/input", "abc_123");
        engine.clearAndType("name=password", "ab_cd_12_34");
        engine.clearAndType("name=repassword", "ab_cd_12_34");
        engine.clearAndType("name=email", "zhangsan@163.com");
        engine.click("xpath=//button[@type='submit']");
        String border = engine.getCss("name=role_id", "border-bottom-color");
        // 验证输入框变红
        Assert.assertEquals(border, "rgb(221, 0, 0)");
    }

    /**
     * 输入错误邮箱
     * 输入用户名abc_123；
     * 输入密码ab_cd_12_34；
     * 输入重复密码ab_cd_12_34；
     * 选择角色超级管理员；
     * 输入邮箱zhangsan
     */
    @Test
    public void testAddAdminByWrongEmail() {
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[8]/ul/li");
        engine.click("partLink=添加管理员");
        engine.clearAndType("xpath=//tbody/tr/td/input", "abc_123");
        engine.clearAndType("name=password", "ab_cd_12_34");
        engine.clearAndType("name=repassword", "ab_cd_12_34");
        engine.selectByVisibleText("name=role_id", "超级管理员");
        engine.clearAndType("name=email", "zhangsan");
        engine.click("xpath=//button[@type='submit']");
        String border = engine.getCss("name=role_id", "border-bottom-color");
        // 验证输入框变红
        Assert.assertEquals(border, "rgb(6, 172, 135)");
    }

    /**
     * 输入不合法的邮箱
     * 输入用户名abc_123；
     * 输入密码ab_cd_12_34；
     * 输入重复密码ab_cd_12_34；
     * 选择角色超级管理员；
     * 输入邮箱a@a.a
     */
    @Test
    public void testAddAdminByIllegalEmail() {
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[8]/ul/li");
        engine.click("partLink=添加管理员");
        engine.clearAndType("xpath=//tbody/tr/td/input", "abcdef");
        engine.clearAndType("name=password", "123456");
        engine.clearAndType("name=repassword", "123456");
        engine.selectByVisibleText("name=role_id", "超级管理员");
        engine.clearAndType("name=email", "a@a.a");
        engine.click("xpath=//button[@type='submit']");
        // 验证列表中没有添加
        Assert.assertFalse(engine.isDisplayed("xpath=//td[text()='abcdef']"));
    }

    /**
     * 不输入邮箱，验证列表中没有添加
     */
    @Test
    public void testAddAdminByIllegalEmail2() {
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[8]/ul/li");
        engine.click("partLink=添加管理员");
        engine.clearAndType("xpath=//tbody/tr/td/input", "123456");
        engine.clearAndType("name=password", "123456");
        engine.clearAndType("name=repassword", "123456");
        engine.selectByVisibleText("name=role_id", "超级管理员");
        engine.click("xpath=//button[@type='submit']");
        // 验证列表中没有添加
        Assert.assertFalse(engine.isDisplayed("xpath=//td[text()='123456']"));
    }

    /**
     * 添加相同名称的管理员
     */
    @Test
    public void testAddRepetitiveAdmin() {
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[8]/ul/li");
        engine.click("partLink=添加管理员");
        engine.clearAndType("xpath=//tbody/tr/td/input", "test1");
        engine.clearAndType("name=password", "123456");
        engine.clearAndType("name=repassword", "123456");
        engine.selectByVisibleText("name=role_id", "超级管理员");
        engine.click("xpath=//button[@type='submit']");
        // 验证提示信息
        engine.pause(1000);
        boolean exist = engine.getHtmlSource().contains("管理员已经存在");
        Assert.assertTrue(exist);
    }

    /**
     * 删除第二个管理员，点击右侧叉号
     */
    @Test
    public void testDeleteAdmin() {
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[8]/ul/li");
        String name = engine.getAttribute("xpath=//div[@id='admin_right']/div[2]/form/table/tbody/tr[2]/td[2]", "innerHTML");
        engine.click("xpath=//div[@id='admin_right']/div[2]/form/table/tbody/tr[2]/td[1]/input");
        engine.click("xpath=//div[@id='admin_right']/div[2]/form/table/tbody/tr[2]/td[7]/a[2]");
        boolean b = engine.getHtmlSource().contains("是否把信息放到回收站内");
        System.out.println(b);
        if (!b) {
            engine.goBack();
            Assert.fail();
        }
        // 验证列表页面没有之前的管理员
        Assert.assertFalse(engine.isDisplayed("xpath=//td[text()='" + name + "']"));
    }

    /**
     * 选中一个管理员，点击批量删除
     */
    @Test
    public void testDeleteAdminByBatch() {
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[8]/ul/li");
        String name = engine.getAttribute("xpath=//div[@id='admin_right']/div[2]/form/table/tbody/tr[2]/td[2]", "innerHTML");
        engine.click("xpath=//div[@id='admin_right']/div[2]/form/table/tbody/tr[2]/td[1]/input");
        engine.click("partLink=批量删除");
        engine.click("xpath=//button[@type='button']");
        // 验证列表页面没有之前的管理员
        Assert.assertTrue(engine.isDisplayed("xpath=//td[text()='" + name + "']"));
    }

    /**
     * 删除初始管理员，提示不可删除
     */
    @Test
    public void testDeleteOriginalAdmin() {
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[8]/ul/li");
        engine.click("xpath=//div[@id='admin_right']/div[2]/form/table/tbody/tr[1]/td[1]/input");
        engine.click("partLink=批量删除");
        engine.click("xpath=//button[@type='button']");
        // 验证提示不能删除初始管理员
        Assert.assertTrue(engine.getHtmlSource().contains("不允许删除系统初始化管理员"));
    }

    /**
     * 在回收站还原一个管理员
     */
    @Test
    public void testRestoreAdmin() {
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[8]/ul/li");
        engine.click("partLink=回收站");
        String name = engine.getAttribute("xpath=//div[@id='admin_right']/div[2]/form/table/tbody/tr[1]/td[2]", "innerHTML");
        engine.click("xpath=//div[@id='admin_right']/div[2]/form/table/tbody/tr[1]/td[1]/input");
        engine.click("xpath=//div[@id='admin_right']/div[2]/form/table/tbody/tr[1]/td[7]/a[2]");
        engine.click("xpath=//button[@type='button']");
        // 验证列表中是否有还原的管理员
        Assert.assertTrue(engine.getHtmlSource().contains(name));
    }

    /**
     * 更改管理员信息
     */
    @Test
    public void testUpdateAdmin() {
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[8]/ul/li");
        engine.click("xpath=//div[@id='admin_right']/div[2]/form/table/tbody/tr[2]/td[7]/a[1]");
        engine.clearAndType("name=admin_name", "update");
        engine.click("xpath=//button[@type='button']");
        engine.clearAndType("name=password", "123456");
        engine.clearAndType("name=repassword", "123456");
        engine.selectByVisibleText("name=role_id", "超级管理员");
        engine.clearAndType("name=email", "a@11.com");
        engine.click("xpath=//button[@type='submit']");
        // 验证列表中是否有编辑后的管理员
        Assert.assertTrue(engine.getHtmlSource().contains("update"));
    }
}
