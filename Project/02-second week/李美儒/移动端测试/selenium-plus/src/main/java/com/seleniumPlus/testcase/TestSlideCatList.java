package com.seleniumPlus.testcase;

import com.seleniumPlus.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestSlideCatList extends BaseTest {
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

    //67.在导航栏滑动选择大家电，看是否正常跳转
    @Test
    public void test_slide1() throws InterruptedException {
        engine.isDisplayed("xpath=//ul[@class='cat_list']");
        engine.slide("xpath=//ul[@class='cat_list']");
        engine.slide("xpath=//ul[@class='cat_list']/li[1]");
        engine.slide("xpath=//a[text()='大家电']");
        engine.click("xpath=//a[text()='大家电']");
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 > 家用电器 > 大家电");
        engine.goBack();
    }

    //68.在导航栏滑动选择生活电器，看是否正常跳转
    @Test
    public void test_slide2() throws InterruptedException {
        engine.isDisplayed("xpath=//ul[@class='cat_list']");
        engine.slide("xpath=//ul[@class='cat_list']");
        engine.slide("xpath=//ul[@class='cat_list']/li[1]");
        engine.slide("xpath=//a[text()='生活电器']");
        engine.click("xpath=//a[text()='生活电器']");
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 > 家用电器 > 生活电器");
        engine.goBack();
    }

    //69.在导航栏滑动选择厨房电器，看是否正常跳转
    @Test
    public void test_slide3() throws InterruptedException {
        engine.isDisplayed("xpath=//ul[@class='cat_list']");
        engine.slide("xpath=//ul[@class='cat_list']");
        engine.slide("xpath=//ul[@class='cat_list']/li[1]");
        engine.slide("xpath=//a[text()='厨房电器']");
        engine.click("xpath=//a[text()='厨房电器']");
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 > 家用电器 > 厨房电器");
        engine.goBack();
    }

    //70.在导航栏滑动选择进口食品，看是否正常跳转
    @Test
    public void test_slide4() throws InterruptedException {
        engine.isDisplayed("xpath=//ul[@class='cat_list']");
        engine.slide("xpath=//ul[@class='cat_list']");
        engine.slide("xpath=//ul[@class='cat_list']/li[2]");
        engine.slide("xpath=//a[text()='进口食品']");
        engine.click("xpath=//a[text()='进口食品']");
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 > 食品饮料 > 进口食品");
        engine.goBack();
    }

    //71.在导航栏滑动选择酒品，看是否正常跳转
    @Test
    public void test_slide5() throws InterruptedException {
        engine.isDisplayed("xpath=//ul[@class='cat_list']");
        engine.slide("xpath=//ul[@class='cat_list']");
        engine.slide("xpath=//ul[@class='cat_list']/li[2]");
        engine.slide("xpath=//a[text()='酒品']");
        engine.click("xpath=//a[text()='酒品']");
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 > 食品饮料 > 酒品");
        engine.goBack();
    }

    //72.在导航栏滑动选择家装建材，看是否正常跳转
    @Test
    public void test_slide6() throws InterruptedException {
        engine.isDisplayed("xpath=//ul[@class='cat_list']");
        engine.slide("xpath=//ul[@class='cat_list']");
        engine.slide("xpath=//ul[@class='cat_list']/li[3]");
        engine.slide("xpath=//a[text()='家装建材']");
        engine.click("xpath=//a[text()='家装建材']");
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 > 家具 > 家装建材");
        engine.goBack();
    }

    //73.在导航栏滑动选择卧室家具，看是否正常跳转
    @Test
    public void test_slide7() throws InterruptedException {
        engine.isDisplayed("xpath=//ul[@class='cat_list']");
        engine.slide("xpath=//ul[@class='cat_list']");
        engine.slide("xpath=//ul[@class='cat_list']/li[3]");
        engine.slide("xpath=//a[text()='卧室家具']");
        engine.click("xpath=//a[text()='卧室家具']");
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 > 家具 > 卧室家具");
        engine.goBack();
    }

    //74.在导航栏滑动选择男装，看是否正常跳转
    @Test
    public void test_slide8() throws InterruptedException {
        engine.isDisplayed("xpath=//ul[@class='cat_list']");
        engine.slide("xpath=//ul[@class='cat_list']");
        engine.slide("xpath=//ul[@class='cat_list']/li[4]");
        engine.slide("xpath=//a[text()='男装']");
        engine.click("xpath=//a[text()='男装']");
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 > 服装 > 男装");
        engine.goBack();
    }

    //75.在导航栏滑动选择女包，看是否正常跳转
    @Test
    public void test_slide9() throws InterruptedException {
        engine.isDisplayed("xpath=//ul[@class='cat_list']");
        engine.slide("xpath=//ul[@class='cat_list']");
        engine.slide("xpath=//ul[@class='cat_list']/li[4]");
        engine.slide("xpath=//a[text()='女包']");
        engine.click("xpath=//a[text()='女包']");
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 > 服装 > 女包");
        engine.goBack();
    }

    //76.在导航栏滑动选择女装，看是否正常跳转
    @Test
    public void test_slide10() throws InterruptedException {
        engine.isDisplayed("xpath=//ul[@class='cat_list']");
        engine.slide("xpath=//ul[@class='cat_list']");
        engine.slide("xpath=//ul[@class='cat_list']/li[4]");
        engine.slide("xpath=//a[text()='女装']");
        engine.click("xpath=//a[text()='女装']");
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 > 服装 > 女装");
        engine.goBack();
    }

}
