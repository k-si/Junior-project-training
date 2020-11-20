package com.seleniumPlus.testcase;

import com.seleniumPlus.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestSearch extends BaseTest {
    String url="http://www.iwebshop.com:8888/";
    //获得网页面包屑内容
    public String getBreadCrumb(){
        String result=engine.getText("xpath=//*[@class='breadcrumb']");
        return result;
    }

    @BeforeClass
    public void openUrl() throws InterruptedException {
        engine.open(url);
        Thread.sleep(2000);
    }

    //1.点击搜索框默认的商品名称真皮女包
    @Test
    public void test_click_search_hotword1() throws InterruptedException {
        engine.click("xpath=//div[@class='search_hotwords']/a[1]");
        Thread.sleep(2000);
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 » 搜索\"真皮女包\"");
        engine.slideDownToBottom();
        //查看各个商品页面之间能否正常跳转
        engine.goBack();
        Thread.sleep(2000);
    }

    //2.点击搜索框默认的商品名称衬衣
    @Test
    public void test_click_search_hotword2() throws InterruptedException {
        engine.click("xpath=//div[@class='search_hotwords']/a[2]");
        Thread.sleep(2000);
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 » 搜索\"衬衣\"");
        engine.slideDownToBottom();
        //查看各个商品页面之间能否正常跳转
        engine.goBack();
        Thread.sleep(2000);
    }

    //3.在搜索框输入大家电，看能否正常跳转到大家电商品展示界面
    @Test
    public void test_send_search_keyword1() throws InterruptedException {
        engine.type("xpath=//input[@class='search_keyword']","大家电");
        engine.click("xpath=//button[@type='submit']");
        Thread.sleep(2000);
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 » 搜索\"大家电\"");
        Assert.assertTrue(engine.isTextPresent("对不起，没有找到相关商品"));
        engine.slideDownToBottom();
        //查看各个商品页面之间能否正常跳转
        engine.goBack();
        Thread.sleep(2000);
    }

    //4.在搜索框输生活电器，看能否正常跳转到生活电器商品展示界面
    @Test
    public void test_send_search_keyword2() throws InterruptedException {
        engine.type("xpath=//input[@class='search_keyword']","生活电器");
        engine.click("xpath=//button[@type='submit']");
        Thread.sleep(2000);
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 » 搜索\"生活电器\"");
        Assert.assertTrue(engine.isTextPresent("对不起，没有找到相关商品"));
        engine.slideDownToBottom();
        //查看各个商品页面之间能否正常跳转
        engine.goBack();
        Thread.sleep(2000);
    }

    //5.在搜索框输入厨房电器，看能否正常跳转到厨房电器商品展示界面
    @Test
    public void test_send_search_keyword3() throws InterruptedException {
        engine.type("xpath=//input[@class='search_keyword']","厨房电器");
        engine.click("xpath=//button[@type='submit']");
        Thread.sleep(2000);
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 » 搜索\"厨房电器\"");
        Assert.assertTrue(engine.isTextPresent("对不起，没有找到相关商品"));
        engine.slideDownToBottom();
        //查看各个商品页面之间能否正常跳转
        engine.goBack();
        Thread.sleep(2000);
    }

    //6.在搜索框输入进口食品，看能否正常跳转到进口食品商品展示界面
    @Test
    public void test_send_search_keyword4() throws InterruptedException {
        engine.type("xpath=//input[@class='search_keyword']","进口食品");
        engine.click("xpath=//button[@type='submit']");
        Thread.sleep(2000);
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 » 搜索\"进口食品\"");
        Assert.assertTrue(engine.isTextPresent("对不起，没有找到相关商品"));
        engine.slideDownToBottom();
        //查看各个商品页面之间能否正常跳转
        engine.goBack();
        Thread.sleep(2000);
    }

    //7.在搜索框输入酒品，看能否正常跳转到酒品商品展示界面
    @Test
    public void test_send_search_keyword5() throws InterruptedException {
        engine.type("xpath=//input[@class='search_keyword']","酒品");
        engine.click("xpath=//button[@type='submit']");
        Thread.sleep(2000);
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 » 搜索\"酒品\"");
        Assert.assertTrue(engine.isTextPresent("对不起，没有找到相关商品"));
        engine.slideDownToBottom();
        //查看各个商品页面之间能否正常跳转
        engine.goBack();
        Thread.sleep(2000);
    }

    //8.在搜索框输入家装建材，看能否正常跳转到家装建材商品展示界面
    @Test
    public void test_send_search_keyword6() throws InterruptedException {
        engine.type("xpath=//input[@class='search_keyword']","家装建材");
        engine.click("xpath=//button[@type='submit']");
        Thread.sleep(2000);
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 » 搜索\"家装建材\"");
        Assert.assertTrue(engine.isTextPresent("对不起，没有找到相关商品"));
        engine.slideDownToBottom();
        //查看各个商品页面之间能否正常跳转
        engine.goBack();
        Thread.sleep(2000);
    }

    //9.在搜索框输入卧室家具，看能否正常跳转到卧室家具商品展示界面
    @Test
    public void test_send_search_keyword7() throws InterruptedException {
        engine.type("xpath=//input[@class='search_keyword']","卧室家具");
        engine.click("xpath=//button[@type='submit']");
        Thread.sleep(2000);
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 » 搜索\"卧室家具\"");
        engine.slideDownToBottom();
        //查看各个商品页面之间能否正常跳转
        engine.goBack();
        Thread.sleep(2000);
    }

    //10.在搜索框输入男装，看能否正常跳转到男装商品展示界面
    @Test
    public void test_send_search_keyword8() throws InterruptedException {
        engine.type("xpath=//input[@class='search_keyword']","男装");
        engine.click("xpath=//button[@type='submit']");
        Thread.sleep(2000);
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 » 搜索\"男装\"");
        engine.slideDownToBottom();
        //查看各个商品页面之间能否正常跳转
        engine.goBack();
        Thread.sleep(2000);
    }

    //11.在搜索框输入女包，看能否正常跳转到女包商品展示界面
    @Test
    public void test_send_search_keyword9() throws InterruptedException {
        engine.type("xpath=//input[@class='search_keyword']","女包");
        engine.click("xpath=//button[@type='submit']");
        Thread.sleep(2000);
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 » 搜索\"女包\"");
        engine.slideDownToBottom();
        //查看各个商品页面之间能否正常跳转
        engine.goBack();
        Thread.sleep(2000);
    }

    //12.在搜索框输入大家电，看能否正常跳转到女装商品展示界面
    @Test
    public void test_send_search_keyword10() throws InterruptedException {
        engine.type("xpath=//input[@class='search_keyword']","女装");
        engine.click("xpath=//button[@type='submit']");
        Thread.sleep(2000);
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 » 搜索\"女装\"");
        Assert.assertTrue(engine.isTextPresent("对不起，没有找到相关商品"));
        engine.slideDownToBottom();
        //查看各个商品页面之间能否正常跳转
        engine.goBack();
        Thread.sleep(2000);
    }

}
