package com.seleniumPlus.testcase;

import com.seleniumPlus.core.BaseTest;
import com.seleniumPlus.dataprovider.NSDataProvider;
import org.apache.xmlbeans.impl.xb.xmlconfig.Nsconfig;
import org.checkerframework.checker.units.qual.A;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestDataProvider extends BaseTest {

    private String url = "http://www.iwebshop.com:83/index.php?controller=ucenter&action=index";
    private String password = "200519";
    private String username = "test12";

    public void setPassword(String password) {
        this.password = password;
    }

    @BeforeClass
    public void Login(){
        engine.open(url);
        engine.type("name=login_info",username);
        engine.type("name=password",password);
        engine.click("xpath=//input[@class='input_submit']");
    }

    //1.数据驱动测试个人中心站点建议功能
        //两个提交失败数据，十个提交成功数据
    @Test(dataProvider="addAdvice", dataProviderClass = NSDataProvider.class)
    public void testAddAdvice(String title, String advice) {
        engine.click("xpath=//a[@title='站点建议']");

        engine.clearAndType("name=title",title);
        engine.clearAndType("name=content",advice);
        engine.click("class=input_submit");
        engine.click("xpath=//a[@class='blue'][1]");
        Assert.assertEquals(engine.getText("xpath=//div[@class='aui_content']/p[1]"),advice);
        Assert.assertEquals(engine.getText("class=aui_title"),title);
    }

    //2.数据驱动测试个人中心提现申请功能
        //成功数据六条，失败数据五条
    @Test(dataProvider="Withdraw", dataProviderClass = NSDataProvider.class)
    public void testWithdraw(String name,String amount,String note){
        engine.click("xpath=//a[@title='帐户余额']");
        if(engine.getText("xpath=//span[@class='money']/em").equals("￥0.00")){
            engine.click("xpath=//ul[@class='nav']/li[3]/a");
            engine.type("name=admin_name","admin");
            engine.type("name=password","12345678");
            engine.type("name=captcha","123");
            engine.click("xpath=//button[@class='btn btn-lg btn-warning btn-block']");
            engine.click("xpath=//ul[@name='topMenu']/li[2]");
            for (int i = 1; i < 10;i++){
                if (engine.getText("xpath=//table[@class='table list-table']/tbody/tr["+i+"]/td[2]").equals(username)){
                    engine.click("xpath=//table[@class='table list-table']/tbody/tr["+i+"]/td[1]/input");
                    engine.click("xpath=//a[@class='btn btn-default'][5]");
                    engine.enterFrame(0);
                    engine.selectByIndex("name=type",1);
                    engine.type("name=balance","10000000");
                    engine.leaveFrame();
                    engine.click("xpath=//button[contains(text(),'确定')]");
                    break;
                }
            }
        }
        engine.open(url);
        engine.click("xpath=//a[@title='帐户余额']");
        engine.click("xpath=//a[contains(text(),'提现申请')]");
        engine.type("name=name",name);
        engine.type("name=amount",amount);
        engine.type("name=note",note);
        engine.click("class=input_submit");
        Assert.assertEquals(engine.getText("xpath=//section[@class='uc_table']/table/tbody/tr[1]/td[1]"),note);
    }

    //3.数据驱动测试个人中心添加新地址功能
        //失败数据五条
    @Test(dataProvider="AddAddressFail", dataProviderClass = NSDataProvider.class)
    public void testAddAddressFail(String name,String province,String city,String area,String address,String mobile,String telphone,String zip){
        engine.click("xpath=//a[@title='地址管理']");
        engine.click("class=new_address_btn");
        engine.enterFrame("OpenaddressWindow");
        engine.type("name=accept_name",name);
        engine.selectByIndex("name=province",Integer.parseInt(province));
        engine.selectByIndex("name=city",Integer.parseInt(city));
        engine.selectByIndex("name=area",Integer.parseInt(area));
        engine.type("name=address",address);
        engine.type("name=mobile",mobile);
        engine.type("name=telphone",telphone);
        engine.type("name=zip",zip);
        engine.pause(1000);
        engine.leaveFrame();
        engine.click("xpath=//button[@class=' aui_state_highlight']");
        Assert.assertTrue(engine.isTextPresent("请正确填写各项信息"));
        engine.click("xpath=//button[contains(text(),'确定')]");
    }
    //4.数据驱动测试个人中心添加新地址功能
        //成功数据三条
    @Test(dataProvider="addAddressSuccess", dataProviderClass = NSDataProvider.class)
    public void testAddAddressSuccess(String name,String province,String city,String area,String address,String mobile,String telphone,String zip){
        engine.click("xpath=//a[@title='地址管理']");
        engine.click("class=new_address_btn");
        engine.enterFrame("OpenaddressWindow");
        engine.type("name=accept_name",name);
        engine.selectByIndex("name=province",Integer.parseInt(province));
        engine.selectByIndex("name=city",Integer.parseInt(city));
        engine.selectByIndex("name=area",Integer.parseInt(area));
        engine.type("name=address",address);
        engine.type("name=mobile",mobile);
        engine.type("name=telphone",telphone);
        engine.type("name=zip",zip);
        engine.pause(1000);
        engine.leaveFrame();
        engine.click("xpath=//button[@class=' aui_state_highlight']");
        Assert.assertTrue(engine.isTextPresent(name));
    }

    //5.数据驱动测试个人中心发票管理功能
        //失败用例9条
    int i = 0;
    @Test(dataProvider = "addReceiptFail",dataProviderClass = NSDataProvider.class)
    public void testAddReceiptFail(String name,String taxcode,String address,String telphone,String bankname,String bankno,String type){
        engine.click("xpath=//a[@title='发票管理']");
        engine.click("class=new_address_btn");
        engine.enterFrame("OpentaxWindow");
        engine.type("name=company_name",name);
        engine.type("name=taxcode",taxcode);
        engine.type("name=address",address);
        engine.type("name=telphone",telphone);
        engine.type("name=bankname",bankname);
        engine.type("name=bankno",bankno);
        engine.selectByIndex("xpath=//select[@name='type']",Integer.parseInt(type));
        engine.leaveFrame();
        engine.pause(1000);
        engine.click("xpath=//button[@class=' aui_state_highlight']");
        if (i<5){
            Assert.assertEquals(engine.getText("xpath=//div[@class='aui_content']"),"请正确填写各项信息");
        }else{
            Assert.assertEquals(engine.getText("xpath=//div[@class='aui_content']"),"请完整填写收件信息");
        }
        System.out.println(engine.getText("xpath=//div[@class='aui_content']"));
        engine.click("xpath=//button[contains(text(),'确定')]");
        i++;
    }
    //6.数据驱动测试个人中心发票管理功能
        //成功用例10条
    @Test(dataProvider = "addReceiptSuccess",dataProviderClass = NSDataProvider.class)
    public void testAddReceiptSuccess(String name,String taxcode,String address,String telphone,String bankname,String bankno,String type){
        engine.click("xpath=//a[@title='发票管理']");
        engine.click("class=new_address_btn");
        engine.enterFrame("OpentaxWindow");
        engine.type("name=company_name",name);
        engine.type("name=taxcode",taxcode);
        engine.type("name=address",address);
        engine.type("name=telphone",telphone);
        engine.type("name=bankname",bankname);
        engine.type("name=bankno",bankno);
        engine.selectByIndex("xpath=//select[@name='type']",Integer.parseInt(type));
        engine.leaveFrame();
        engine.pause(1000);
        engine.click("xpath=//button[@class=' aui_state_highlight']");
        Assert.assertTrue(engine.isTextPresent(name));
    }
    //7.数据驱动测试商品评论功能
        //成功数据5条
    int j = 0;
    @Test(dataProvider = "addComment",dataProviderClass = NSDataProvider.class)
    public void testAddComment(String level,String contents,String img){
        engine.click("xpath=//a[@title='商品评价']");
        engine.click("xpath=//a[@class='orange bold'][1]");
        engine.click("xpath=//input[@value='"+level+"']");
        engine.clearAndType("class=input_textarea",contents);
        if (j>=3){
            engine.clearAndType("id=fileUpload",img);
        }
        engine.pause(1000);
        engine.click("xpath=//input[@class='input_submit']");
        j++;
        engine.open(url);
    }
}
