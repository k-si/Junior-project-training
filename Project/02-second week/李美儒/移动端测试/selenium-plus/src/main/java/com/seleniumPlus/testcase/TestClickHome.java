package com.seleniumPlus.testcase;

import com.seleniumPlus.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestClickHome extends BaseTest {
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

    //50.点击首页中家用电器旁边的大家电，看是否能跳转到对应界面
    @Test
    public void test_page_jump1() throws InterruptedException {
        engine.slideDownToBottom();
        engine.click("xpath=//*[@class='home_floor'][1]/header/nav/ul/li[1]/a");
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 > 家用电器 > 大家电");
        //存在不属于大家电类型的饿iwebshop支付测试
        engine.isTextPresent("iWebShop支付测试");
        engine.goBack();

    }
    //51.点击首页中家用电器旁边的生活电器，看是否能跳转到对应界面
    @Test
    public void test_page_jump2() throws InterruptedException {
        engine.slideDownToBottom();
        engine.click("xpath=//*[@class='home_floor'][1]/header/nav/ul/li[2]/a");
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 > 家用电器 > 生活电器");
        engine.goBack();

    }

    //52.点击首页中家用电器旁边的厨房电器，看是否能跳转到对应界面
    @Test
    public void test_page_jump3() throws InterruptedException {
        engine.slideDownToBottom();
        engine.click("xpath=//*[@class='home_floor'][1]/header/nav/ul/li[3]/a");
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 > 家用电器 > 厨房电器");
        //存在不属于厨房电器类别的电视商品信息
        engine.isTextPresent("电视");
        engine.goBack();

    }

    //53.点击首页中食品饮料旁边的进口食品，看是否能跳转到对应界面
    @Test
    public void test_page_jump4() throws InterruptedException {
        engine.slideDownToBottom();
        engine.click("xpath=//*[@class='home_floor'][2]/header/nav/ul/li[1]/a");
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 > 食品饮料 > 进口食品");
        engine.goBack();
    }

    //54.点击首页中食品饮料旁边的酒品，看是否能跳转到对应界面
    @Test
    public void test_page_jump5() throws InterruptedException {
        engine.slideDownToBottom();
        engine.click("xpath=//*[@class='home_floor'][2]/header/nav/ul/li[2]/a");
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 > 食品饮料 > 酒品");
        engine.goBack();
    }

    //55.点击首页中家具旁边的家装建材，看是否能跳转到对应界面
    @Test
    public void test_page_jump6() throws InterruptedException {
        engine.slideDownToBottom();
        engine.click("xpath=//*[@class='home_floor'][3]/header/nav/ul/li[1]/a");
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 > 家具 > 家装建材");
        engine.goBack();
    }

    //56.点击首页中家具旁边的卧室家具，看是否能跳转到对应界面
    @Test
    public void test_page_jump7() throws InterruptedException {
        engine.slideDownToBottom();
        engine.click("xpath=//*[@class='home_floor'][3]/header/nav/ul/li[2]/a");
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 > 家具 > 卧室家具");
        engine.goBack();
    }

    //57.点击首页中服装旁边的男装，看是否能跳转到对应界面
    @Test
    public void test_page_jump8() throws InterruptedException {
        engine.slideDownToBottom();
        engine.click("xpath=//*[@class='home_floor'][4]/header/nav/ul/li[1]/a");
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 > 服装 > 男装");
        //存在不属于男装类别的女士服装信息
        engine.isTextPresent("女士");
        engine.goBack();
    }

    //58.点击首页中服装旁边的女包，看是否能跳转到对应界面
    @Test
    public void test_page_jump9() throws InterruptedException {
        engine.slideDownToBottom();
        engine.click("xpath=//*[@class='home_floor'][4]/header/nav/ul/li[2]/a");
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 > 服装 > 女包");
        engine.goBack();
    }

    //58.点击首页中服装旁边的女装，看是否能跳转到对应界面
    @Test
    public void test_page_jump10() throws InterruptedException {
        engine.slideDownToBottom();
        engine.click("xpath=//*[@class='home_floor'][4]/header/nav/ul/li[3]/a");
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 > 服装 > 女装");
        engine.goBack();
    }
}
