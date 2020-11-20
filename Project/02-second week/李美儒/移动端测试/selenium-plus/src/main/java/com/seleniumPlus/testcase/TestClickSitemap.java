package com.seleniumPlus.testcase;

import com.seleniumPlus.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestClickSitemap extends BaseTest {
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
    //13.在首页点击sitemap标签，进入到全部商品分类界面，点击大家电，查看商品能否正常展示，且是否分类成功
    @Test
    public void test_click_sitemap_cat1() throws InterruptedException {
        engine.click("xpath=//*[@class='fa fa-sitemap']");
        Thread.sleep(2000);
        engine.click("xpath=//section[@class='sitemap_list']/div[1]/dl[1]/dt/a");
        Thread.sleep(2000);
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 > 家用电器 > 大家电");
        engine.slideDownToBottom();
        //不属于大家电的iWebShop支付测试存在此商品展示页面
        engine.isElementPresent("xpath=//span[text()='iWebShop支付测试']");
        //查看各个商品页面之间能否正常跳转
        engine.goBack();
        Thread.sleep(2000);
        engine.goBack();
        Thread.sleep(2000);
    }


    //14.在首页点击sitemap标签，进入到全部商品分类界面，点击生活电器，查看商品能否正常展示，且是否分类成功
    @Test
    public void test_click_sitemap_cat2() throws InterruptedException {
        engine.click("xpath=//*[@class='fa fa-sitemap']");
        Thread.sleep(2000);
        engine.click("xpath=//section[@class='sitemap_list']/div[1]/dl[2]/dt/a");
        Thread.sleep(2000);
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 > 家用电器 > 生活电器");
        engine.slideDownToBottom();
        //查看各个商品页面之间能否正常跳转
        engine.goBack();
        Thread.sleep(2000);
        engine.goBack();
        Thread.sleep(2000);
    }

    //15.在首页点击sitemap标签，进入到全部商品分类界面，点击厨房电器，查看商品能否正常展示，且是否分类成功
    @Test
    public void test_click_sitemap_cat3() throws InterruptedException {
        engine.click("xpath=//*[@class='fa fa-sitemap']");
        Thread.sleep(2000);
        engine.click("xpath=//section[@class='sitemap_list']/div[1]/dl[3]/dt/a");
        Thread.sleep(2000);
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 > 家用电器 > 厨房电器");
        engine.slideDownToBottom();
        //存在不属于厨房电器的电视商品被展示
        engine.isTextPresent("电视");
        //查看各个商品页面之间能否正常跳转
        engine.goBack();
        Thread.sleep(2000);
        engine.goBack();
        Thread.sleep(2000);
    }

    //16.在首页点击sitemap标签，进入到全部商品分类界面，点击进口食品，查看商品能否正常展示，且是否分类成功
    @Test
    public void test_click_sitemap_cat4() throws InterruptedException {
        engine.click("xpath=//*[@class='fa fa-sitemap']");
        Thread.sleep(2000);
        engine.click("xpath=//section[@class='sitemap_list']/div[2]/dl[1]/dt/a");
        Thread.sleep(2000);
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 > 食品饮料 > 进口食品");
        engine.slideDownToBottom();
        //查看各个商品页面之间能否正常跳转
        engine.goBack();
        Thread.sleep(2000);
        engine.goBack();
        Thread.sleep(2000);
    }

    //17.在首页点击sitemap标签，进入到全部商品分类界面，点击酒品，查看商品能否正常展示，且是否分类成功
    @Test
    public void test_click_sitemap_cat5() throws InterruptedException {
        engine.click("xpath=//*[@class='fa fa-sitemap']");
        Thread.sleep(2000);
        engine.click("xpath=//section[@class='sitemap_list']/div[2]/dl[2]/dt/a");
        Thread.sleep(2000);
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 > 食品饮料 > 酒品");
        engine.slideDownToBottom();
        //查看各个商品页面之间能否正常跳转
        engine.goBack();
        Thread.sleep(2000);
        engine.goBack();
        Thread.sleep(2000);
    }

    //18.在首页点击sitemap标签，进入到全部商品分类界面，点击家装建材，查看商品能否正常展示，且是否分类成功
    @Test
    public void test_click_sitemap_cat6() throws InterruptedException {
        engine.click("xpath=//*[@class='fa fa-sitemap']");
        Thread.sleep(2000);
        engine.click("xpath=//section[@class='sitemap_list']/div[3]/dl[1]/dt/a");
        Thread.sleep(2000);
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 > 家具 > 家装建材");
        engine.slideDownToBottom();
        //查看各个商品页面之间能否正常跳转
        engine.goBack();
        Thread.sleep(2000);
        engine.goBack();
        Thread.sleep(2000);
    }

    //19.在首页点击sitemap标签，进入到全部商品分类界面，点击卧室家具，查看商品能否正常展示，且是否分类成功
    @Test
    public void test_click_sitemap_cat7() throws InterruptedException {
        engine.click("xpath=//*[@class='fa fa-sitemap']");
        Thread.sleep(2000);
        engine.click("xpath=//section[@class='sitemap_list']/div[3]/dl[2]/dt/a");
        Thread.sleep(2000);
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 > 家具 > 卧室家具");
        engine.slideDownToBottom();
        //查看各个商品页面之间能否正常跳转
        engine.goBack();
        Thread.sleep(2000);
        engine.goBack();
        Thread.sleep(2000);
    }

    //20.在首页点击sitemap标签，进入到全部商品分类界面，点击男装，查看商品能否正常展示，且是否分类成功
    @Test
    public void test_click_sitemap_cat8() throws InterruptedException {
        engine.click("xpath=//*[@class='fa fa-sitemap']");
        Thread.sleep(2000);
        engine.click("xpath=//section[@class='sitemap_list']/div[4]/dl[1]/dt/a");
        Thread.sleep(2000);
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 > 服装 > 男装");
        engine.slideDownToBottom();
        //存在不属于男装商品类别的女士商品
        engine.isTextPresent("女");
        //查看各个商品页面之间能否正常跳转
        engine.goBack();
        Thread.sleep(2000);
        engine.goBack();
        Thread.sleep(2000);
    }

    //21.在首页点击sitemap标签，进入到全部商品分类界面，点击男装，查看商品能否正常展示，且是否分类成功
    @Test
    public void test_click_sitemap_cat9() throws InterruptedException {
        engine.click("xpath=//*[@class='fa fa-sitemap']");
        Thread.sleep(2000);
        engine.click("xpath=//section[@class='sitemap_list']/div[4]/dl[1]/dt/a");
        Thread.sleep(2000);
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 > 服装 > 男装");
        engine.slideDownToBottom();
        //存在不属于男装商品类别的女士商品
        engine.isTextPresent("女");
        //查看各个商品页面之间能否正常跳转
        engine.goBack();
        Thread.sleep(2000);
        engine.goBack();
        Thread.sleep(2000);
    }

    //22.在首页点击sitemap标签，进入到全部商品分类界面，点击女包，查看商品能否正常展示，且是否分类成功
    @Test
    public void test_click_sitemap_cat10() throws InterruptedException {
        engine.click("xpath=//*[@class='fa fa-sitemap']");
        Thread.sleep(2000);
        engine.click("xpath=//section[@class='sitemap_list']/div[4]/dl[2]/dt/a");
        Thread.sleep(2000);
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 > 服装 > 女包");
        engine.slideDownToBottom();
        //查看各个商品页面之间能否正常跳转
        engine.goBack();
        Thread.sleep(2000);
        engine.goBack();
        Thread.sleep(2000);
    }

    //23.在首页点击sitemap标签，进入到全部商品分类界面，点击女装，查看商品能否正常展示，且是否分类成功
    @Test
    public void test_click_sitemap_cat11() throws InterruptedException {
        engine.click("xpath=//*[@class='fa fa-sitemap']");
        Thread.sleep(2000);
        engine.click("xpath=//section[@class='sitemap_list']/div[4]/dl[3]/dt/a");
        Thread.sleep(2000);
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 > 服装 > 女装");
        engine.slideDownToBottom();
        //查看各个商品页面之间能否正常跳转
        engine.goBack();
        Thread.sleep(2000);
        engine.goBack();
        Thread.sleep(2000);
    }

    //24.在首页点击sitemap标签，进入到全部商品分类界面，点击平板电视，查看商品能否正常展示，且是否分类成功
    @Test
    public void test_click_sitemap_cat12() throws InterruptedException {
        engine.click("xpath=//*[@class='fa fa-sitemap']");
        Thread.sleep(2000);
        engine.click("xpath=//section[@class='sitemap_list']/div[1]/dl[1]/dd/a");
        Thread.sleep(2000);
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 > 家用电器 > 大家电 > 平板电视");
        engine.slideDownToBottom();
        engine.isElementPresent("xpath=//span[text()='iWebShop支付测试']");
        //查看各个商品页面之间能否正常跳转
        engine.goBack();
        Thread.sleep(2000);
        engine.goBack();
        Thread.sleep(2000);

    }


    //25.在首页点击sitemap标签，进入到全部商品分类界面，点击电风扇，查看商品能否正常展示，且是否分类成功
    @Test
    public void test_click_sitemap_cat13() throws InterruptedException {
        engine.click("xpath=//*[@class='fa fa-sitemap']");
        Thread.sleep(2000);
        engine.click("xpath=//section[@class='sitemap_list']/div[1]/dl[2]/dd/a[1]");
        Thread.sleep(2000);
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 > 家用电器 > 生活电器 > 电风扇");
        engine.slideDownToBottom();
        //存在不属于电风扇类别的电视商品信息
        engine.isTextPresent("电视");
        //查看各个商品页面之间能否正常跳转
        engine.goBack();
        Thread.sleep(2000);
        engine.goBack();
        Thread.sleep(2000);
    }

    //26.在首页点击sitemap标签，进入到全部商品分类界面，点击冷风扇，查看商品能否正常展示，且是否分类成功
    @Test
    public void test_click_sitemap_cat14() throws InterruptedException {
        engine.click("xpath=//*[@class='fa fa-sitemap']");
        Thread.sleep(2000);
        engine.click("xpath=//section[@class='sitemap_list']/div[1]/dl[2]/dd/a[2]");
        Thread.sleep(2000);
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 > 家用电器 > 生活电器 > 冷风扇");
        engine.slideDownToBottom();
        //全部显示不属于冷风扇类别的电视商品信息
        engine.isTextPresent("电视");
        //查看各个商品页面之间能否正常跳转
        engine.goBack();
        Thread.sleep(2000);
        engine.goBack();
        Thread.sleep(2000);
    }

    //27.在首页点击sitemap标签，进入到全部商品分类界面，点击扫地机器人，查看商品能否正常展示，且是否分类成功
    @Test
    public void test_click_sitemap_cat15() throws InterruptedException {
        engine.click("xpath=//*[@class='fa fa-sitemap']");
        Thread.sleep(2000);
        engine.click("xpath=//section[@class='sitemap_list']/div[1]/dl[2]/dd/a[3]");
        Thread.sleep(2000);
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 > 家用电器 > 生活电器 > 扫地机器人");
        engine.slideDownToBottom();
        //存在不属于扫地机器人类别的电视商品信息
        engine.isTextPresent("电视");
        //查看各个商品页面之间能否正常跳转
        engine.goBack();
        Thread.sleep(2000);
        engine.goBack();
        Thread.sleep(2000);
    }

    //28.在首页点击sitemap标签，进入到全部商品分类界面，点击电饭煲，查看商品能否正常展示，且是否分类成功
    @Test
    public void test_click_sitemap_cat16() throws InterruptedException {
        engine.click("xpath=//*[@class='fa fa-sitemap']");
        Thread.sleep(2000);
        engine.click("xpath=//section[@class='sitemap_list']/div[1]/dl[3]/dd/a[1]");
        Thread.sleep(2000);
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 > 家用电器 > 厨房电器 > 电饭煲");
        engine.slideDownToBottom();
        //存在不属于电饭煲类别的电视商品信息
        engine.isTextPresent("电视");
        //查看各个商品页面之间能否正常跳转
        engine.goBack();
        Thread.sleep(2000);
        engine.goBack();
        Thread.sleep(2000);
    }

    //29.在首页点击sitemap标签，进入到全部商品分类界面，点击微波炉，查看商品能否正常展示，且是否分类成功
    @Test
    public void test_click_sitemap_cat17() throws InterruptedException {
        engine.click("xpath=//*[@class='fa fa-sitemap']");
        Thread.sleep(2000);
        engine.click("xpath=//section[@class='sitemap_list']/div[1]/dl[3]/dd/a[2]");
        Thread.sleep(2000);
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 > 家用电器 > 厨房电器 > 微波炉");
        engine.slideDownToBottom();
        //全部显示不属于微波炉类别的电视商品信息
        engine.isTextPresent("电视");
        //查看各个商品页面之间能否正常跳转
        engine.goBack();
        Thread.sleep(2000);
        engine.goBack();
        Thread.sleep(2000);
    }

    //30.在首页点击sitemap标签，进入到全部商品分类界面，点击牛奶，查看商品能否正常展示，且是否分类成功
    @Test
    public void test_click_sitemap_cat18() throws InterruptedException {
        engine.click("xpath=//*[@class='fa fa-sitemap']");
        Thread.sleep(2000);
        engine.click("xpath=//section[@class='sitemap_list']/div[2]/dl[1]/dd/a");
        Thread.sleep(2000);
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 > 食品饮料 > 进口食品 > 牛奶");
        engine.slideDownToBottom();
        //查看各个商品页面之间能否正常跳转
        engine.goBack();
        Thread.sleep(2000);
        engine.goBack();
        Thread.sleep(2000);
    }

    //31.在首页点击sitemap标签，进入到全部商品分类界面，点击红酒和白酒，查看商品能否正常展示，且是否分类成功
    @Test
    public void test_click_sitemap_cat19() throws InterruptedException {
        engine.click("xpath=//*[@class='fa fa-sitemap']");
        Thread.sleep(2000);
        engine.click("xpath=//section[@class='sitemap_list']/div[2]/dl[2]/dd/a");
        Thread.sleep(2000);
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 > 食品饮料 > 酒品 > 红酒和白酒");
        engine.slideDownToBottom();
        //查看各个商品页面之间能否正常跳转
        engine.goBack();
        Thread.sleep(2000);
        engine.goBack();
        Thread.sleep(2000);
    }

    //32.在首页点击sitemap标签，进入到全部商品分类界面，点击灯饰照明，查看商品能否正常展示，且是否分类成功
    @Test
    public void test_click_sitemap_cat20() throws InterruptedException {
        engine.click("xpath=//*[@class='fa fa-sitemap']");
        Thread.sleep(2000);
        engine.click("xpath=//section[@class='sitemap_list']/div[3]/dl[1]/dd/a");
        Thread.sleep(2000);

        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 > 家具 > 家装建材 > 灯饰照明");
        engine.slideDownToBottom();
        //查看各个商品页面之间能否正常跳转
        engine.goBack();
        Thread.sleep(2000);
        engine.goBack();
        Thread.sleep(2000);
    }
    //33.在首页点击sitemap标签，进入到全部商品分类界面，点击实木床，查看商品能否正常展示，且是否分类成功
    @Test
    public void test_click_sitemap_cat21() throws InterruptedException {
        engine.click("xpath=//*[@class='fa fa-sitemap']");
        Thread.sleep(2000);
        engine.click("xpath=//section[@class='sitemap_list']/div[3]/dl[2]/dd/a");
        Thread.sleep(2000);
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 > 家具 > 卧室家具 > 实木床");
        engine.slideDownToBottom();
        //存在不属于实木床类别的床垫商品信息
        engine.isTextPresent("床垫");
        //查看各个商品页面之间能否正常跳转
        engine.goBack();
        Thread.sleep(2000);
        engine.goBack();
        Thread.sleep(2000);
    }

    //34.在首页点击sitemap标签，进入到全部商品分类界面，点击衬衫，查看商品能否正常展示，且是否分类成功
    @Test
    public void test_click_sitemap_cat22() throws InterruptedException {
        engine.click("xpath=//*[@class='fa fa-sitemap']");
        Thread.sleep(2000);
        engine.click("xpath=//section[@class='sitemap_list']/div[4]/dl[1]/dd/a");
        Thread.sleep(2000);
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 > 服装 > 男装 > 衬衫");
        engine.slideDownToBottom();
        //存在大量不属于衬衫类别的民族舞台服商品
        engine.isTextPresent("舞台服");
        //查看各个商品页面之间能否正常跳转
        engine.goBack();
        Thread.sleep(2000);
        engine.goBack();
        Thread.sleep(2000);
    }
}
