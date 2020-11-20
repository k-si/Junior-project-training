package com.seleniumPlus.testcase;

import com.seleniumPlus.core.BaseTest;
import com.seleniumPlus.dataprovider.NSDataProvider;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MerchantTest extends BaseTest {

    /**
     * 登录页url
     */
    private static String LOGIN_URL = "http://localhost:9999/index.php?controller=admin&action=index";

    /**
     * 在类测试之前先登录，打开商户模块
     */
    @BeforeClass
    public void openModule() {
        engine.open(LOGIN_URL);
        engine.type("name=admin_name", "admin");
        engine.type("name=password", "3.1415926");
        engine.type("name=captcha", "abc");
        engine.click("xpath=//button[@type='submit']");
        engine.click("link=会员");
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[3]");
    }

    /**
     * 根据execl，添加多个商家
     */
    @Test(dataProvider = "excel", dataProviderClass = NSDataProvider.class)
    public void addMerchant(String name, String password, String repassword, String trueName, String telephone, String phoneNumber, String email, String discountRate) {
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[3]/ul/li");
        engine.click("partLink=添加商户");
        engine.clearAndType("name=seller_name", name);
        engine.clearAndType("name=password", password);
        engine.clearAndType("name=repassword", repassword);
        engine.clearAndType("name=true_name", trueName);
        engine.clearAndType("name=phone", telephone);
        engine.clearAndType("name=mobile", phoneNumber);
        engine.clearAndType("name=email", email);
        engine.clearAndType("name=discount", discountRate);
        engine.click("xpath=//button[@type='submit']");
        // 检验列表中有没有添加的名字
        Assert.assertTrue(engine.isDisplayed("xpath=//td[text()='" + name + "']"));
    }

    /**
     * 添加商户的时候输入过长的名称
     */
    @Test
    public void addMerchantByLongName() {
        String longName = "11111111111111111111111111111111111111111111111111111111111";
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[3]/ul/li");
        engine.click("partLink=添加商户");
        engine.clearAndType("name=seller_name", longName);
        engine.clearAndType("name=password", "123456");
        engine.clearAndType("name=repassword", "123456");
        engine.clearAndType("name=true_name", "无名氏");
        engine.clearAndType("name=phone", "1235889");
        engine.clearAndType("name=mobile", "18633697815");
        engine.clearAndType("name=email", "aa@a.com");
        engine.clearAndType("name=discount", "3");
        engine.click("xpath=//button[@type='submit']");
        // 检验列表中有没有添加的名字
        Assert.assertFalse(engine.isDisplayed("xpath=//td[text()='" + longName + "']"));
    }

    /**
     * 输入重复密码时错误
     */
    @Test
    public void addMerchantByWrongRepassword() {
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[3]/ul/li");
        engine.click("partLink=添加商户");
        engine.clearAndType("name=seller_name", "custom");
        engine.clearAndType("name=password", "123456");
        engine.clearAndType("name=repassword", "12345");
        engine.clearAndType("name=true_name", "游客");
        engine.clearAndType("name=phone", "1235889");
        engine.clearAndType("name=mobile", "18633697815");
        engine.clearAndType("name=email", "aa@a.com");
        engine.clearAndType("name=discount", "3");
        engine.click("xpath=//button[@type='submit']");
        // 检验列表中有没有添加的名字
        Assert.assertTrue(engine.getHtmlSource().contains("两次输入的密码不一致"));
    }

    /**
     * 输入非法的折扣率-1
     */
    @Test
    public void addMerchantByWrongDiscount() {
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[3]/ul/li");
        engine.click("partLink=添加商户");
        engine.clearAndType("name=seller_name", "custom1");
        engine.clearAndType("name=password", "123456");
        engine.clearAndType("name=repassword", "123456");
        engine.clearAndType("name=true_name", "游客");
        engine.clearAndType("name=phone", "1235889");
        engine.clearAndType("name=mobile", "18633697815");
        engine.clearAndType("name=email", "aa@a.com");
        engine.clearAndType("name=discount", "-1");
        engine.click("xpath=//button[@type='submit']");
        // 检验列表中有没有添加的名字
        Assert.assertTrue(engine.getHtmlSource().contains("商户结算折扣率请填写0~100的数字"));
    }

    /**
     * 输入非法的折扣率101
     */
    @Test
    public void addMerchantByWrongDiscount2() {
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[3]/ul/li");
        engine.click("partLink=添加商户");
        engine.clearAndType("name=seller_name", "custom2");
        engine.clearAndType("name=password", "123456");
        engine.clearAndType("name=repassword", "123456");
        engine.clearAndType("name=true_name", "游客");
        engine.clearAndType("name=phone", "1235889");
        engine.clearAndType("name=mobile", "18633697815");
        engine.clearAndType("name=email", "aa@a.com");
        engine.clearAndType("name=discount", "101");
        engine.click("xpath=//button[@type='submit']");
        // 检验列表中有没有添加的名字
        Assert.assertTrue(engine.getHtmlSource().contains("商户结算折扣率请填写0~100的数字"));
    }

    /**
     * 输入非法的折扣率101
     */
    @Test
    public void addMerchantByWrongDiscount3() {
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[3]/ul/li");
        engine.click("partLink=添加商户");
        engine.clearAndType("name=seller_name", "custom3");
        engine.clearAndType("name=password", "123456");
        engine.clearAndType("name=repassword", "123456");
        engine.clearAndType("name=true_name", "游客");
        engine.clearAndType("name=phone", "1235889");
        engine.clearAndType("name=mobile", "18633697815");
        engine.clearAndType("name=email", "aa@a.com");
        engine.clearAndType("name=discount", "21474836474");
        engine.click("xpath=//button[@type='submit']");
        // 检验列表中有没有添加的名字
        Assert.assertTrue(engine.getHtmlSource().contains("商户结算折扣率请填写0~100的数字"));
    }

    /**
     * 输入错误的收款账号
     */
    @Test
    public void addMerchantByWrongAccount() {
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[3]/ul/li");
        engine.click("partLink=添加商户");
        engine.clearAndType("name=seller_name", "custom4");
        engine.clearAndType("name=password", "123456");
        engine.clearAndType("name=repassword", "123456");
        engine.clearAndType("name=true_name", "游客");
        engine.clearAndType("name=account", "1");
        engine.clearAndType("name=phone", "1235889");
        engine.clearAndType("name=mobile", "18633697815");
        engine.clearAndType("name=email", "aa@a.com");
        engine.clearAndType("name=discount", "21474836474");
        engine.click("xpath=//button[@type='submit']");
        // 检验列表中有没有添加的名字
        Assert.assertTrue(engine.getHtmlSource().contains("收款账户错误"));
    }

    /**
     * 删除一个商户
     */
    @Test
    public void deleteMerchant() {
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[3]/ul/li");
        String name = engine.getAttribute("xpath=//div[@id='admin_right']/div/table/tbody/tr/td[2]", "innerHTML");
        engine.click("xpath=//div[@id='admin_right']/div/table/tbody/tr/td[11]/a[2]");
        engine.click("xpath=//button[@type='button']");
        // 检验列表中有没有添加的名字
        Assert.assertFalse(engine.isDisplayed("xpath=//td[text()='" + name + "']"));
    }

    /**
     * 删除多个商户
     */
    @Test
    public void deleteMerchantByBatch() {
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[3]/ul/li");
        String name1 = engine.getAttribute("xpath=//div[@id='admin_right']/div/table/tbody/tr[1]/td[2]", "innerHTML");
        String name2 = engine.getAttribute("xpath=//div[@id='admin_right']/div/table/tbody/tr[2]/td[2]", "innerHTML");
        engine.click("xpath=//div[@id='admin_right']/div/table/tbody/tr/td/input");
        engine.click("xpath=//div[@id='admin_right']/div/table/tbody/tr[2]/td/input");
        engine.click("partLink=批量删除");
        engine.click("xpath=//button[@type='button']");
        // 检验列表中有没有添加的名字
        Assert.assertFalse(engine.isDisplayed("xpath=//td[text()='" + name1 + "']"));
        Assert.assertFalse(engine.isDisplayed("xpath=//td[text()='" + name2 + "']"));
    }

    /**
     * 还原一个商户
     */
    @Test
    public void restoreMerchant() {
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[3]/ul/li");
        engine.click("partLink=回收站");
        String name = engine.getAttribute("xpath=//div[@id='admin_right']/form/div/table/tbody/tr[1]/td[2]", "innerHTML");
        engine.click("xpath=//div[@id='admin_right']/form/div/table/tbody/tr[1]/td[10]/a[1]");
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[3]/ul/li");
        // 检验列表中有没有还原的名称
        Assert.assertTrue(engine.isDisplayed("xpath=//td[text()='" + name + "']"));
    }

    /**
     * 还原多个账户
     */
    @Test
    public void restoreMerchants() {
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[3]/ul/li");
        engine.click("partLink=回收站");
        String name1 = engine.getAttribute("xpath=//div[@id='admin_right']/form/div/table/tbody/tr[1]/td[2]", "innerHTML");
        String name2 = engine.getAttribute("xpath=//div[@id='admin_right']/form/div/table/tbody/tr[2]/td[2]", "innerHTML");
        engine.click("xpath=//div[@id='admin_right']/form/div/table/tbody/tr[1]/td[1]/input");
        engine.click("xpath=//div[@id='admin_right']/form/div/table/tbody/tr[2]/td[1]/input");
        engine.click("partLink=还原");
        engine.click("xpath=//button[@type='button']");
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[3]/ul/li");
        // 检验列表中有没有还原的名称
        Assert.assertTrue(engine.isDisplayed("xpath=//td[text()='" + name1 + "']"));
        Assert.assertTrue(engine.isDisplayed("xpath=//td[text()='" + name2 + "']"));
    }

    /**
     * 修改商家
     */
    @Test
    public void updateMerchant() {
        engine.click("xpath=//aside[@id='admin_left']/section/ul/li[3]/ul/li");
        engine.click("xpath=//div[@id='admin_right']/div[2]/table/tbody/tr[1]/td[11]/a[1]");
        engine.clearAndType("name=true_name", "update");
        engine.click("xpath=//button[@type='submit']");
        // 检验列表中有没有更新的名称
        Assert.assertTrue(engine.isDisplayed("xpath=//td[text()='update']"));
    }
}
