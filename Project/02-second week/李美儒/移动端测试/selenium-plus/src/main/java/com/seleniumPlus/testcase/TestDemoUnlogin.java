package com.seleniumPlus.testcase;

import com.seleniumPlus.core.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestDemoUnlogin extends BaseTest {

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

    //35.随意点击一个热销单品，跳转到相应商品界面后，点击立即购买，如果没有登录，是否提示登录
    @Test
    public void test_buy_process1() throws InterruptedException {
        engine.slideDownToBottom();
        engine.click("xpath=//div[@class='index_card_container']/a[1]");

        engine.click("xpath=//div[@id='buyNowButton']");
        engine.isTextPresent("已注册用户，请登录");

//        如果选择使用游客身份结账
        engine.click("xpath=//input[@value='acount']");
        engine.click("xpath=//*[text()='下一步']");
        Thread.sleep(2000);
//        String result=engine.getText("xpath=//section[@class='breadcrumb']");
//        System.out.print(result);
//        Assert.assertEquals(result,"您当前的位置： 首页 » 填写核对订单信息");
        engine.goBack();
        engine.goBack();
        engine.goBack();


    }

    //36.随意点击一个热销单品，跳转到相应商品界面后，点击立即购买，如果没有登录，是否提示登录
    //选择QQ第三方应用进行登录时，能正常跳转，但是不能完成登录
    @Test
    public void test_buy_process2() throws InterruptedException {
        engine.slideDownToBottom();
        engine.click("xpath=//div[@class='index_card_container']/a[1]");
        engine.click("xpath=//div[@id='buyNowButton']");
        engine.isTextPresent("已注册用户，请登录");
        //如果选择使用QQ第三方登录
        engine.click("xpath=//*[@class='login_box']/form/dl[4]/dd/a[1]");
        Thread.sleep(2000);
        engine.isTextPresent("param client_id is wrong or lost (100001)");
        engine.goBack();
        engine.goBack();
        engine.goBack();

    }

    //37.随意点击一个热销单品，跳转到相应商品界面后，点击立即购买，如果没有登录，是否提示登录
    //选择微博第三方应用进行登录时，能正常跳转，但是不能完成登录
    @Test
    public void test_buy_process3() throws InterruptedException {
        engine.slideDownToBottom();
        engine.click("xpath=//div[@class='index_card_container']/a[1]");
        engine.click("xpath=//div[@id='buyNowButton']");
        engine.isTextPresent("已注册用户，请登录");
        //如果选择使用微博第三方登录
        engine.click("xpath=//*[@class='login_box']/form/dl[4]/dd/a[2]");
        Thread.sleep(2000);
        engine.isTextPresent("用微博帐号登录出错了！");
        engine.goBack();
        engine.goBack();
        engine.goBack();
    }

    //38.随意点击一个热销单品，跳转到相应商品界面后，点击立即购买，如果没有登录，是否提示登录
    //选择注册新用户，正确跳转到用户注册
    @Test
    public void test_buy_process4() throws InterruptedException {
        engine.slideDownToBottom();
        engine.click("xpath=//div[@class='index_card_container']/a[1]");
        engine.click("xpath=//div[@id='buyNowButton']");
        engine.isTextPresent("已注册用户，请登录");
        //如果选择使用注册新用户
        if(!engine.isChecked("xpath=//input[@value='reg']")){
            engine.click("xpath=//input[@value='reg']");
        }
        engine.click("xpath=//*[text()='下一步']");
        Thread.sleep(2000);
        engine.isTextPresent("用户注册");
        engine.goBack();
        engine.goBack();
        engine.goBack();
    }

    //39.随意点击一个热销单品，跳转到相应商品界面后，点击立即购买，如果没有登录，是否提示登录
    //选择直接登录，输入正确的用户名和密码，跳转到填写核对订单信息
    @Test
    public void test_buy_process_login_success() throws InterruptedException {
        engine.slideDownToBottom();
        engine.click("xpath=//div[@class='index_card_container']/a[1]");
        engine.click("xpath=//div[@id='buyNowButton']");
        engine.isTextPresent("已注册用户，请登录");
        //如果选择直接登录
       engine.type("xpath=//input[@name='login_info']","admin");
       engine.type("xpath=//input[@name='password']","123456");
       engine.click("xpath=//input[@type='submit']");
       Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 » 填写核对订单信息");
       engine.goBack();
       engine.goBack();
       engine.goBack();
    }

    //40.随意点击一个热销单品，跳转到相应商品界面后，点击立即购买，如果没有登录，是否提示登录
    //选择直接登录，输入错误的账号名或者密码，提示账号或者密码错误
    @Test
    public void test_buy_process_login_fail() throws InterruptedException {
        engine.slideDownToBottom();
        engine.click("xpath=//div[@class='index_card_container']/a[1]");
        engine.slideDownToBottom();
        engine.click("xpath=//div[@id='buyNowButton']");
        engine.isTextPresent("已注册用户，请登录");
        //如果选择直接登录
        engine.type("xpath=//input[@name='login_info']","admin");
        engine.type("xpath=//input[@name='password']","12345678");
        engine.click("xpath=//input[@type='submit']");
        engine.isTextPresent("账号或密码错误 ");
        engine.goBack();
        engine.goBack();
        engine.goBack();
    }

    //41.随意点击一个商品，进入商品界面，未登录时点击收藏商品
    @Test
    public void test_click_favorite_unlogin() throws InterruptedException {
        engine.slideDownToBottom();
        engine.click("xpath=//div[@class='index_card_container']/a[1]");
        Thread.sleep(2000);
        engine.click("xpath=//span[@class='favorite']");
        engine.isTextPresent("请先登录");
        engine.goBack();
        engine.goBack();
    }

    //42.随意点击一个商品，进入商品界面，未登录时点击网友讨论圈进行发表话题
    @Test
    public void test_comment_unlogin() throws InterruptedException {
        engine.slideDownToBottom();
        engine.click("xpath=//div[@class='index_card_container']/a[1]");
        Thread.sleep(2000);
        engine.slideDownToBottom();
        engine.click("xpath=//label[contains(text(),'网友讨论圈')]");
        engine.click("xpath=//a[@name='discussButton']");
        engine.isTextPresent("请登陆后再发表讨论!");
        engine.click("xpath=//button[@class='aui_state_highlight']");
        engine.goBack();

    }

    //43.点击向右的箭头商品是否正常移动
    @Test
    public void test_click_right() throws InterruptedException {
        engine.slideDownToBottom();
        engine.click("xpath=//*[@id='home_rec_right']");
        engine.isElementPresent("xpath=//div[@id='home_rec']/ul/li[6]");
    }

    //44.点击向左的箭头商品是否正常移动
    @Test
    public void test_click_left() throws InterruptedException {
        engine.slideDownToBottom();
        engine.click("xpath=//*[@id='home_rec_left']");
        engine.isElementPresent("xpath=//div[@id='home_rec']/ul/li[6]");
    }

    //45.点击拼团活动的更多看能否正常跳转
    @Test
    public void test_click_more() throws InterruptedException {
        engine.slideDownToBottom();
        engine.click("xpath=//*[@class='home_floor assemble']/header/a");
        engine.isTextPresent("当前没有可以参加的拼团活动");
        engine.goBack();
    }

    //46.点击拼团活动的更多正常跳转后，选择点击返回上一级操作看能否跳转成功
    @Test
    public void test_click_more_back() throws InterruptedException {
        engine.slideDownToBottom();
        engine.click("xpath=//*[@class='home_floor assemble']/header/a");
        engine.isTextPresent("当前没有可以参加的拼团活动");
        engine.click("xpath=//a[text()='返回上一级操作']");
        engine.isTextPresent("首页");
    }

    //47.点击拼团活动的更多正常跳转后，选择点击网站首页，看能否跳转成功
    @Test
    public void test_click_more_home() throws InterruptedException {
        engine.slideDownToBottom();
        engine.click("xpath=//*[@class='home_floor assemble']/header/a");
        engine.isTextPresent("当前没有可以参加的拼团活动");
        engine.click("xpath=//a[text()='网站首页']");
        engine.isTextPresent("首页");
    }

    //48.点击拼团活动的更多正常跳转后，未登录前提下选择选择点击我的订单，看能否跳转成功
    //应该跳转到提示登录界面
    @Test
    public void test_click_more_order_unlogin() throws InterruptedException {
        engine.slideDownToBottom();
        engine.click("xpath=//*[@class='home_floor assemble']/header/a");
        engine.isTextPresent("当前没有可以参加的拼团活动");
        engine.click("xpath=//a[text()='我的订单']");
        engine.isTextPresent("已注册用户，请登录");
        engine.goBack();
        engine.goBack();
    }

    //49.点击拼团活动的更多正常跳转后，未登录前提下选择选择点击我的购物车，看能否跳转成功
    //跳转到了没有任何信息的购物车界面
    @Test
    public void test_click_more_cart_unlogin() throws InterruptedException {
        engine.slideDownToBottom();
        engine.click("xpath=//*[@class='home_floor assemble']/header/a");
        engine.isTextPresent("当前没有可以参加的拼团活动");
        engine.click("xpath=//a[text()='我的购物车']");
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 » 购物车");
        engine.click("xpath=//div[@class='go_back_btn']");
        engine.isTextPresent("当前没有可以参加的拼团活动");
        engine.goBack();
    }


    //59.商品数量显示测试，商品数量不得小于1，如果小于1看是否会有提示
    @Test
    public void test_show_number_reduce() throws InterruptedException {
        engine.slideDownToBottom();
        engine.click("xpath=//div[@class='index_card_container']/a[1]");
        Thread.sleep(2000);
        engine.slideAndClick("xpath=//span[@id='buyReduceButton']");
        engine.isTextPresent("此商品购买数量不得少于1");
        engine.click("xpath=//button[text()='确定']");
        engine.goBack();
    }

    //60.商品数量显示测试，商品数量大于库存数量+1，如果大于看是否会有提示
    @Test
    public void test_show_number_add() throws InterruptedException {
        engine.slideDownToBottom();
        engine.click("xpath=//div[@class='index_card_container']/a[1]");
        Thread.sleep(2000);
        String num=engine.getText("xpath=//span[@id='data_storeNums']");
        engine.clearAndType("xpath=//input[@id='buyNums']",num);
        engine.slideAndClick("xpath=//span[@id='buyAddButton']");
        engine.isTextPresent("此商品购买数量不得超过"+num);
        engine.click("xpath=//button[text()='确定']");
        engine.goBack();
    }

    //61.点击商品品牌,点击酒品牌
    //里面出现不属于酒品牌的商品品牌
    @Test
    public void test_click_start1() throws InterruptedException {
        engine.click("xpath=//*[@class='fa fa-star']");
        System.out.print(getBreadCrumb());
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 » 商品品牌");
        engine.click("xpath=//a[text()='酒品牌']");
        engine.isTextPresent("阿迪达斯");
        engine.goBack();
    }
    //62.点击商品品牌,点击牛奶
    //里面出现不属于牛奶的商品品牌
    @Test
    public void test_click_start2() throws InterruptedException {
        engine.click("xpath=//*[@class='fa fa-star']");
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 » 商品品牌");
        engine.click("xpath=//a[text()='牛奶']");
        engine.isTextPresent("邦威");
        engine.goBack();
    }

    //63.点击商品品牌,点击第一个衣服品牌分类
    //里面不出现任何衣服商品品牌信息,并且下面还有一个衣服品牌类别，所以这个品牌类别存在没有必要
    @Test
    public void test_click_start3() throws InterruptedException {
        engine.click("xpath=//*[@class='fa fa-star']");
        System.out.print(getBreadCrumb());
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 » 商品品牌");
        engine.click("xpath=//a[text()='衣服'][1]");
        engine.goBack();
    }

    //64.点击商品品牌,点击第一个衣服分类
    //里面不出现任何衣服商品品牌信息
    @Test
    public void test_click_start4() throws InterruptedException {
        engine.click("xpath=//*[@class='fa fa-star']");
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 » 商品品牌");
        engine.click("xpath=//a[text()='衣服'][2]");
        engine.isTextPresent("哥弟");
        engine.goBack();
    }

    //65.点击商品品牌,点击第一个衣服分类
    //里面不出现任何衣服商品品牌信息
    @Test
    public void test_click_start5() throws InterruptedException {
        engine.click("xpath=//*[@class='fa fa-star']");
        Assert.assertEquals(getBreadCrumb(),"您当前的位置： 首页 » 商品品牌");
        engine.click("xpath=//a[text()='商品']");
        engine.isTextPresent("匡威");
        engine.goBack();
    }

}
