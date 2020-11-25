package com.seleniumPlus.testcase;

import com.seleniumPlus.core.BaseTest;
import com.seleniumPlus.core.WebDriverEngine;
import com.seleniumPlus.dataprovider.NSDataProvider;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestCase extends BaseTest{

    String backUrl = "http://iwebshop:8889/index.php?controller=admin&action=index";
    String frontUrl = "http://iwebshop:8889/";

    /**
     * 测试更改商店名称
     * @throws InterruptedException
     */
    @Test
    public void testAlterName() throws InterruptedException {
        //打开网站后台
        engine.open(backUrl);
        //登录
        engine.type("name=admin_name" , "admin");
        engine.type("name=password" , "lengtong5655");
        engine.type("name=captcha" , "123456");
        engine.click("class=btn");

        //点击网站管理
        engine.click("xpath=//span[text()='网站管理']");
        //点击网站设置
        engine.click("xpath=//a[text()='网站设置']");
        //修改商店名称
        engine.clearAndType("xpath=//input[@name='name']" , "iWebShop开源商城系统测试");
        //滑动到底部
        engine.slideDownToBottom();

        engine.pause(1000);
        //点击保存按钮
        engine.click("xpath=//button[@type='submit' and text()='保存基本设置']");


        engine.pause(10000);
        //打开前台网页
        engine.open(frontUrl);
        //转换窗口
        //engine.switchWidow(1);
        //获取文本值
        String text = engine.getText("class=welcome");
        //获取标题
        String title = engine.getTitle();

        Assert.assertTrue(text.contains("iWebShop开源商城系统测试"));
        Assert.assertTrue(!title.contains("iWebShop开源商城系统测试"));
    }


    /**
     * 测试更改商店名称为数字字符串
     * @throws InterruptedException
     */
    @Test
    public void testAlterNameByNumber() throws InterruptedException {
        //打开网站后台
        engine.open(backUrl);
        //登录
        engine.type("name=admin_name" , "admin");
        engine.type("name=password" , "lengtong5655");
        engine.type("name=captcha" , "123456");
        engine.click("class=btn");

        //点击网站管理
        engine.click("xpath=//span[text()='网站管理']");
        //点击网站设置
        engine.click("xpath=//a[text()='网站设置']");
        //修改商店名称
        engine.clearAndType("xpath=//input[@name='name']" , "123456");
        //滑动到底部
        engine.slideDownToBottom();

        engine.pause(1000);
        //点击保存按钮
        engine.click("xpath=//button[@type='submit' and text()='保存基本设置']");


        engine.pause(10000);
        //打开前台网页
        engine.open(frontUrl);
        //转换窗口
        //engine.switchWidow(1);
        //获取文本值
        String text = engine.getText("class=welcome");
        //获取标题
        String title = engine.getTitle();

        Assert.assertTrue(!text.contains("123456"));
    }

    /**
     * 修改商店正确网址
     */
    @Test
    public void testAlterURL(){
        //打开网站后台
        engine.open(backUrl);
        //登录
        engine.type("name=admin_name" , "admin");
        engine.type("name=password" , "lengtong5655");
        engine.type("name=captcha" , "123456");
        engine.click("class=btn");

        //点击网站管理
        engine.click("xpath=//span[text()='网站管理']");
        //点击网站设置
        engine.click("xpath=//a[text()='网站设置']");
        //修改网站网址
        engine.clearAndType("xpath=//input[@name='url']" , "http://www.aircheng.com");
        //滑动到底部
        engine.slideDownToBottom();
        //点击保存
        engine.click("xpath=//button[@type='submit' and text()='保存基本设置']");

        //打开网页
        engine.open("http://www.aircheng.com");

        Assert.assertTrue(!engine.getTitle().contains("404"));



    }

    /**
     * 修改商店错误网址
     */
    @Test
    public void testAlterErrorURL(){
        //打开网站后台
        engine.open(backUrl);
        //登录
        engine.type("name=admin_name" , "admin");
        engine.type("name=password" , "lengtong5655");
        engine.type("name=captcha" , "123456");
        engine.click("class=btn");

        //点击网站管理
        engine.click("xpath=//span[text()='网站管理']");
        //点击网站设置
        engine.click("xpath=//a[text()='网站设置']");
        //修改网站网址
        engine.clearAndType("xpath=//input[@name='url']" , "http://www.airchengabc.com");
        //滑动到底部
        engine.slideDownToBottom();
        //点击保存
        engine.click("xpath=//button[@type='submit' and text()='保存基本设置']");

        //打开网页
        engine.open("http://www.airchengabc.com");

        Assert.assertTrue(engine.getHtmlSource().contains("连接超时"));



    }

    /**
     * 修改标题
     */
    @Test
    public void testAlterTitle(){
        //打开网站后台
        engine.open(backUrl);
        //登录
        engine.type("name=admin_name" , "admin");
        engine.type("name=password" , "lengtong5655");
        engine.type("name=captcha" , "123456");
        engine.click("class=btn");

        //点击网站管理
        engine.click("xpath=//span[text()='网站管理']");
        //点击网站设置
        engine.click("xpath=//a[text()='网站设置']");

        engine.clearAndType("xpath=//input[@name='index_seo_title']" , "iWebShop开源商城系统");

        engine.slideDownToBottom();

        engine.click("xpath=//button[@class='btn btn-primary' and text()='保存基本设置']");

        //打开前台页面
        engine.open(frontUrl);

        String title = engine.getTitle();

        Assert.assertEquals(title , "iWebShop开源商城系统");
    }

    /**
     * 修改标题
     */
    @Test
    public void testAlterTitleByNumber(){
        //打开网站后台
        engine.open(backUrl);
        //登录
        engine.type("name=admin_name" , "admin");
        engine.type("name=password" , "lengtong5655");
        engine.type("name=captcha" , "123456");
        engine.click("class=btn");

        //点击网站管理
        engine.click("xpath=//span[text()='网站管理']");
        //点击网站设置
        engine.click("xpath=//a[text()='网站设置']");

        engine.clearAndType("xpath=//input[@name='index_seo_title']" , "123456");

        engine.slideDownToBottom();

        engine.click("xpath=//button[@class='btn btn-primary' and text()='保存基本设置']");

        String value = engine.getValue("xpath=//input[@name='index_seo_title']");

        Assert.assertTrue(!value.equals("123456"));
    }

    /**
     * 修改联系人
     */
    @Test
    public void testAlterContacts(){
        //打开网站后台
        engine.open(backUrl);
        //登录
        engine.type("name=admin_name" , "admin");
        engine.type("name=password" , "lengtong5655");
        engine.type("name=captcha" , "123456");
        engine.click("class=btn");

        //点击网站管理
        engine.click("xpath=//span[text()='网站管理']");
        //点击网站设置
        engine.click("xpath=//a[text()='网站设置']");

        engine.clearAndType("xpath=//input[@class='form-control' and @name='master']" , "李四");

        engine.slideDownToBottom();

        engine.click("xpath=//button[@class='btn btn-primary' and text()='保存基本设置']");

        String value = engine.getValue("xpath=//input[@class='form-control' and @name='master']");

        Assert.assertEquals(value , "李四");
    }

    /**
     * 在联系人中输入数字字符串
     */
    @Test
    public void testAlterContactsByNumber(){
        //打开网站后台
        engine.open(backUrl);
        //登录
        engine.type("name=admin_name" , "admin");
        engine.type("name=password" , "lengtong5655");
        engine.type("name=captcha" , "123456");
        engine.click("class=btn");

        //点击网站管理
        engine.click("xpath=//span[text()='网站管理']");
        //点击网站设置
        engine.click("xpath=//a[text()='网站设置']");

        engine.clearAndType("xpath=//input[@class='form-control' and @name='master']" , "12345");

        engine.slideDownToBottom();

        engine.click("xpath=//button[@class='btn btn-primary' and text()='保存基本设置']");

        String value = engine.getValue("xpath=//input[@class='form-control' and @name='master']");

        Assert.assertTrue(!value.equals("12345"));
    }


    /**
     * 修改QQ
     */
    @Test
    public void testAlterQQ(){
        //打开网站后台
        engine.open(backUrl);
        //登录
        engine.type("name=admin_name" , "admin");
        engine.type("name=password" , "lengtong5655");
        engine.type("name=captcha" , "123456");
        engine.click("class=btn");

        //点击网站管理
        engine.click("xpath=//span[text()='网站管理']");
        //点击网站设置
        engine.click("xpath=//a[text()='网站设置']");

        engine.clearAndType("xpath=//input[@name='qq']" , "123456789");

        engine.slideDownToBottom();

        engine.click("xpath=//button[@class='btn btn-primary' and text()='保存基本设置']");

        String value = engine.getValue("xpath=//input[@name='qq']");

        Assert.assertEquals(value , "123456789");
    }

    /**
     * 修改邮箱
     */
    @Test
    public void testAlterEmail(){
        //打开网站后台
        engine.open(backUrl);
        //登录
        engine.type("name=admin_name" , "admin");
        engine.type("name=password" , "lengtong5655");
        engine.type("name=captcha" , "123456");
        engine.click("class=btn");

        //点击网站管理
        engine.click("xpath=//span[text()='网站管理']");
        //点击网站设置
        engine.click("xpath=//a[text()='网站设置']");

        engine.clearAndType("xpath=//input[@name='email']" , "123456789@qq.com");

        engine.slideDownToBottom();

        engine.click("xpath=//button[@class='btn btn-primary' and text()='保存基本设置']");

        String value = engine.getValue("xpath=//input[@name='email']");

        Assert.assertEquals(value , "123456789@qq.com");
    }

    /**
     * 修改手机号
     */
    @Test
    public void testAlterPhonenumber(){
        //打开网站后台
        engine.open(backUrl);
        //登录
        engine.type("name=admin_name" , "admin");
        engine.type("name=password" , "lengtong5655");
        engine.type("name=captcha" , "123456");
        engine.click("class=btn");

        //点击网站管理
        engine.click("xpath=//span[text()='网站管理']");
        //点击网站设置
        engine.click("xpath=//a[text()='网站设置']");
        
        engine.clearAndType("xpath=//input[@name='mobile']" , "19831126681");

        engine.click("xpath=//button[@class='btn btn-primary' and text()='保存基本设置']");

        String value = engine.getValue("xpath=//input[@name='mobile']");

        Assert.assertEquals(value , "19831126681");
    }


    /**
     * 修改手机号，保存文本内容
     */
    @Test
    public void testAlterPhonenumberByString(){
        //打开网站后台
        engine.open(backUrl);
        //登录
        engine.type("name=admin_name" , "admin");
        engine.type("name=password" , "lengtong5655");
        engine.type("name=captcha" , "123456");
        engine.click("class=btn");

        //点击网站管理
        engine.click("xpath=//span[text()='网站管理']");
        //点击网站设置
        engine.click("xpath=//a[text()='网站设置']");

        engine.clearAndType("xpath=//input[@name='mobile']" , "哈哈哈");

        engine.click("xpath=//button[@class='btn btn-primary' and text()='保存基本设置']");

        //点击网站设置，相当于刷新
        engine.click("xpath=//a[text()='网站设置']");

        String value = engine.getValue("xpath=//input[@name='mobile']");

        Assert.assertTrue(!value.equals("哈哈哈"));
    }


    /**
     * 修改客服电话
     */
    @Test
    public void testAlterServicetel(){
        //打开网站后台
        engine.open(backUrl);
        //登录
        engine.type("name=admin_name" , "admin");
        engine.type("name=password" , "lengtong5655");
        engine.type("name=captcha" , "123456");
        engine.click("class=btn");

        //点击网站管理
        engine.click("xpath=//span[text()='网站管理']");
        //点击网站设置
        engine.click("xpath=//a[text()='网站设置']");

        engine.clearAndType("xpath=//input[@name='phone']" , "123456");

        engine.click("xpath=//button[@class='btn btn-primary' and text()='保存基本设置']");

        String value = engine.getValue("xpath=//input[@name='phone']");

        Assert.assertEquals(value , "123456");
    }


    /**
     * 修改客服电话，保存文本内容
     */
    @Test
    public void testAlterServicetelByString(){
        //打开网站后台
        engine.open(backUrl);
        //登录
        engine.type("name=admin_name" , "admin");
        engine.type("name=password" , "lengtong5655");
        engine.type("name=captcha" , "123456");
        engine.click("class=btn");

        //点击网站管理
        engine.click("xpath=//span[text()='网站管理']");
        //点击网站设置
        engine.click("xpath=//a[text()='网站设置']");

        engine.clearAndType("xpath=//input[@name='phone']" , "哈哈哈哈");

        engine.click("xpath=//button[@class='btn btn-primary' and text()='保存基本设置']");

        //点击网站设置，相当于刷新
        engine.click("xpath=//a[text()='网站设置']");

        String value = engine.getValue("xpath=//input[@name='phone']");

        Assert.assertTrue(!value.equals("哈哈哈哈"));
    }

    /**
     * 修改地址
     */
    @Test
    public void testAlterAddress(){
        //打开网站后台
        engine.open(backUrl);
        //登录
        engine.type("name=admin_name" , "admin");
        engine.type("name=password" , "lengtong5655");
        engine.type("name=captcha" , "123456");
        engine.click("class=btn");

        //点击网站管理
        engine.click("xpath=//span[text()='网站管理']");
        //点击网站设置
        engine.click("xpath=//a[text()='网站设置']");

        engine.clearAndType("xpath=//input[@name='address']" , "河北师大");

        engine.click("xpath=//button[@class='btn btn-primary' and text()='保存基本设置']");

        String value = engine.getValue("xpath=//input[@name='address']");

        Assert.assertEquals(value , "河北师大");
    }

    /**
     * 修改地址
     */
    @Test
    public void testAlterAddressByNumber(){
        //打开网站后台
        engine.open(backUrl);
        //登录
        engine.type("name=admin_name" , "admin");
        engine.type("name=password" , "lengtong5655");
        engine.type("name=captcha" , "123456");
        engine.click("class=btn");

        //点击网站管理
        engine.click("xpath=//span[text()='网站管理']");
        //点击网站设置
        engine.click("xpath=//a[text()='网站设置']");

        engine.clearAndType("xpath=//input[@name='address']" , "123456");

        engine.click("xpath=//button[@class='btn btn-primary' and text()='保存基本设置']");

        //点击网站设置
        engine.click("xpath=//a[text()='网站设置']");

        String value = engine.getValue("xpath=//input[@name='address']");

        Assert.assertTrue(!value.equals("123456"));
    }

    /**
     * 修改货号前缀
     */
    @Test
    public void testAlterPrefix(){
        //打开网站后台
        engine.open(backUrl);
        //登录
        engine.type("name=admin_name" , "admin");
        engine.type("name=password" , "lengtong5655");
        engine.type("name=captcha" , "123456");
        engine.click("class=btn");

        //点击网站管理
        engine.click("xpath=//span[text()='网站管理']");
        //点击网站设置
        engine.click("xpath=//a[text()='网站设置']");

        engine.clearAndType("xpath=//input[@name='goods_no_pre']" , "BD");

        engine.click("xpath=//button[@class='btn btn-primary' and text()='保存基本设置']");

        engine.open(frontUrl);

        engine.click("xpath=//img[@alt='2014 最新爆款 欧美时尚大牌 真皮手提包包']");

        String text = engine.getText("id=data_goodsNo");

        Assert.assertTrue(text.startsWith("BD"));
    }

    /**
     * 修改货号前缀为文本字符
     */
    @Test
    public void testAlterPrefixByString(){
        //打开网站后台
        engine.open(backUrl);
        //登录
        engine.type("name=admin_name" , "admin");
        engine.type("name=password" , "lengtong5655");
        engine.type("name=captcha" , "123456");
        engine.click("class=btn");

        //点击网站管理
        engine.click("xpath=//span[text()='网站管理']");
        //点击网站设置
        engine.click("xpath=//a[text()='网站设置']");

        engine.clearAndType("xpath=//input[@name='goods_no_pre']" , "哈哈");

        engine.click("xpath=//button[@class='btn btn-primary' and text()='保存基本设置']");

        String value = engine.getValue("xpath=//input[@name='goods_no_pre']");

        Assert.assertTrue(value.equals("哈哈"));

        engine.open(frontUrl);

        engine.click("xpath=//img[@alt='2014 最新爆款 欧美时尚大牌 真皮手提包包']");

        String text = engine.getText("id=data_goodsNo");

        Assert.assertTrue(!text.startsWith("哈哈"));
    }

    /**
     * 修改排序方式为默认方式
     */
    @Test
    public void testAlterOrderBySort(){
        //打开网站后台
        engine.open(backUrl);
        //登录
        engine.type("name=admin_name" , "admin");
        engine.type("name=password" , "lengtong5655");
        engine.type("name=captcha" , "123456");
        engine.click("class=btn");

        //点击网站管理
        engine.click("xpath=//span[text()='网站管理']");
        //点击网站设置
        engine.click("xpath=//a[text()='网站设置']");
        //点击其他设置
        engine.click("xpath=//a[text()='其他设置']");

        engine.click("xpath=//select[@name='order_by']");

        engine.click("xpath=//option[@value='sort']");

        engine.click("xpath=//button[@class='btn btn-primary' and text()='保存配置']");

        //点击其他设置
        engine.click("xpath=//a[text()='其他设置']");

        String text = engine.getValue("xpath=//select[@name='order_by']");

        Assert.assertEquals(text , "sort");
    }

    /**
     * 修改排序方式为评分
     */
    @Test
    public void testAlterOrderBySale(){
        //打开网站后台
        engine.open(backUrl);
        //登录
        engine.type("name=admin_name" , "admin");
        engine.type("name=password" , "lengtong5655");
        engine.type("name=captcha" , "123456");
        engine.click("class=btn");

        //点击网站管理
        engine.click("xpath=//span[text()='网站管理']");
        //点击网站设置
        engine.click("xpath=//a[text()='网站设置']");
        //点击其他设置
        engine.click("xpath=//a[text()='其他设置']");

        engine.click("xpath=//select[@name='order_by']");

        engine.click("xpath=//option[@value='sale']");

        engine.click("xpath=//button[@class='btn btn-primary' and text()='保存配置']");

        //点击其他设置
        engine.click("xpath=//a[text()='其他设置']");

        String text = engine.getValue("xpath=//select[@name='order_by']");

        Assert.assertEquals(text , "sale");
    }

    /**
     * 修改排序方式为评分
     */
    @Test
    public void testAlterOrderByCpoint(){
        //打开网站后台
        engine.open(backUrl);
        //登录
        engine.type("name=admin_name" , "admin");
        engine.type("name=password" , "lengtong5655");
        engine.type("name=captcha" , "123456");
        engine.click("class=btn");

        //点击网站管理
        engine.click("xpath=//span[text()='网站管理']");
        //点击网站设置
        engine.click("xpath=//a[text()='网站设置']");
        //点击其他设置
        engine.click("xpath=//a[text()='其他设置']");

        engine.click("xpath=//select[@name='order_by']");

        engine.click("xpath=//option[@value='cpoint']");

        engine.click("xpath=//button[@class='btn btn-primary' and text()='保存配置']");

        //点击其他设置
        engine.click("xpath=//a[text()='其他设置']");

        String text = engine.getValue("xpath=//select[@name='order_by']");

        Assert.assertEquals(text , "cpoint");
    }

    /**
     * 修改排序方式为价格
     */
    @Test
    public void testAlterOrderByPrice(){
        //打开网站后台
        engine.open(backUrl);
        //登录
        engine.type("name=admin_name" , "admin");
        engine.type("name=password" , "lengtong5655");
        engine.type("name=captcha" , "123456");
        engine.click("class=btn");

        //点击网站管理
        engine.click("xpath=//span[text()='网站管理']");
        //点击网站设置
        engine.click("xpath=//a[text()='网站设置']");
        //点击其他设置
        engine.click("xpath=//a[text()='其他设置']");

        engine.click("xpath=//select[@name='order_by']");

        engine.click("xpath=//option[@value='price']");

        engine.click("xpath=//button[@class='btn btn-primary' and text()='保存配置']");

        //点击其他设置
        engine.click("xpath=//a[text()='其他设置']");

        String text = engine.getValue("xpath=//select[@name='order_by']");

        Assert.assertEquals(text , "price");
    }

    /**
     * 修改排序方式为最新上架
     */
    @Test
    public void testAlterOrderByNew(){
        //打开网站后台
        engine.open(backUrl);
        //登录
        engine.type("name=admin_name" , "admin");
        engine.type("name=password" , "lengtong5655");
        engine.type("name=captcha" , "123456");
        engine.click("class=btn");

        //点击网站管理
        engine.click("xpath=//span[text()='网站管理']");
        //点击网站设置
        engine.click("xpath=//a[text()='网站设置']");
        //点击其他设置
        engine.click("xpath=//a[text()='其他设置']");

        engine.click("xpath=//select[@name='order_by']");

        engine.click("xpath=//option[@value='new']");

        engine.click("xpath=//button[@class='btn btn-primary' and text()='保存配置']");

        //点击其他设置
        engine.click("xpath=//a[text()='其他设置']");

        String text = engine.getValue("xpath=//select[@name='order_by']");

        Assert.assertEquals(text , "new");
    }

    /**
     * 修改注册设置为默认
     */
    @Test
    public void testAlterLoginByDefault(){

        //打开网站后台
        engine.open(backUrl);
        //登录
        engine.type("name=admin_name" , "admin");
        engine.type("name=password" , "lengtong5655");
        engine.type("name=captcha" , "123456");
        engine.click("class=btn");

        //点击网站管理
        engine.click("xpath=//span[text()='网站管理']");
        //点击网站设置
        engine.click("xpath=//a[text()='网站设置']");
        //点击其他设置
        engine.click("xpath=//a[text()='其他设置']");

        engine.click("xpath=//input[@name='reg_option' and @value='0']");


        engine.click("xpath=//button[@class='btn btn-primary' and text()='保存配置']");

        engine.open(frontUrl);

        String htmlSource = engine.getHtmlSource();

        Assert.assertTrue(!htmlSource.contains("404"));
    }

    /**
     * 修改注册设置为邮箱
     */
    @Test
    public void testAlterLoginByEmail(){

        //打开网站后台
        engine.open(backUrl);
        //登录
        engine.type("name=admin_name" , "admin");
        engine.type("name=password" , "lengtong5655");
        engine.type("name=captcha" , "123456");
        engine.click("class=btn");

        //点击网站管理
        engine.click("xpath=//span[text()='网站管理']");
        //点击网站设置
        engine.click("xpath=//a[text()='网站设置']");
        //点击其他设置
        engine.click("xpath=//a[text()='其他设置']");

        engine.click("xpath=//input[@name='reg_option' and @value = '1']");


        engine.click("xpath=//button[@class='btn btn-primary' and text()='保存配置']");

        engine.open(frontUrl);

        engine.click("xpath=//a[text()='注册']");

        WebElement o = engine.findElement("xpath=//input[@name='email']");

        Assert.assertNotNull(o);
    }


    /**
     * 关闭注册
     */
    @Test
    public void testCloseLogin(){

        //打开网站后台
        engine.open(backUrl);
        //登录
        engine.type("name=admin_name" , "admin");
        engine.type("name=password" , "lengtong5655");
        engine.type("name=captcha" , "123456");
        engine.click("class=btn");

        //点击网站管理
        engine.click("xpath=//span[text()='网站管理']");
        //点击网站设置
        engine.click("xpath=//a[text()='网站设置']");
        //点击其他设置
        engine.click("xpath=//a[text()='其他设置']");

        engine.click("xpath=//input[@name='reg_option' and @value='2']");


        engine.click("xpath=//button[@class='btn btn-primary' and text()='保存配置']");

        engine.open(frontUrl);

        engine.click("xpath=//a[text()='注册']");

        String htmlSource = engine.getHtmlSource();

        Assert.assertTrue(htmlSource.contains("网站当前已经关闭注册"));
    }


    /**
     * 修改最低额度
     */
    @Test
    public void testAlterQuota(){
        //打开网站后台
        engine.open(backUrl);
        //登录
        engine.type("name=admin_name" , "admin");
        engine.type("name=password" , "lengtong5655");
        engine.type("name=captcha" , "123456");
        engine.click("class=btn");

        //点击网站管理
        engine.click("xpath=//span[text()='网站管理']");
        //点击网站设置
        engine.click("xpath=//a[text()='网站设置']");
        //点击其他设置
        engine.click("xpath=//a[text()='其他设置']");

        engine.clearAndType("xpath=//input[@name='low_withdraw']" , "30");

        engine.click("xpath=//button[@class='btn btn-primary' and text()='保存配置']");

        engine.open(frontUrl);

        engine.click("xpath=//a[text()='登录']");

        engine.type("xpath=//input[@name='login_info']" , "lengtong");
        engine.type("xpath=//input[@name='password']" , "lengtong5655");
        engine.click("xpath=//input[@class='input_submit']");

        engine.click("xpath=//a[text()='帐户余额']");
        engine.click("xpath=//a[text()='提现申请']");

        String text = engine.getText("xpath=//section[@class='user_form']/form[@name='withdraw']/dl[2]/dd/span");

        Assert.assertTrue(text.contains("30"));
    }

    /**
     * 修改最低额度为负数
     */
    @Test
    public void testAlterQuotaToNegative(){
        //打开网站后台
        engine.open(backUrl);
        //登录
        engine.type("name=admin_name" , "admin");
        engine.type("name=password" , "lengtong5655");
        engine.type("name=captcha" , "123456");
        engine.click("class=btn");

        //点击网站管理
        engine.click("xpath=//span[text()='网站管理']");
        //点击网站设置
        engine.click("xpath=//a[text()='网站设置']");
        //点击其他设置
        engine.click("xpath=//a[text()='其他设置']");

        engine.clearAndType("xpath=//input[@name='low_withdraw']" , "-10");

        engine.click("xpath=//button[@class='btn btn-primary' and text()='保存配置']");

        //点击其他设置
        engine.click("xpath=//a[text()='其他设置']");

        String value = engine.getValue("xpath=//input[@name='low_withdraw']");

        Assert.assertTrue(!value.equals("-10"));
    }

    /**
     * 修改最低额度为负数
     */
    @Test
    public void testAlterQuotaToBigNumber(){
        //打开网站后台
        engine.open(backUrl);
        //登录
        engine.type("name=admin_name" , "admin");
        engine.type("name=password" , "lengtong5655");
        engine.type("name=captcha" , "123456");
        engine.click("class=btn");

        //点击网站管理
        engine.click("xpath=//span[text()='网站管理']");
        //点击网站设置
        engine.click("xpath=//a[text()='网站设置']");
        //点击其他设置
        engine.click("xpath=//a[text()='其他设置']");

        engine.clearAndType("xpath=//input[@name='low_withdraw']" , "1000000000");

        engine.click("xpath=//button[@class='btn btn-primary' and text()='保存配置']");

        //点击其他设置
        engine.click("xpath=//a[text()='其他设置']");

        String value = engine.getValue("xpath=//input[@name='low_withdraw']");

        Assert.assertTrue(!value.equals("1000000000"));
    }

    /**
     * 修改网页前台主题
     */
    @Test
    public void testAlterFrontTheme(){
        //打开网站后台
        engine.open(backUrl);
        //登录
        engine.type("name=admin_name" , "admin");
        engine.type("name=password" , "lengtong5655");
        engine.type("name=captcha" , "123456");
        engine.click("class=btn");

        //点击网站管理
        engine.click("xpath=//span[text()='网站管理']");

        engine.click("xpath=//a[text()='网站前台主题']");

        //点击选择主题的下拉框
        engine.click("xpath=//select[@name='pc']");

        //选择主题
        engine.click("xpath//select[@name='pc']/option[text()='锦绣前程【锦绣前程-黑色皮肤】']");

        //点击保存
        engine.click("xpath=//button[@class='btn btn-primary']");

        engine.click("xpath=//button[text()='确定']");

        String value = engine.getValue("xpath=//select[@name='pc']");

        Assert.assertEquals(value , "{\"default\":\"black\"}");
    }

    /**
     * 修改后台主题
     */
    @Test
    public void testAlterBackTheme(){
        //打开网站后台
        engine.open(backUrl);
        //登录
        engine.type("name=admin_name" , "admin");
        engine.type("name=password" , "lengtong5655");
        engine.type("name=captcha" , "123456");
        engine.click("class=btn");

        //点击网站管理
        engine.click("xpath=//span[text()='网站管理']");

        engine.click("xpath=//a[text()='后台管理主题']");

        //点击选择主题的下拉框
        engine.click("xpath=//select[@name='pc']");

        //选择主题
        engine.click("xpath//select[@name='pc']/option[text()='DM后台主题【默认皮肤】']");

        //点击保存
        engine.click("xpath=//button[@class='btn btn-primary']");

        engine.click("xpath=//button[text()='确定']");

        String value = engine.getValue("xpath=//select[@name='pc']");

        Assert.assertEquals(value , "{\"sysdm\":\"default\"}");
    }

    /**
     * 修改商家管理主题
     */
    @Test
    public void testAlterMerchantTheme(){
        //打开网站后台
        engine.open(backUrl);
        //登录
        engine.type("name=admin_name" , "admin");
        engine.type("name=password" , "lengtong5655");
        engine.type("name=captcha" , "123456");
        engine.click("class=btn");

        //点击网站管理
        engine.click("xpath=//span[text()='网站管理']");

        engine.click("xpath=//a[text()='商家管理主题']");

        //点击选择主题的下拉框
        engine.click("xpath=//select[@name='pc']");

        //选择主题
        engine.click("xpath//select[@name='pc']/option[text()='iWebShop商家管理后台【蓝色】']");

        //点击保存
        engine.click("xpath=//button[@class='btn btn-primary']");

        engine.click("xpath=//button[text()='确定']");

        String value = engine.getValue("xpath=//select[@name='pc']");

        Assert.assertEquals(value , "{\"sysseller\":\"blue\"}");
    }


    /**
     * 修改支付方式开启状态
     */
    @Test
    public void testAlterPaymentState(){
        //打开网站后台
        engine.open(backUrl);
        //登录
        engine.type("name=admin_name" , "admin");
        engine.type("name=password" , "lengtong5655");
        engine.type("name=captcha" , "123456");
        engine.click("class=btn");

        //点击支付管理
        engine.click("xpath=//span[text()='支付管理']");
        //点击支付方式
        engine.click("xpath=//a[text()='支付方式']");

        //点击网银在线的修改图标
        engine.click("xpath=//a[@href='/index.php?controller=system&action=payment_edit&id=2']");

        //点击开启
        engine.click("xpath=//input[@name='status' and @value='0']");

        //点击保存
        engine.click("xpath=//button[@class='btn btn-primary']");

        String text = engine.getText("xpath=//table/tbody/tr[2]/td[4]/span[@class='text-green']");

        Assert.assertEquals(text , "使用中");
    }

    /**
     * 关闭支付方式开启状态
     */
    @Test
    public void testClosePaymentState(){
        //打开网站后台
        engine.open(backUrl);
        //登录
        engine.type("name=admin_name" , "admin");
        engine.type("name=password" , "lengtong5655");
        engine.type("name=captcha" , "123456");
        engine.click("class=btn");

        //点击支付管理
        engine.click("xpath=//span[text()='支付管理']");
        //点击支付方式
        engine.click("xpath=//a[text()='支付方式']");

        //点击网银在线的修改图标
        engine.click("xpath=//a[@href='/index.php?controller=system&action=payment_edit&id=2']");

        //点击关闭
        engine.click("xpath=//input[@name='status' and @value='1']");

        //点击保存
        engine.click("xpath=//button[@class='btn btn-primary']");

        String text = engine.getText("xpath=//table/tbody/tr[2]/td[4]/span[@class='text-red']");

        Assert.assertEquals(text , "已关闭");
    }


    /**
     * 修改支付方式名称
     */
    @Test
    public void testAlterPaymentName(){
        //打开网站后台
        engine.open(backUrl);
        //登录
        engine.type("name=admin_name" , "admin");
        engine.type("name=password" , "lengtong5655");
        engine.type("name=captcha" , "123456");
        engine.click("class=btn");

        //点击支付管理
        engine.click("xpath=//span[text()='支付管理']");
        //点击支付方式
        engine.click("xpath=//a[text()='支付方式']");

        //点击网银在线的修改图标
        engine.click("xpath=//a[@href='/index.php?controller=system&action=payment_edit&id=2']");

        //修改名称
        engine.clearAndType("xpath=//input[@name='name']" , "网银在线Test");

        engine.slideDownToBottom();

        //点击保存
        engine.click("xpath=//button[@class='btn btn-primary']");

        String text = engine.getText("xpath=//table[@class='table list-table']/tbody/tr[2]/td[2]");

        Assert.assertEquals(text , "网银在线Test");
    }


    /**
     * 开启oauth状态
     */
    @Test
    public void testAlterOauthState(){
        //打开网站后台
        engine.open(backUrl);
        //登录
        engine.type("name=admin_name" , "admin");
        engine.type("name=password" , "lengtong5655");
        engine.type("name=captcha" , "123456");
        engine.click("class=btn");

        //点击第三方平台
        engine.click("xpath=//span[text()='第三方平台']");
        //点击登录列表
        engine.click("xpath=//a[text()='oauth登录列表']");

        //点击QQ的修改图标
        engine.click("xpath=//a[@href='/index.php?controller=system&action=oauth_edit&id=2']");

        //点击开启
        engine.click("xpath=//input[@name='is_close' and @value='0']");


        //点击保存
        engine.click("xpath=//button[@class='btn btn-primary']");

        String text = engine.getText("xpath=//table/tbody/tr[2]/td[4]");

        Assert.assertEquals(text , "开启");
    }

    /**
     * 关闭oauth状态
     */
    @Test
    public void testCloseOauthState(){
        //打开网站后台
        engine.open(backUrl);
        //登录
        engine.type("name=admin_name" , "admin");
        engine.type("name=password" , "lengtong5655");
        engine.type("name=captcha" , "123456");
        engine.click("class=btn");

        //点击第三方平台
        engine.click("xpath=//span[text()='第三方平台']");
        //点击登录列表
        engine.click("xpath=//a[text()='oauth登录列表']");

        //点击QQ的修改图标
        engine.click("xpath=//a[@href='/index.php?controller=system&action=oauth_edit&id=2']");

        //点击开启
        engine.click("xpath=//input[@name='is_close' and @value='1']");


        //点击保存
        engine.click("xpath=//button[@class='btn btn-primary']");

        String text = engine.getText("xpath=//table/tbody/tr[2]/td[4]");

        Assert.assertEquals(text , "关闭");
    }

    /**
     * 测试申请链接
     */
    @Test
    public void testLink(){
        //打开网站后台
        engine.open(backUrl);
        //登录
        engine.type("name=admin_name" , "admin");
        engine.type("name=password" , "lengtong5655");
        engine.type("name=captcha" , "123456");
        engine.click("class=btn");

        //点击支付管理
        engine.click("xpath=//span[text()='支付管理']");
        //点击支付方式
        engine.click("xpath=//a[text()='支付方式']");

        //点击立即申请链接
        engine.click("xpath=//a[@href='http://www.chinabank.com.cn/']");

        String htmlSource = engine.getHtmlSource();

        Assert.assertTrue(!htmlSource.contains("404"));
    }

    /**
     * 修改上传容量
     */
    @Test
    public void testAlterUploadSize(){
        //打开网站后台
        engine.open(backUrl);
        //登录
        engine.type("name=admin_name" , "admin");
        engine.type("name=password" , "lengtong5655");
        engine.type("name=captcha" , "123456");
        engine.click("class=btn");

        //点击网站管理
        engine.click("xpath=//span[text()='网站管理']");
        //点击网站设置
        engine.click("xpath=//a[text()='网站设置']");
        //点击其他设置
        engine.click("xpath=//a[text()='系统设置']");

        //修改上传容量
        engine.clearAndType("xpath=//input[@name='uploadSize']" , "10");

        //点击其他设置
        engine.click("xpath=//a[text()='其他设置']");

        //获取容量
        String value = engine.getValue("xpath=//input[@name='uploadSize']");

        Assert.assertEquals(value , "10");

    }

    /**
     * 修改上传容量为大数
     */
    @Test
    public void testAlterUploadSizeToBigNumber(){
        //打开网站后台
        engine.open(backUrl);
        //登录
        engine.type("name=admin_name" , "admin");
        engine.type("name=password" , "lengtong5655");
        engine.type("name=captcha" , "123456");
        engine.click("class=btn");

        //点击网站管理
        engine.click("xpath=//span[text()='网站管理']");
        //点击网站设置
        engine.click("xpath=//a[text()='网站设置']");
        //点击其他设置
        engine.click("xpath=//a[text()='系统设置']");

        //修改上传容量
        engine.clearAndType("xpath=//input[@name='uploadSize']" , "10000000000000000000");

        //点击其他设置
        engine.click("xpath=//a[text()='其他设置']");

        //获取容量
        String value = engine.getValue("xpath=//input[@name='uploadSize']");

        Assert.assertEquals(value , "10000000000000000000");

    }

    /**
     * 将上传容量修改为负数
     */
    @Test
    public void testAlterUploadSizeToNegative(){
        //打开网站后台
        engine.open(backUrl);
        //登录
        engine.type("name=admin_name" , "admin");
        engine.type("name=password" , "lengtong5655");
        engine.type("name=captcha" , "123456");
        engine.click("class=btn");

        //点击网站管理
        engine.click("xpath=//span[text()='网站管理']");
        //点击网站设置
        engine.click("xpath=//a[text()='网站设置']");
        //点击其他设置
        engine.click("xpath=//a[text()='系统设置']");

        //修改上传容量
        engine.clearAndType("xpath=//input[@name='uploadSize']" , "-10");

        //点击其他设置
        engine.click("xpath=//a[text()='其他设置']");

        //获取容量
        String value = engine.getValue("xpath=//input[@name='uploadSize']");

        Assert.assertEquals(value , "-10");

    }

    /**
     * 修改邮箱，保存非邮箱内容
     */
    @Test
    public void testAlterEmailByErrorFormat(){
        //打开网站后台
        engine.open(backUrl);
        //登录
        engine.type("name=admin_name" , "admin");
        engine.type("name=password" , "lengtong5655");
        engine.type("name=captcha" , "123456");
        engine.click("class=btn");

        //点击网站管理
        engine.click("xpath=//span[text()='网站管理']");
        //点击网站设置
        engine.click("xpath=//a[text()='网站设置']");

        engine.clearAndType("xpath=//input[@name='email']" , "123456789");

        engine.slideDownToBottom();

        engine.click("xpath=//button[@class='btn btn-primary' and text()='保存基本设置']");

        //相当于刷新
        //点击网站设置
        engine.click("xpath=//a[text()='网站设置']");

        String value = engine.getValue("xpath=//input[@name='email']");

        Assert.assertTrue(!value.equals("123456"));

    }

    /**
     * 测试第三方平台申请链接是否能够打开
     */
    @Test
    public void testThirdPartLink(){
        //打开网站后台
        engine.open(backUrl);
        //登录
        engine.type("name=admin_name" , "admin");
        engine.type("name=password" , "lengtong5655");
        engine.type("name=captcha" , "123456");
        engine.click("class=btn");

        //点击第三方平台
        engine.click("xpath=//span[text()='第三方平台']");
        //点击登录列表
        engine.click("xpath=//a[text()='oauth登录列表']");

        String text = engine.getText("xpath=//table[@class='table list-table']/tbody/tr[2]/td[3]");

        String url = text.substring(text.indexOf("：") + 1);

        engine.open(url);

        String htmlSource = engine.getHtmlSource();

        Assert.assertTrue(!htmlSource.contains("404"));
    }



    /**
     * 测试第三方平台申请链接是否是正确地址
     */
    @Test
    public void testThirdPartLinkIsCorrect(){
        //打开网站后台
        engine.open(backUrl);
        //登录
        engine.type("name=admin_name" , "admin");
        engine.type("name=password" , "lengtong5655");
        engine.type("name=captcha" , "123456");
        engine.click("class=btn");

        //点击第三方平台
        engine.click("xpath=//span[text()='第三方平台']");
        //点击登录列表
        engine.click("xpath=//a[text()='oauth登录列表']");

        String text = engine.getText("xpath=//table[@class='table list-table']/tbody/tr[2]/td[3]");

        String url = text.substring(text.indexOf("：") + 1);

        engine.open(url);

        String title = engine.getTitle();

        Assert.assertTrue(title.contains("QQ"));
    }


    /**
     * 测试授权编号下方的链接
     */
    @Test
    public void testCodeLink(){
        //打开网站后台
        engine.open(backUrl);
        //登录
        engine.type("name=admin_name" , "admin");
        engine.type("name=password" , "lengtong5655");
        engine.type("name=captcha" , "123456");
        engine.click("class=btn");

        //点击网站管理
        engine.click("xpath=//span[text()='网站管理']");
        //点击网站设置
        engine.click("xpath=//a[text()='网站设置']");
        //点击系统设置
        engine.click("xpath=//a[text()='系统设置']");

        //点击链接
        engine.click("xpath=//a[text()='点击查询']");

        String htmlSource = engine.getHtmlSource();

        Assert.assertTrue(!htmlSource.contains("404"));

    }

    /**
     * 测试授权编号下方的链接
     */
    @Test
    public void testCodeLinkIsCorrect(){
        //打开网站后台
        engine.open(backUrl);
        //登录
        engine.type("name=admin_name" , "admin");
        engine.type("name=password" , "lengtong5655");
        engine.type("name=captcha" , "123456");
        engine.click("class=btn");

        //点击网站管理
        engine.click("xpath=//span[text()='网站管理']");
        //点击网站设置
        engine.click("xpath=//a[text()='网站设置']");
        //点击系统设置
        engine.click("xpath=//a[text()='系统设置']");

        //点击链接
        engine.click("xpath=//a[text()='点击查询']");

        String htmlSource = engine.getHtmlSource();

        Assert.assertTrue(!htmlSource.contains("404"));

    }

    /**
     * 测试价格表链接
     */
    @Test
    public void testPriceLink(){
        //打开网站后台
        engine.open(backUrl);
        //登录
        engine.type("name=admin_name" , "admin");
        engine.type("name=password" , "lengtong5655");
        engine.type("name=captcha" , "123456");
        engine.click("class=btn");

        //点击网站管理
        engine.click("xpath=//span[text()='网站管理']");
        //点击网站设置
        engine.click("xpath=//a[text()='网站设置']");
        //点击系统设置
        engine.click("xpath=//a[text()='系统设置']");

        //点击链接
        engine.click("xpath=//a[text()='点击价格表']");

        String htmlSource = engine.getHtmlSource();

        Assert.assertTrue(!htmlSource.contains("404"));

    }

    /**
     * 测试价格表链接是否能够到达正确页面
     */
    @Test
    public void testPriceLinkIsCorrect(){
        //打开网站后台
        engine.open(backUrl);
        //登录
        engine.type("name=admin_name" , "admin");
        engine.type("name=password" , "lengtong5655");
        engine.type("name=captcha" , "123456");
        engine.click("class=btn");

        //点击网站管理
        engine.click("xpath=//span[text()='网站管理']");
        //点击网站设置
        engine.click("xpath=//a[text()='网站设置']");
        //点击系统设置
        engine.click("xpath=//a[text()='系统设置']");

        //点击链接
        engine.click("xpath=//a[text()='点击价格表']");

        Assert.assertTrue(!engine.getTitle().contains("404"));

    }

    /**
     * 测试QQ输入框保存文本内容
     */
    @Test
    public void testSaveStringToQQ(){
        //打开网站后台
        engine.open(backUrl);
        //登录
        engine.type("name=admin_name" , "admin");
        engine.type("name=password" , "lengtong5655");
        engine.type("name=captcha" , "123456");
        engine.click("class=btn");

        //点击网站管理
        engine.click("xpath=//span[text()='网站管理']");
        //点击网站设置
        engine.click("xpath=//a[text()='网站设置']");

        engine.clearAndType("xpath=//input[@name='qq']" , "哈哈哈哈哈");

        //点击保存
        engine.click("xpath=//button[text()='保存基本设置']");
        //点击网站设置，相当于刷新
        engine.click("xpath=//a[text()='网站设置']");

        String value = engine.getValue("xpath=//input[@name='qq']");

        Assert.assertTrue(!value.equals("哈哈哈哈哈"));
    }





}
