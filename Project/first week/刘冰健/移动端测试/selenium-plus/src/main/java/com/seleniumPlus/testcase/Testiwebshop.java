package com.seleniumPlus.testcase;

import com.google.inject.internal.cglib.core.$VisibilityPredicate;
import com.seleniumPlus.core.BaseTest;
import com.sun.javafx.scene.traversal.TopMostTraversalEngine;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Testiwebshop extends BaseTest {

    private String url = "http://www.iwebshop.com:83/index.php?controller=ucenter&action=index";
    private String password = "200519";

    public void setPassword(String password) {
        this.password = password;
    }

    @BeforeClass
    public void Login(){
        engine.open(url);
        engine.type("name=login_info","admin");
        engine.type("name=password",password);
        engine.click("xpath=//input[@class='input_submit']");
    }

    //1.打开我的订单
    @Test
    public void test1(){
        engine.click("xpath=//a[@title='我的订单']");
        String text = engine.getText("xpath=//header[@class='uc_head']/h3");
        Assert.assertEquals("我的订单",text);
    }
    //2.打开我的积分
    @Test
    public void test2(){
        engine.click("xpath=//a[@title='我的积分']");
        String text = engine.getText("xpath=//header[@class='uc_head']/h3");
        Assert.assertEquals("个人积分",text);
    }
    //3.打开我的优惠券
    @Test
    public void test3(){
        engine.click("xpath=//a[@title='我的优惠券']");
        String text = engine.getText("xpath=//header[@class='uc_head']/h3");
        Assert.assertEquals("我的优惠券",text);
    }
    //4.打开站点建议
    @Test
    public void test4(){
        engine.click("xpath=//a[@title='站点建议']");
        String text = engine.getText("xpath=//header[@class='uc_head']/h3");
        Assert.assertEquals("我要建议",text);
    }
    //5.打开商品咨询
    @Test
    public void test5(){
        engine.click("xpath=//a[@title='商品咨询']");
        String text = engine.getText("xpath=//header[@class='uc_head']/h3");
        Assert.assertEquals("商品咨询",text);
    }
    //6.打开商品评价
    @Test
    public void test6(){
        engine.click("xpath=//a[@title='商品评价']");
        Assert.assertEquals(engine.isTextPresent("已评价"),true);
        Assert.assertEquals(engine.isTextPresent("未评价"),true);
    }
    //7.打开地址管理
    @Test
    public void test7(){
        engine.click("xpath=//a[@title='地址管理']");
        String text = engine.getText("xpath=//header[@class='uc_head']/h3");
        Assert.assertEquals("地址管理",text);
    }
    //8.打开个人资料
    @Test
    public void test8(){
        engine.click("xpath=//a[@title='个人资料']");
        String text = engine.getText("xpath=//header[@class='uc_head']/h3");
        Assert.assertEquals("个人资料",text);
    }
    //9.打开修改密码
    @Test
    public void test9(){
        engine.click("xpath=//a[@title='修改密码']");
        String text = engine.getText("xpath=//header[@class='uc_head']/h3");
        Assert.assertEquals("密码管理",text);
    }
    //10.打开发票管理
    @Test
    public void test10(){
        engine.click("xpath=//a[@title='发票管理']");
        String text = engine.getText("xpath=//header[@class='uc_head']/h3");
        Assert.assertEquals("发票管理",text);
    }
    //11.打开账户余额
    @Test
    public void test11(){
        engine.click("xpath=//a[@title='帐户余额']");
        Assert.assertEquals(engine.isTextPresent("交易记录"),true);
        Assert.assertEquals(engine.isTextPresent("提现申请"),true);
    }
    //12.地址管理添加新地址
        //输入格式不正确的信息
    @Test
    public void test12(){
        engine.click("xpath=//a[@title='地址管理']");
        engine.click("class=new_address_btn");
        engine.enterFrame("OpenaddressWindow");
        engine.type("name=accept_name","123");
        engine.selectByIndex("name=province",1);
        engine.selectByIndex("name=city",1);
        engine.selectByIndex("name=area",1);
        engine.type("name=address","123");
        engine.type("name=mobile","123");
        engine.type("name=telphone","123");
        engine.type("name=zip","123");
        engine.pause(1000);
        engine.leaveFrame();
        engine.click("xpath=//button[@text='提交']");
        Assert.assertEquals(engine.isTextPresent("请正确填写各项信息"),true);
    }
    //13.地址管理添加新地址
        //输入格式正确的信息
    @Test
    public void test13(){
        engine.click("xpath=//a[@title='地址管理']");
        engine.click("class=new_address_btn");
        engine.enterFrame("OpenaddressWindow");
        engine.type("name=accept_name","刘冰健");
        engine.selectByIndex("name=province",1);
        engine.selectByIndex("name=city",1);
        engine.selectByIndex("name=area",1);
        engine.type("name=address","河北师范大学");
        engine.type("name=mobile","18031527777");
        engine.type("name=telphone","123");
        engine.type("name=zip","100000");
        engine.pause(1000);
        engine.leaveFrame();
        engine.click("xpath=//button[@text='提交']");
        Assert.assertEquals(engine.isTextPresent("刘冰健"),true);
    }
    //14.地址管理修改信息
        //修改省市区信息
    @Test
    public void test14(){
        engine.click("xpath=//a[@title='地址管理']");
        engine.click("xpath=//td[@class='uc_tab_operation current'][1]/a[1]");
        engine.enterFrame("OpenaddressWindow");
        engine.selectByValue("name=province","海南省");
        engine.selectByValue("name=city","三亚市");
        engine.selectByValue("name=area","市辖区");
        engine.pause(1000);
        engine.leaveFrame();
        engine.click("xpath=//button[@text='提交']");
        Assert.assertEquals(engine.isTextPresent("海南省三亚市市辖区"),true);
    }
    //15.地址管理修改信息
        //修改收货人地址手机邮编
    @Test
    public void test15(){
        engine.click("xpath=//a[@title='地址管理']");
        engine.click("xpath=//td[@class='uc_tab_operation current'][1]/a[1]");
        engine.enterFrame("OpenaddressWindow");
        engine.clearAndType("name=accept_name","修改后刘冰健");
        engine.clearAndType("name=address","修改后河北师范大学");
        engine.clearAndType("name=mobile","18031528888");
        engine.clearAndType("name=telphone","13579");
        engine.clearAndType("name=zip","200000");
        Assert.assertEquals(engine.isTextPresent("修改后刘冰健"),true);
        Assert.assertEquals(engine.isTextPresent("修改后河北师范大学"),true);
        Assert.assertEquals(engine.isTextPresent("18031528888"),true);
        Assert.assertEquals(engine.isTextPresent("13579"),true);
        Assert.assertEquals(engine.isTextPresent("200000"),true);
    }
    //16.地址管理删除地址
    @Test
    public void test16(){
        engine.click("xpath=//a[@title='地址管理']");
        engine.click("xpath=//td[@class='uc_tab_operation current'][1]/a[2]");
        engine.click("xpath=//button[@class=' aui_state_highlight']");
        Assert.assertEquals(engine.isElementPresent("xpath=//td[@class='uc_tab_operation current'][1]/a[2]"),false);
    }
    //17.地址管理设为默认
    @Test
    public void test17(){
        engine.click("xpath=//a[@title='地址管理']");
        if(engine.getText("xpath=//td[@class='uc_tab_operation current'][1]/a[3]").equals("取消默认")) {
            engine.click("xpath=//td[@class='uc_tab_operation current'][1]/a[3]");
            engine.click("xpath=//td[@class='uc_tab_operation current'][1]/a[3]");
        }else {
            engine.click("xpath=//td[@class='uc_tab_operation current'][1]/a[3]");
        }
        Assert.assertEquals(engine.getText("xpath=//td[@class='uc_tab_operation current'][1]/a[3]"),"取消默认");
    }
    //18.个人中心发票管理
        //单位名称为test01其余各项信息均填写为123，类型为普通发票
    @Test
    public void test18(){
        engine.click("xpath=//a[@title='发票管理']");
        engine.click("class=new_address_btn");
        engine.enterFrame("OpentaxWindow");
        engine.type("name=company_name","test01");
        engine.type("name=taxcode","123");
        engine.type("name=address","123");
        engine.type("name=telphone","123");
        engine.type("name=bankname","123");
        engine.type("name=bankno","123");
        engine.selectByIndex("xpath=//select[@name='type']",0);
        engine.leaveFrame();
        engine.pause(1000);
        engine.click("xpath=//button[@class=' aui_state_highlight']");
        Assert.assertEquals(engine.isTextPresent("test01"),true);
    }
    //19.个人中心发票管理
        //修改单位名称,发票类型
    @Test
    public void test19(){
        engine.click("xpath=//a[@title='发票管理']");
        engine.click("xpath=//td[@class='uc_tab_operation'][1]/a[1]");
        engine.enterFrame("OpentaxWindow");
        engine.clearAndType("name=company_name","修改后test01");
        if (engine.getText("xpath=//select[@name='type']").equals("增值税专用发票")){
            engine.selectByVisibleText("xpath=//select[@name='type']","普通发票");
        }else{
            engine.selectByVisibleText("xpath=//select[@name='type']","增值税专用发票");
        }
        engine.leaveFrame();
        engine.pause(1000);
        engine.click("xpath=//button[@class=' aui_state_highlight']");
        Assert.assertEquals(engine.isTextPresent("修改后test01"),true);
    }
    //20.个人中心发票管理
        //删除发票
    @Test
    public void test20(){
        engine.click("xpath=//a[@title='发票管理']");
        engine.click("xpath=//td[@class='uc_tab_operation'][1]/a[2]");
        engine.click("xpath=//button[@class=' aui_state_highlight']");
        Assert.assertEquals(engine.isElementPresent("xpath=//td[@class='uc_tab_operation current'][1]/a[2]"),false);
    }
    //21.个人中心修改密码
    @Test
    public void test21(){
        String newPassword = "123456";
        engine.type("name=fpassword","200519");
        engine.type("name=password",newPassword);
        engine.type("name=repassword",newPassword);
        engine.click("xpath=//input[@class='input_submit']");
        Assert.assertEquals(engine.isTextPresent("密码修改成功"),true);
        setPassword(newPassword);
    }
    //22.个人中心修改密码
        //在原有密码中输入和原有密码不同的密码
    @Test
    public void test22(){
        engine.click("xpath=//a[@title='修改密码']");
        engine.type("name=fpassword","asdc");
        engine.type("name=password","123456");
        engine.type("name=repassword","123456");
        engine.click("xpath=//input[@class='input_submit']");
        Assert.assertEquals(engine.isTextPresent("原始密码输入错误"),true);
    }
    //23.个人中心修改密码
        //输入的确认密码和设置密码不同
    @Test
    public void test23(){
        engine.click("xpath=//a[@title='修改密码']");
        engine.type("name=fpassword","123456");
        engine.type("name=password","123456");
        engine.type("name=repassword","123456");
        Assert.assertEquals(engine.isTextPresent("密码修改失败"),true);
    }
    //24.个人中心个人资料
        //不填写固定电话
    @Test
    public void test24(){
        engine.click("xpath=//a[@title='个人资料']");
        engine.clearAndType("name=true_name","123");
        engine.click("xpath=//input[@value='1']");
        engine.clearAndType("xpath=//input[@name='birthday']", "2015-01-01");
        engine.selectByIndex("name=province",1);
        engine.selectByIndex("name=city",1);
        engine.selectByIndex("name=area",1);
        engine.clearAndType("name=contact_addr","123");
        engine.clearAndType("name=mobile","13712312311");
        engine.clearAndType("name=email","123@123.com");
        engine.clearAndType("name=zip","123123");
        engine.clearAndType("name=qq","1231231231");
        engine.click("class=input_submit");
        Assert.assertEquals(engine.isChecked("xpath=//input[@value='1']"),true);
    }
    //25.修改个人资料
        //将信息填写为正确格式,性别为女
    @Test
    public void test25(){
        engine.click("xpath=//a[@title='个人资料']");
        engine.clearAndType("name=true_name","123");
        engine.click("xpath=//input[@value='2']");
        engine.clearAndType("xpath=//input[@name='birthday']", "2015-01-01");
        engine.selectByIndex("name=province",1);
        engine.selectByIndex("name=city",1);
        engine.selectByIndex("name=area",1);
        engine.clearAndType("name=contact_addr","123");
        engine.clearAndType("name=mobile","13712312311");
        engine.clearAndType("name=email","123@123.com");
        engine.clearAndType("name=zip","123123");
        engine.clearAndType("name=telephone","123");
        engine.clearAndType("name=qq","1231231231");
        engine.click("class=input_submit");
        Assert.assertEquals(engine.isChecked("xpath=//input[@value='2']"),true);
    }
    //26.修改个人资料
        ////出生日期填写为今天，性别为男，所在省市区选择多选框第一项,其余填写为123
    @Test
    public void test26(){
        engine.click("xpath=//a[@title='个人资料']");
        engine.clearAndType("name=true_name","123");
        engine.click("xpath=//input[@value='1']");
        engine.type("xpath=//input[@name='birthday']",new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        engine.selectByIndex("name=province",1);
        engine.selectByIndex("name=city",1);
        engine.selectByIndex("name=area",1);
        engine.clearAndType("name=contact_addr","123");
        engine.clearAndType("name=mobile","123");
        engine.clearAndType("name=email","123");
        engine.clearAndType("name=zip","123");
        engine.clearAndType("name=telephone","123");
        engine.clearAndType("name=qq","123");
        engine.click("class=input_submit");
        engine.refresh();
        Assert.assertEquals(engine.isChecked("xpath=//input[@value='2']"),true);
    }
    //27.个人中心站点建议
        //只输入建议内容点击提交
    @Test
    public void test27(){
        engine.click("xpath=//a[@title='站点建议']");
        engine.clearAndType("name=content","这是建议内容");
        engine.click("class=input_submit");
        engine.click("xpath=//a[@class='blue'][1]");
        Assert.assertEquals(engine.isTextPresent("这是一条建议内容"),false);
    }
    //28.个人中心站点建议
        //只输入建议标题
    @Test
    public void test28(){
        engine.click("xpath=//a[@title='站点建议']");
        engine.clearAndType("name=title","这是一个建议标题");
        engine.click("class=input_submit");
        engine.click("xpath=//a[@class='blue'][1]");
        Assert.assertEquals(engine.getText("class=aui_title"),"这是一个建议标题");
    }
    //29.个人中心站点建议
        //输入标题和内容点击提交
    @Test
    public void test29(){
        engine.click("xpath=//a[@title='站点建议']");
        engine.clearAndType("name=title","这是一个建议标题");
        engine.clearAndType("name=content","这是一条建议内容");
        engine.click("class=input_submit");
        engine.click("xpath=//a[@class='blue'][1]");
        Assert.assertEquals(engine.isTextPresent("这是一条建议内容"),true);
        Assert.assertEquals(engine.getText("class=aui_title"),"这是一个建议标题");
    }
    //30.个人中心站点建议
        //循环添加十条建议并查看建议内容是否与提交一致
    @Test
    public void test30(){
        engine.click("xpath=//a[@title='站点建议']");
        for (int i = 1; i <= 10; i++){
            engine.clearAndType("name=title","这是第"+i+"条建议标题");
            engine.clearAndType("name=content","这是第"+i+"条建议内容");
            engine.click("class=input_submit");
            engine.click("xpath=//a[@class='blue'][1]");
            Assert.assertEquals(engine.isTextPresent("这是第"+i+"条建议内容"),true);
        }

    }
    //31.个人中心账户余额提现申请
        //输入收款人姓名，提现金额，备注
    @Test
    public void test31(){
        engine.click("xpath=//a[@title='帐户余额']");
        engine.click("xpath=//a[contains(text(),'提现申请')]");
        engine.type("name=name","第31条测试用例收款人姓名");
        engine.type("name=amount","10");
        engine.type("name=note","第31条测试用例备注");
        engine.click("class=input_submit");
        Assert.assertEquals(engine.getText("xpath=//section[@class='uc_table']/table/tbody/tr[1]/td[1]"),"第31条测试用例备注");
    }
    //32.个人中心账户余额提现申请
        //取消一条提现申请
    @Test
    public void test32(){
        engine.click("xpath=//a[@title='帐户余额']");
        engine.click("xpath=//a[contains(text(),'提现申请')]");
        engine.click("xpath=//a[contains(text(),'取消')][1]");
        engine.click("xpath=//button[@class=' aui_state_highlight']");
    }
    //33.个人中心账户余额提现申请
        //循环添加十条提现申请
    @Test
    public void test33(){
        engine.click("xpath=//a[@title='帐户余额']");
        engine.click("xpath=//a[contains(text(),'提现申请')]");
        for (int i = 1; i <= 10; i++){
            engine.type("name=name","第31条测试用例收款人姓名"+i);
            engine.type("name=amount","10");
            engine.type("name=note","第31条测试用例备注"+i);
            engine.click("class=input_submit");
        }
        Assert.assertEquals(engine.getText("xpath=//section[@class='uc_table']/table/tbody/tr[1]/td[1]"),"第31条测试用例备注10");
    }
    //34.个人中心账户余额提现申请
        //获取账户余额，查看提现成功后账户是否减少了对应的提现金额
    @Test
    public void test34(){
        engine.click("xpath=//a[@title='帐户余额']");
        String currentMoney = engine.getText("xpath=//span[@class='money']/em");
        engine.click("xpath=//ul[@class='nav']/li[3]/a");
        engine.type("name=admin_name","admin");
        engine.type("name=password","12345678");
        engine.type("name=captcha","123");
        engine.click("xpath=//button[@class='btn btn-lg btn-warning btn-block']");
        engine.click("xpath=//a[contains(text(),'会员')]");
        engine.click("xpath=//a[contains(text(),'会员提现管理')]");
        engine.click("xpath=//tbody/tr[1]/td[8]/a[1]");
        String withDraw = engine.getText("xpath=//tbody/tr[5]/td");
        engine.pause(1000);
        engine.click("xpath=//input[@value='2']");
        engine.click("xpath=//button[@class='btn btn-primary']");

        engine.open(url);
        engine.click("xpath=//a[@title='帐户余额']");
        String money = engine.getText("xpath=//span[@class='money']/em");
        currentMoney = currentMoney.substring(1,currentMoney.length()-3);
        money = money.substring(1,money.length()-3);
        withDraw = withDraw.substring(0,withDraw.length()-3);
        Assert.assertEquals(Integer.parseInt(currentMoney)-Integer.parseInt(withDraw),Integer.parseInt(money));
    }
    //35.个人中心我的订单
        //获取当前余额，之后选择商品购买付款，获取购买商品后的余额，看是否扣除了已完成订单的价格
    @Test
    public void test35(){
        engine.click("xpath=//a[@title='帐户余额']");
        String currentMoney = engine.getText("xpath=//span[@class='money']/em");
        currentMoney = currentMoney.substring(1,currentMoney.length()-3);
        engine.click("xpath=//ul[@class='nav']/li[1]/a");
        engine.click("xpath=//a[@class='home_pul_box_shadow'][1]");
        engine.click("id=buyNowButton");
        String price = engine.getText("id=final_sum");
        price = price.substring(0,price.length()-3);
        engine.click("class=cart_topay_btn");
        engine.click("class=gobank_pay_btn");
        engine.switchWidow(0);
        engine.click("xpath=//button[@class=' aui_state_highlight']");
        engine.click("xpath=//a[@title='帐户余额']");
        String money = engine.getText("xpath=//span[@class='money']/em");
        money = money.substring(1,money.length()-3);
        Assert.assertEquals(Integer.parseInt(currentMoney)-Integer.parseInt(price),Integer.parseInt(money));
    }
    //36.个人中心我的订单
        //点击要退款的订单编号，点击取消订单，点击售后服务，查看退款订单状态是否为申请中
    @Test
    public void test36(){
        String orderId = "";
        engine.click("xpath=//a[@title='帐户余额']");
        String currentMoney = engine.getText("xpath=//span[@class='money']/em");
        currentMoney = currentMoney.substring(1,currentMoney.length()-3);
        engine.click("xpath=//a[@title='我的订单']");
        for (int i = 1; i <= 20; i++){
            if (engine.getText("xpath=//tbody/tr["+i+"]/td[6]").equals("等待发货")){
                orderId = engine.getText("xpath=//tbody/tr["+i+"]/td[1]/a");
                engine.click("xpath=//tbody/tr["+i+"]/td[1]/a");
                break;
            }
        }
        String price = engine.getText("xpath=//span[@class='red']/label");
        price = price.substring(0,price.length()-3);
        engine.click("xpath=//input[@value='取消订单']");
        engine.click("xpath=//ul[@class='nav']/li[3]/a");
        engine.type("name=admin_name","admin");
        engine.type("name=password","12345678");
        engine.type("name=captcha","123");
        engine.click("xpath=//button[@class='btn btn-lg btn-warning btn-block']");
        engine.click("xpath=//a[contains(text(),'订单')]");
        engine.click("xpath=//a[contains(text(),'退款申请列表')]");

        for (int i = 1; i <= 20; i++){
            if (engine.getText("xpath=//tbody/tr["+i+"]/td[2]").equals(orderId)){
                engine.click("xpath=//tbody/tr["+i+"]/td[6]/a");
                break;
            }
        }
        engine.click("xpath=//button[@type='submit']");
        engine.click("xpath=//button[contains(text(),'退款')]");

        engine.open(url);
        engine.click("xpath=//a[@title='帐户余额']");
        String money = engine.getText("xpath=//span[@class='money']/em");
        money = money.substring(1,money.length()-3);
        Assert.assertEquals(Integer.parseInt(currentMoney)+Integer.parseInt(price),Integer.parseInt(money));
    }
    //37.个人中心我的订单
        //取消申请退款
    @Test
    public void test37(){
        String orderId = "";
        engine.click("xpath=//a[@title='售后服务']");
        for (int i = 1; i <= 20; i++){
            if (engine.getText("xpath=//tbody/tr["+i+"]/td[3]").equals("申请中")){
                orderId = engine.getText("xpath=//tbody/tr["+i+"]/td[1]");
                engine.click("xpath=//tbody/tr["+i+"]/td[4]/a[1]");
                break;
            }
        }
        engine.click("xpath=//button[@class=' aui_state_highlight']");
        Assert.assertEquals(engine.isTextPresent(orderId),false);
    }
    //38.个人中心我的订单
        //选择订单状态为等待付款的订单，点击立即付款，查看订单状态是否更新为等待发货
    @Test
    public void test38(){
        String orderId;
        engine.click("xpath=//a[@title='我的订单']");
        for (int i = 1; i <= 20; i++){
            if (engine.getText("xpath=//tbody/tr["+i+"]/td[6]").equals("等待付款")){
                orderId = engine.getText("xpath=//tbody/tr["+i+"]/td[1]/a");
                engine.click("xpath=//tbody/tr["+i+"]/td[1]/a");
                break;
            }
        }
        engine.click("xpath=//input[@value='立即付款']");
        engine.enterFrame(0);
        engine.click("xpath=//input[@value='立即支付']");
        engine.switchWidow(0);
        engine.click("xpath=//button[@class=' aui_state_highlight']");
        engine.refresh();
        Assert.assertEquals(engine.getText("xpath=//span[@class='green bold']"),"等待发货");

    }
    //39.个人中心我的订单
        //选择订单状态为等待付款的订单，点击取消订单，查看订单状态是否更新为已取消
    @Test
    public void test39(){
        String orderId = "";
        engine.click("xpath=//a[@title='我的订单']");
        for (int i = 1; i <= 20; i++){
            if (engine.getText("xpath=//tbody/tr["+i+"]/td[6]").equals("等待付款")){
                orderId = engine.getText("xpath=//tbody/tr["+i+"]/td[1]/a");
                engine.click("xpath=//tbody/tr["+i+"]/td[1]/a");
                break;
            }
        }
        engine.click("xpath=//input[@value='取消订单']");
        Assert.assertEquals(engine.getText("xpath=//span[@class='green bold']"),"已取消");
    }
    //40.个人中心我的订单
        //选择订单状态为已发货的订单，点击确认收货，查看订单状态是否更新为已完成
    @Test
    public void test40(){
        String orderId = "";
        engine.click("xpath=//a[@title='我的订单']");
        for (int i = 1; i <= 20; i++){
            if (engine.getText("xpath=//tbody/tr["+i+"]/td[6]").equals("已发货")){
                orderId = engine.getText("xpath=//tbody/tr["+i+"]/td[1]/a");
                engine.click("xpath=//tbody/tr["+i+"]/td[1]/a");
                break;
            }
        }
        engine.click("xpath=//input[@value='确认收货']");
        engine.click("xpath=//a[@title='我的订单']");
        for (int i = 1; i <= 20; i++){
            if (engine.getText("xpath=//tbody/tr["+i+"]/td[2]").equals(orderId)){
                Assert.assertEquals(engine.getText("xpath=//tbody/tr["+i+"]/td[6]"),"已完成");
                break;
            }
        }
    }
    //41.个人中心我的订单
        //获取未评价订单的数量，查看是否和个人中心中待评价商品的数量一致
    @Test
    public void test41(){
        String num = engine.getText("xpath=//ol[@class='user_stat'][2]/li[1]/strong");
        engine.click("xpath=//a[@title='商品评价']");
        Assert.assertEquals(engine.getNum("xpath=//section[@class='uc_table']/table/tbody/tr"),Integer.parseInt(num));
    }
    //42.个人中心我的订单
        //点击要等待发货的订单，点击取消订单，点击售后服务，查看退款订单状态是否为申请中
    @Test
    public void test42(){
        String orderId = "";
        engine.click("xpath=//a[@title='我的订单']");
        for (int i = 1; i <= 20; i++){
            if (engine.getText("xpath=//tbody/tr["+i+"]/td[6]").equals("等待发货")||
                    engine.getText("xpath=//tbody/tr["+i+"]/td[6]").equals("等待付款")){
                orderId = engine.getText("xpath=//tbody/tr["+i+"]/td[1]/a");
                engine.click("xpath=//tbody/tr["+i+"]/td[1]/a");
                break;
            }
        }
        engine.click("xpath=//input[@value='取消订单']");
        engine.click("xpath=//a[@title='售后服务']");
        Assert.assertEquals(engine.getText("xpath=//section[@class='uc_table']/table/tbody/tr[1]/td[3]"),"申请中");
    }
    //43.个人中心售后服务
        //点击查看退款订单，看里面的申请时间是否一致
    @Test
    public void test43(){
        engine.click("xpath=//a[@title='售后服务']");
        String str = engine.getText("xpath=//section[@class='uc_table']/table/tbody/tr[1]/td[2]");
        engine.click("xpath=//tbody/tr[1]/td[4]/a[2]");
        String id = engine.getText("xpath=//tbody/tr[4]/td");
        Assert.assertEquals(str,id);
    }
    //44.个人中心商品评价
        //商品评价，选择一个商品点击发表评价，选择评论等级五颗星，评论内容为123，点击发表评价
    @Test
    public void test44(){
        engine.click("xpath=//a[@title='商品评价']");
        engine.click("xpath=//a[@class='orange bold'][1]");
        engine.type("class=input_textarea","123");
        engine.click("xpath=//input[@class='input_submit']");
    }
    //45.个人中心商品评价
        //选择一个商品点击发表评价，选择评论等级五颗星，不填写评论内容，点击我要评价
    @Test
    public void test45(){
        engine.click("xpath=//a[@title='商品评价']");
        engine.click("xpath=//a[@class='orange bold'][1]");
        engine.click("xpath=//input[@class='input_submit']");
        engine.refresh();
        Assert.assertEquals(engine.isElementPresent("xpath=//input[@class='input_submit']"),true);
    }
    //46.个人中心商品评价
    //选择一个商品点击发表评价，选择评论等级五颗星，评论内容为png，上传png格式图片
    @Test
    public void test46(){
        engine.click("xpath=//a[@title='商品评价']");
        engine.click("xpath=//a[@class='orange bold'][1]");
        engine.type("class=input_textarea","png");
        engine.type("id=fileUpload","C:\\Users\\14214\\Desktop\\picture\\fuji.png");
        engine.pause(1000);
        engine.click("xpath=//input[@class='input_submit']");
    }
    //47.个人中心商品评价
    //选择一个商品点击发表评价，选择评论等级五颗星，评论内容为jpg，上传jpg格式图片
    @Test
    public void test47(){
        engine.click("xpath=//a[@title='商品评价']");
        engine.click("xpath=//a[@class='orange bold'][1]");
        engine.type("class=input_textarea","jpg");
        engine.type("id=fileUpload","C:\\Users\\14214\\Desktop\\picture\\fuji.png");
        engine.pause(1000);
        engine.click("xpath=//input[@class='input_submit']");
    }
    //48.个人中心商品评价
        //选择一个商品点击发表评价，上传图片，点击图片下方的删除，查看图片是否被删除
    @Test
    public void test48(){
        engine.click("xpath=//a[@title='商品评价']");
        engine.click("xpath=//a[@class='orange bold'][1]");
        engine.type("class=input_textarea","png");
        engine.type("id=fileUpload","C:\\Users\\14214\\Desktop\\picture\\fuji.png");
        engine.pause(1000);
        engine.click("xpath=//a[contains(text(),'删除')]");
        Assert.assertEquals(engine.isElementPresent("xpath=//a[contains(text(),'删除')]"),false);
    }
    //49.个人中心商品评价
        //选择一个未评价商品获取订单编号并点击发表评价，选择评论等级五颗星，评论内容为123，点击我要评价，
        //然后返回个人中心商品评价页面，查看商品订单编号是否从未评价中删除并且在已评价中出现
    @Test
    public void test49(){
        engine.click("xpath=//a[@title='商品评价']");
        String orderId = engine.getText("xpath=//section[@class='uc_table']/table/tbody/tr[1]/td[1]");
        engine.click("xpath=//a[@class='orange bold'][1]");
        engine.type("class=input_textarea","123");
        engine.click("xpath=//input[@class='input_submit']");
        engine.open(url);
        engine.click("xpath=//a[@title='商品评价']");
        Assert.assertEquals(engine.isTextPresent(orderId),false);
        engine.click("xpath=//a[contains(text(),'已评价')]");
        Assert.assertEquals(engine.isTextPresent(orderId),true);
    }

    //50.个人中心商品评价
        //选择一个未评价商品获取订单编号并点击发表评价，任意选择评论等级，评论内容为123，点击我要评价，
        //返回个人中心商品评价页面，查看已评价商品评分是否和自己选择的评论等级相同
    @Test
    public void test50(){
        engine.click("xpath=//a[@title='商品评价']");
        String orderId = engine.getText("xpath=//section[@class='uc_table']/table/tbody/tr[1]/td[1]");
        engine.click("xpath=//a[@class='orange bold'][1]");
        engine.click("xpath=//input[@value='2']");
        engine.type("class=input_textarea","123");
        engine.click("xpath=//input[@class='input_submit']");
        engine.open(url);
        engine.click("xpath=//a[@title='商品评价']");
        engine.click("xpath=//a[contains(text(),'已评价')]");
        for (int i = 1; i <= 20; i++){
            if (engine.getText("xpath=//section[@class='uc_table']/table/tbody/tr["+i+"]/td[2]").equals(orderId)){
                Assert.assertEquals(engine.getText("xpath=//section[@class='uc_table']/table/tbody/tr["+i+"]/td[4]"),"2");
                break;
            }
        }
    }
}