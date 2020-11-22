package com.seleniumPlus.testcase;

import com.seleniumPlus.core.BaseTest;
import com.seleniumPlus.dataprovider.NSDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestIndentMoudle extends BaseTest {
    private String url = "http://iwebshop:8888/index.php?controller=admin&action=index";

    /*
    登录程序
     */
    public void login(){
        engine.open(url);
        engine.type("name=admin_name","iwebshop");
        engine.type("name=password","123456");
        engine.type("name=captcha","123");
        engine.click("xpath=//button[@type='submit']");
    }

    /*
    添加订单,不输入商品名
     */
    @Test(dataProvider="excel", dataProviderClass = NSDataProvider.class)
    public void testaddIndent1(){
        login();

        engine.click("link=订单");
        engine.click("link=添加订单");
        engine.click("link=添加商品");

        engine.click("class=aui_state_highlight");
        engine.enterFrame(0);
        engine.click("id=goods0");
        String num = engine.getText("css=tr:nth-child(1) label");
        String shopname1 = engine.getText("xpath=//div[2]/table/tbody/tr/td[2]");
        engine.leaveFrame();

        engine.click("xpath=//button[contains(.,'执行')]");
        String shopname2 = engine.getText("xpath=//div[2]/div/table/tbody/tr/td");
        Assert.assertEquals(shopname1 + " " + "【" + num + "】",shopname2);
    }

    /*
    添加订单,输入正确商品名称
     */
    @Test(dataProvider="excel", dataProviderClass = NSDataProvider.class)
    public void testaddIndent2(){
        login();

        engine.click("link=订单");
        engine.click("link=添加订单");
        engine.click("link=添加商品");

        engine.enterFrame(0);
        engine.type("name=search[content]","商品21");
        engine.leaveFrame();

        engine.click("class=aui_state_highlight");
        engine.enterFrame(0);
        engine.click("id=goods0");
        String num = engine.getText("css=tr:nth-child(1) label");
        String shopname1 = engine.getText("xpath=//div[2]/table/tbody/tr/td[2]");
        engine.leaveFrame();

        engine.click("xpath=//button[contains(.,'执行')]");
        String shopname2 = engine.getText("xpath=//div[2]/div/table/tbody/tr/td");
        Assert.assertEquals(shopname1 + " " + "【" + num + "】",shopname2);
    }

    /*
    添加订单,输入错误商品名称
     */
    @Test(dataProvider="excel", dataProviderClass = NSDataProvider.class)
    public void testaddIndent3(){
        login();

        engine.click("link=订单");
        engine.click("link=添加订单");
        engine.click("link=添加商品");

        engine.enterFrame(0);
        engine.type("name=search[content]","商品32");
        engine.leaveFrame();

        engine.click("class=aui_state_highlight");
        engine.enterFrame(0);
        engine.click("id=goods0");
        String num = engine.getText("css=tr:nth-child(1) label");
        String shopname1 = engine.getText("xpath=//div[2]/table/tbody/tr/td[2]");
        engine.leaveFrame();

        engine.click("xpath=//button[contains(.,'执行')]");
        String shopname2 = engine.getText("xpath=//div[2]/div/table/tbody/tr/td");
        Assert.assertEquals(shopname1 + " " + "【" + num + "】",shopname2);
    }

    /*
    全选
     */
    @Test(dataProvider="excel", dataProviderClass = NSDataProvider.class)
    public void testCheckall1(){
        login();

        engine.click("link=订单");
        engine.click("link=全选");

        for (int i = 1; i <= 20; i++) {
            if(engine.isDisplayed("xpath=(//input[@name='id[]'])[" + i + "]")){
                boolean check = engine.isChecked("xpath=(//input[@name='id[]'])[" + i + "]");
                Assert.assertFalse(check);
            }
            else {
                break;
            }
        }
    }

    /*
    双击取消全选
     */
    @Test(dataProvider="excel", dataProviderClass = NSDataProvider.class)
    public void testCheckall2(){
        login();

        engine.click("link=订单");
        engine.doubleClick("link=全选");

        for (int i = 1; i <= 20; i++) {
            if(engine.isDisplayed("xpath=(//input[@name='id[]'])[" + i + "]")){
                boolean check = engine.isChecked("xpath=(//input[@name='id[]'])[" + i + "]");
                Assert.assertFalse(check);
            }
            else {
                break;
            }
        }
    }

    /*
    不选择商品，批量删除
     */
    @Test(dataProvider="excel", dataProviderClass = NSDataProvider.class)
    public void testbulkOperation1(){
        login();

        engine.click("link=订单");
        engine.click("xpath=(//button[@type='button'])[2]");
        engine.click("link=批量删除");
        String alert = engine.getText("class=aui_content");
        Assert.assertEquals(alert,"请选择要操作项");
    }

    /*
    选择商品，批量删除
     */
    @Test(dataProvider="excel", dataProviderClass = NSDataProvider.class)
    public void testbulkOperation2(){
        login();

        engine.click("link=订单");
        String shopname1 = engine.getText("css=tbody:nth-child(5) > tr:nth-child(1) > td:nth-child(2)");
        engine.click("css=tr:nth-child(1) input");
        engine.click("xpath=(//button[@type='button'])[2]");
        engine.click("link=批量删除");
        engine.click("class=aui_state_highlight");
        String shopname2 = engine.getText("css=tbody:nth-child(5) > tr:nth-child(1) > td:nth-child(2)");
        Assert.assertNotEquals(shopname1,shopname2);
    }

    /*
    不选择商品，批量发货
     */
    @Test(dataProvider="excel", dataProviderClass = NSDataProvider.class)
    public void testbulkOperation3(){
        login();

        engine.click("link=订单");
        engine.click("xpath=(//button[@type='button'])[2]");
        engine.click("link=批量发货和打印快递单");
        String alert = engine.getText("class=aui_content");
        Assert.assertEquals(alert,"请选择要操作项");
    }

    /*
    选择商品，批量发货
     */
    @Test(dataProvider="excel", dataProviderClass = NSDataProvider.class)
    public void testbulkOperation4(){
        login();

        engine.click("link=订单");
        engine.click("css=tr:nth-child(1) input");
        engine.click("xpath=(//button[@type='button'])[2]");
        engine.click("link=批量发货和打印快递单");
        engine.click("class=aui_state_highlight");
        String state = engine.getText("css=tr:nth-child(1) > td:nth-child(6)");
        Assert.assertNotEquals(state,"等待发货");
    }

    /*
    点击回收站
     */
    @Test(dataProvider="excel", dataProviderClass = NSDataProvider.class)
    public void testrecycleBin(){
        login();

        engine.click("link=订单");
        engine.click("link=回收站");

        String url = engine.getUrl();
        Assert.assertEquals(url,"http://iwebshop:8888/index.php?controller=order&action=order_recycle_list");

    }

    /*
    点击检索，不输入
     */
    @Test(dataProvider="excel", dataProviderClass = NSDataProvider.class)
    public void testSearch1(){
        login();

        engine.click("link=订单");
        engine.click("link=检索");
        engine.click("class=aui_state_highlight");
        Assert.assertEquals(engine.getUrl(),"http://iwebshop:8888/index.php?controller=order&action=order_list&search%5Btype%5D=order_no&search%5Bcontent%5D=&search%5Bpay_status%5D=&search%5Bdistribution_status%5D=&search%5Bis_seller%5D=&search%5Bstatus%5D=&search%5Border_amount_down%5D=&search%5Border_amount_up%5D=&search%5Bcreate_time_start%5D=&search%5Bcreate_time_end%5D=&search%5Bsend_time_start%5D=&search%5Bsend_time_end%5D=&search%5Bcompletion_time_start%5D=&search%5Bcompletion_time_end%5D=");
    }

    /*
    点击检索，输入正确订单号
     */
    @Test(dataProvider="excel", dataProviderClass = NSDataProvider.class)
    public void testSearch2(){
        login();

        engine.click("link=订单");
        engine.click("link=检索");
        engine.enterFrame(0);
        engine.type("name=search[content]","202011151154124034");
        engine.leaveFrame();
        engine.click("class=aui_state_highlight");
        String shopname = engine.getText("css=tbody:nth-child(5) td:nth-child(2)");
        Assert.assertEquals(shopname,"202011151154124034");
    }

    /*
    点击检索，输入错误订单号
     */
    @Test(dataProvider="excel", dataProviderClass = NSDataProvider.class)
    public void testSearch3(){
        login();

        engine.click("link=订单");
        engine.click("link=检索");
        engine.enterFrame(0);
        engine.type("name=search[content]","123");
        engine.leaveFrame();
        engine.click("class=aui_state_highlight");
        String shopname = engine.getText("css=tbody:nth-child(5) td:nth-child(2)");
        Assert.assertNotEquals(shopname,"123");
    }

    /*
    点击查看订单
     */
    @Test(dataProvider="excel", dataProviderClass = NSDataProvider.class)
    public void testlook(){
        login();

        engine.click("link=订单");
        String shopname1 = engine.getText("css=tbody:nth-child(5) > tr:nth-child(1) > td:nth-child(2)");
        engine.click("css=tr:nth-child(1) .fa-eye");

        String shopname2 = engine.getText("css=.row:nth-child(4) tr:nth-child(1) > td");
        Assert.assertEquals(shopname1,shopname2);
    }

    /*
    点击修改订单
     */
    @Test(dataProvider="excel", dataProviderClass = NSDataProvider.class)
    public void testEdit(){
        login();

        engine.click("link=订单");

        engine.click("css=tr:nth-child(1) .fa-edit");
        
    }
}


