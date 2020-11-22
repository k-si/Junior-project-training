package com.seleniumPlus.testcase;

import com.seleniumPlus.core.BaseTest;
import com.seleniumPlus.dataprovider.NSDataProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestShopMoudle extends BaseTest{

    private String url = "http://101.200.157.76:8888/index.php?controller=admin&action=index";

    /*
    登录程序
     */
    public void login(){
        engine.open(url);
        engine.type("name=admin_name","admin");
        engine.type("name=password","123456");
        engine.type("name=captcha","123");
        engine.click("xpath=//button[@type='submit']");
    }

    /*
    商品名称字符串
     */
    @Test(dataProvider="excel", dataProviderClass = NSDataProvider.class)
    public void testproductName1(String shopname,String maketprice,String salesprice){
        login();

        engine.click("link=商品");
        engine.click("link=添加");

        engine.type("name=name",shopname);
        engine.type("name=_market_price[0]",maketprice);
        engine.type("name=_sell_price[0]",salesprice);
        engine.click("xpath=//button[@type='submit']");

        boolean displayed = engine.isDisplayed("link=商品1");
        Assert.assertTrue(displayed);
    }

    /*
    商品名称数字
     */
    @Test
    public void testproductName2(){
        login();

        engine.click("link=商品");
        engine.click("link=添加");

        engine.type("name=name","123");
        engine.type("name=_market_price[0]","12");
        engine.type("name=_sell_price[0]","6");
        engine.click("xpath=//button[@type='submit']");

        boolean displayed = engine.isDisplayed("link=123");
        Assert.assertTrue(displayed);
    }

    /*
    商品名称特殊字符
     */
    @Test
    public void testproductName3(){
        login();

        engine.click("link=商品");
        engine.click("link=添加");

        engine.type("name=name","$%^$%%@#%^");
        engine.type("name=_market_price[0]","12");
        engine.type("name=_sell_price[0]","6");
        engine.click("xpath=//button[@type='submit']");

        boolean displayed = engine.isDisplayed("link=$%^$%%@#%^");
        Assert.assertTrue(displayed);
    }

    /*
    关键词null
     */
    @Test
    public void testkeyWord1() throws InterruptedException {
        login();

        engine.click("link=商品");
        engine.click("link=添加");

        engine.type("name=name","商品4");
        engine.type("name=_market_price[0]","12");
        engine.type("name=_sell_price[0]","6");
        engine.click("xpath=//button[@type='submit']");

        engine.click("class=fa-gears");
        engine.click("link=商城首页");
        engine.pause(3000);

        engine.switchWidow(1);
        engine.clearAndType("class=search_keyword","商品4");
        engine.click("xpath=//button[@type='submit']");

        boolean display = engine.isDisplayed("xpath=//a[@target='_blank']/img[@alt='商品4']");
        Assert.assertTrue(display);
    }

    /*
    关键词15个字符
     */
    @Test
    public void testkeyWord2(){
        login();

        engine.click("link=商品");
        engine.click("link=添加");

        engine.type("name=name","商品5");
        engine.type("name=search_words","012345678901234");
        engine.type("name=_market_price[0]","12");
        engine.type("name=_sell_price[0]","6");
        engine.click("xpath=//button[@type='submit']");

        engine.click("class=fa-gears");
        engine.click("link=商城首页");
        engine.pause(3000);

        engine.switchWidow(1);
        engine.clearAndType("class=search_keyword","012345678901234");
        engine.click("xpath=//button[@type='submit']");

        boolean display = engine.isDisplayed("xpath=//a[@target='_blank']/img[@alt='商品5']");
        Assert.assertTrue(display);
    }

    /*
    关键词16个字符
     */
    @Test
    public void testkeyWord3(){
        login();

        engine.click("link=商品");
        engine.click("link=添加");

        engine.type("name=name","商品6");
        engine.type("name=search_words","0123456789012345");
        engine.type("name=_market_price[0]","12");
        engine.type("name=_sell_price[0]","6");
        engine.click("xpath=//button[@type='submit']");

        engine.click("class=fa-gears");
        engine.click("link=商城首页");
        engine.pause(3000);

        engine.switchWidow(1);
        engine.clearAndType("class=search_keyword","0123456789012345");
        engine.click("xpath=//button[@type='submit']");

        boolean display = engine.isDisplayed("xpath=//a[@target='_blank']/img[@alt='商品6']");
        Assert.assertFalse(display);
    }

    /*
    商品分类单选
     */
    @Test
    public void testshopClass1(){
        login();

        engine.click("link=商品");
        engine.click("link=添加");

        engine.type("name=name","商品7");
        engine.click("xpath=//button[@name='_goodsCategoryButton']");

        engine.enterFrame(1);
        engine.click("xpath=//div[@id='categoryBox']/ul/li[1]/label/span");
        engine.leaveFrame();
        engine.click("class=aui_state_highlight");


        engine.type("name=_market_price[0]","12");
        engine.type("name=_sell_price[0]","6");
        engine.click("xpath=//button[@type='submit']");

        String shopname = engine.getText("xpath=//div[@id='admin_right']/div[2]/table/tbody/tr/td[2]/a");
        String shopclass = engine.getText("xpath=//div[@id='admin_right']/div[2]/table/tbody/tr/td[3]");

        Assert.assertEquals(shopname,"商品7");
        Assert.assertEquals(shopclass,"[家用电器]");

    }

    /*
    商品分类多选
     */
    @Test
    public void testshopClass2(){
        login();

        engine.click("link=商品");
        engine.click("link=添加");

        engine.type("name=name","商品8");
        engine.click("xpath=//button[@name='_goodsCategoryButton']");

        engine.enterFrame(1);
        engine.click("xpath=//div[@id='categoryBox']/ul/li[1]/label/span");
        engine.click("xpath=//div[@id='categoryBox']/ul/li[2]/label/span");
        engine.click("xpath=//div[@id='categoryBox']/ul/li[3]/label/span");
        engine.click("xpath=//div[@id='categoryBox']/ul/li[4]/label/span");
        engine.leaveFrame();
        engine.click("class=aui_state_highlight");


        engine.type("name=_market_price[0]","12");
        engine.type("name=_sell_price[0]","6");
        engine.click("xpath=//button[@type='submit']");

        String shopname = engine.getText("xpath=//div[@id='admin_right']/div[2]/table/tbody/tr/td[2]/a");
        String shopclass = engine.getText("xpath=//div[@id='admin_right']/div[2]/table/tbody/tr/td[3]");

        Assert.assertEquals(shopname,"商品8");
        Assert.assertEquals(shopclass,"[家用电器] [食品饮料] [家具] [服装]");
    }

    /*
    商品状态上架
     */
    @Test
    public void testUp(){
        login();

        engine.click("link=商品");
        engine.click("link=添加");

        engine.type("name=name","商品9");
        engine.click("xpath=//input[@name='is_del' and @value='0']");
        engine.type("name=_market_price[0]","12");
        engine.type("name=_sell_price[0]","6");
        engine.click("xpath=//button[@type='submit']");

        String state = engine.getValue("xpath=//div[@id='admin_right']/div[2]/table/tbody/tr/td[6]/select");

        Assert.assertEquals(state,"up");
    }

    /*
    商品状态下架
     */
    @Test(dataProvider="excel", dataProviderClass = NSDataProvider.class)
    public void testDown(){
        login();

        engine.click("link=商品");
        engine.click("link=添加");

        engine.type("name=name","商品10");
        engine.click("xpath=//input[@name='is_del' and @value='2']");
        engine.type("name=_market_price[0]","12");
        engine.type("name=_sell_price[0]","6");
        engine.click("xpath=//button[@type='submit']");

        String state = engine.getValue("xpath=//div[@id='admin_right']/div[2]/table/tbody/tr/td[6]/select");

        Assert.assertEquals(state,"down");
    }

   /*
   不免运费
    */
   @Test
    public void testFreight1(){
        login();

        engine.click("link=商品");
        engine.click("link=添加");

        engine.type("name=name","商品11");
        engine.click("xpath=//input[@name='is_delivery_fee' and @value='0']");
        engine.type("name=_market_price[0]","12");
        engine.type("name=_sell_price[0]","6");
        engine.click("xpath=//button[@type='submit']");

        engine.click("xpath=//div[@id='admin_right']/div[2]/table/tbody/tr/td[2]/a");

        engine.switchWidow(1);
        String money=  engine.getText("xpath=//span[@id='deliveInfo']/b");
        Assert.assertEquals(money,"￥20");
    }

    /*
    免运费
     */
    @Test
    public void testFreight2(){
        login();

        engine.click("link=商品");
        engine.click("link=添加");

        engine.type("name=name","商品12");
        engine.click("xpath=//input[@name='is_delivery_fee' and @value='1']");
        engine.type("name=_market_price[0]","12");
        engine.type("name=_sell_price[0]","6");
        engine.click("xpath=//button[@type='submit']");

        engine.click("xpath=//div[@id='admin_right']/div[2]/table/tbody/tr/td[2]/a");

        engine.switchWidow(1);
        String money=  engine.getText("xpath=//span[@id='deliveInfo']/b");
        Assert.assertEquals(money,"￥0");
    }

    /*
    设置成功购买积分100
     */
    @Test(dataProvider="excel", dataProviderClass = NSDataProvider.class)
    public void testCollateraldata1(){
        login();

        engine.click("link=商品");
        engine.click("link=添加");

        engine.type("name=name","商品13");
        engine.clearAndType("xpath=//input[@name='point']","100");
        engine.type("name=_market_price[0]","12");
        engine.type("name=_sell_price[0]","6");
        engine.click("xpath=//button[@type='submit']");

        engine.click("xpath=//div[@id='admin_right']/div[2]/table/tbody/tr/td[2]/a");

        engine.switchWidow(1);
        String data = engine.getText("xpath=//li[7]");
        Assert.assertEquals(data,"送积分：单件送100分");
    }

    /*
    设置成功购买积分为-100
     */
    @Test
    public void testCollateraldata2(){
        login();

        engine.click("link=商品");
        engine.click("link=添加");

        engine.type("name=name","商品14");
        engine.clearAndType("xpath=//input[@name='point']","-100");
        engine.type("name=_market_price[0]","12");
        engine.type("name=_sell_price[0]","6");
        engine.click("xpath=//button[@type='submit']");

        engine.click("xpath=//div[@id='admin_right']/div[2]/table/tbody/tr/td[2]/a");

        engine.switchWidow(1);
        String data = engine.getText("xpath=//li[7]");
        Assert.assertEquals(data,"送积分：单件送-100分");
    }

    /*
    设置购买成功送积分0
     */
    @Test
    public void testCollateraldata3(){
        login();

        engine.click("link=商品");
        engine.click("link=添加");

        engine.type("name=name","商品15");
        engine.clearAndType("xpath=//input[@name='point']","0");
        engine.type("name=_market_price[0]","12");
        engine.type("name=_sell_price[0]","6");
        engine.click("xpath=//button[@type='submit']");

        engine.click("xpath=//div[@id='admin_right']/div[2]/table/tbody/tr/td[2]/a");

        engine.switchWidow(1);
        String data = engine.getText("xpath=//li[7]");
        Assert.assertEquals(data,"送积分：单件送0分");
    }

    /*
    设置计件为10
     */
    @Test
    public void testunits1(){
        login();

        engine.click("link=商品");
        engine.click("link=添加");

        engine.type("name=name","商品16");
        engine.clearAndType("xpath=//input[@name='unit']","10件");
        engine.type("name=_market_price[0]","12");
        engine.type("name=_sell_price[0]","6");
        engine.click("xpath=//button[@type='submit']");

        engine.click("xpath=//div[@id='admin_right']/div[2]/table/tbody/tr/td[2]/a");

        engine.switchWidow(1);
        boolean display = engine.isTextPresent("单位：10件");
        Assert.assertTrue(display);
    }

    /*
    设置计件为0
     */
    @Test
    public void testunits2(){
        login();

        engine.click("link=商品");
        engine.click("link=添加");

        engine.type("name=name","商品17");
        engine.clearAndType("xpath=//input[@name='unit']","0件");
        engine.type("name=_market_price[0]","12");
        engine.type("name=_sell_price[0]","6");
        engine.click("xpath=//button[@type='submit']");

        engine.click("xpath=//div[@id='admin_right']/div[2]/table/tbody/tr/td[2]/a");

        engine.switchWidow(1);
        boolean display = engine.isTextPresent("单位：0件");
        Assert.assertTrue(display);
    }

    /*
    设置计件为-10件
     */
    @Test
    public void testunits3(){
        login();

        engine.click("link=商品");
        engine.click("link=添加");

        engine.type("name=name","商品18");
        engine.clearAndType("xpath=//input[@name='unit']","-10件");
        engine.type("name=_market_price[0]","12");
        engine.type("name=_sell_price[0]","6");
        engine.click("xpath=//button[@type='submit']");

        engine.click("xpath=//div[@id='admin_right']/div[2]/table/tbody/tr/td[2]/a");

        engine.switchWidow(1);
        boolean display = engine.isTextPresent("单位：-10件");
        Assert.assertTrue(display);
    }

    /*
    设置库存为100
     */
    @Test
    public void teststoreNum1(){
        login();

        engine.click("link=商品");
        engine.click("link=添加");

        engine.type("name=name","商品19");
        engine.clearAndType("xpath=//input[@name='_store_nums[0]']","100");
        engine.type("name=_market_price[0]","12");
        engine.type("name=_sell_price[0]","6");
        engine.click("xpath=//button[@type='submit']");

        engine.click("xpath=//div[@id='admin_right']/div[2]/table/tbody/tr/td[2]/a");

        engine.switchWidow(1);
        String data = engine.getText("xpath=//section[2]/ul/li[4]");
        Assert.assertEquals(data,"库存：现货 100 收藏此商品");
    }

    /*
    设置库存为0
     */
    @Test
    public void teststoreNum2(){
        login();

        engine.click("link=商品");
        engine.click("link=添加");

        engine.type("name=name","商品20");
        engine.clearAndType("xpath=//input[@name='_store_nums[0]']","0");
        engine.type("name=_market_price[0]","12");
        engine.type("name=_sell_price[0]","6");
        engine.click("xpath=//button[@type='submit']");

        engine.click("xpath=//div[@id='admin_right']/div[2]/table/tbody/tr/td[2]/a");

        engine.switchWidow(1);
        String data = engine.getText("xpath=//section[2]/ul/li[4]");
        Assert.assertEquals(data,"库存：现货 0 收藏此商品");
    }

    /*
    设置库存为-100
     */
    @Test
    public void teststoreNum3(){
        login();

        engine.click("link=商品");
        engine.click("link=添加");

        engine.type("name=name","商品21");
        engine.clearAndType("xpath=//input[@name='_store_nums[0]']","-100");
        engine.type("name=_market_price[0]","12");
        engine.type("name=_sell_price[0]","6");
        engine.click("xpath=//button[@type='submit']");

        engine.click("xpath=//div[@id='admin_right']/div[2]/table/tbody/tr/td[2]/a");

        engine.switchWidow(1);
        String data = engine.getText("xpath=//section[2]/ul/li[4]");
        Assert.assertEquals(data,"库存：现货 -100 收藏此商品");
    }

    /*
    点击全选
     */
    @Test
    public void testCheckall1(){
        login();

        engine.click("link=商品");
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
    双击全选取消
     */
    @Test
    public void testCheckall2(){
        login();

        engine.click("link=商品");
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
    不选择商品，批量下架
     */
    @Test
    public void testbulkOperation1(){
        login();

        engine.click("link=商品");
        engine.click("xpath=(//button[@type='button'])[3]");
        engine.click("link=下架");
        String alert = engine.getText("class=aui_content");
        Assert.assertEquals(alert,"请选择要操作项");
    }

    /*
    选择商品，批量下架
     */
    @Test
    public void testbulkOperation2(){
        login();

        engine.click("link=商品");
        engine.click("xpath=(//input[@name='id[]'])[1]");
        engine.click("xpath=(//button[@type='button'])[3]");
        engine.click("link=下架");
        engine.click("class=aui_state_highlight");
        String state = engine.getValue("xpath=//div[@id='admin_right']/div[2]/table/tbody/tr/td[6]/select");
        Assert.assertEquals(state,"down");
    }

    /*
    不选择商品，批量删除
     */
    @Test
    public void testbulkOperation3(){
        login();

        engine.click("link=商品");
        engine.click("xpath=(//button[@type='button'])[3]");
        engine.click("link=删除");
        String alert = engine.getText("class=aui_content");
        Assert.assertEquals(alert,"请选择要操作项");
    }

    /*
    选择商品，批量删除
     */
    @Test
    public void testbulkOperation4(){
        login();

        engine.click("link=商品");
        String shopname1 = engine.getText("xpath=//div[@id='admin_right']/div[2]/table/tbody/tr/td[2]/a");
        engine.click("xpath=(//input[@name='id[]'])[1]");
        engine.click("xpath=(//button[@type='button'])[3]");
        engine.click("link=删除");
        engine.click("class=aui_state_highlight");
        String shopname2 = engine.getText("xpath=//div[@id='admin_right']/div[2]/table/tbody/tr/td[2]/a");
        Assert.assertNotEquals(shopname1,shopname2);
    }

    /*
    不选择商品，点击待审
     */
    @Test
    public void testbulkOperation5(){
        login();

        engine.click("link=商品");
        engine.click("xpath=(//button[@type='button'])[3]");
        engine.click("link=待审");
        String alert = engine.getText("class=aui_content");
        Assert.assertEquals(alert,"请选择要操作项");
    }

    /*
    选择商品，选择待审
     */
    @Test
    public void testbulkOperation6(){
        login();

        engine.click("link=商品");
        engine.click("xpath=(//input[@name='id[]'])[1]");
        engine.click("xpath=(//button[@type='button'])[3]");
        engine.click("link=待审");
        engine.click("class=aui_state_highlight");
        String state = engine.getValue("xpath=//div[@id='admin_right']/div[2]/table/tbody/tr/td[6]/select");
        Assert.assertEquals(state,"check");
    }

    /*
    点击回收站
     */
    @Test
    public void testrecycleBin(){
        login();

        engine.click("link=商品");
        engine.click("link=回收站");

        Assert.assertEquals(engine.getTitle(),"后台管理");
    }

    /*
    不选择商品，选择批量编辑
     */
    @Test
    public void testbulkEdit1(){
        login();

        engine.click("link=商品");
        engine.click("link=批量编辑");
        String alert = engine.getText("class=aui_content");
        Assert.assertEquals(alert,"请选择您要操作的商品");
    }

    /*
    选择商品，点击批量编辑
     */
    @Test
    public void testbulkEdit2(){
        login();

        engine.click("link=商品");
        engine.click("xpath=(//input[@name='id[]'])[1]");
        engine.click("link=批量编辑");

        engine.enterFrame(0);
        engine.click("xpath=//button[@name='_goodsCategoryButton']");
        engine.leaveFrame();

        engine.enterFrame(1);
        engine.click("xpath=//div[@id='categoryBox']/ul/li[1]/label/span");
        engine.click("class=aui_state_highlight");
        engine.leaveFrame();

        engine.click("xpath=//button[contains(.,'确定')]");
        engine.click("xpath=//button[contains(.,'保存设置')]");

        String shopclass = engine.getText("xpath=//div[@id='admin_right']/div[2]/table/tbody/tr/td[3]");
        Assert.assertEquals(shopclass,"[家用电器]");
    }

    /*
    点击检索，不输入
     */
    @Test
    public void testSearch1(){
        login();

        engine.click("link=商品");
        engine.click("link=检索");
        engine.click("class=aui_state_highlight");
        Assert.assertEquals(engine.getTitle(),"后台管理");
    }

    /*
    点击检索，输入正确商品名
     */
    @Test
    public void testSearch2(){
        login();

        engine.click("link=商品");
        engine.click("link=检索");
        engine.enterFrame(0);
        engine.type("name=search[content]","商品21");
        engine.leaveFrame();
        engine.click("class=aui_state_highlight");
        String shopname = engine.getText("xpath=//div[@id='admin_right']/div[2]/table/tbody/tr/td[2]/a");
        Assert.assertEquals(shopname,"商品21");
    }

    /*
    点击检索，输入错误商品名
     */
    @Test
    public void testSearch3(){
        login();

        engine.click("link=商品");
        engine.click("link=检索");
        engine.enterFrame(0);
        engine.type("name=search[content]","商品35");
        engine.leaveFrame();
        engine.click("class=aui_state_highlight");
        String shopname = engine.getText("xpath=//div[@id='admin_right']/div[2]/table/tbody/tr/td[2]/a");
        Assert.assertEquals(shopname,"商品35");
    }
}
