package com.seleniumPlus.testcase;

import com.seleniumPlus.core.BaseTest;
import com.seleniumPlus.dataprovider.NSDataProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestDataProvider extends BaseTest {

    private String url = "http://localhost:8083/index.php?controller=admin&action=index";
    //进入会员界面
    protected void loginMember(){
        // 登录
        engine.open(url);
        engine.type("name=admin_name", "admin");
        engine.type("name=password", "password");
        engine.type("name=captcha", "abc");
        engine.click("xpath=//button[@type='submit']");

        // 进入会员界面
        engine.click("link=会员");
    }
    //进入广告界面
    protected void loginAdvertisement(){
        // 登录
        engine.open(url);
        engine.type("name=admin_name", "admin");
        engine.type("name=password", "password");
        engine.type("name=captcha", "abc");
        engine.click("xpath=//button[@type='submit']");

        // 进入广告界面
        engine.click("link=工具");
        engine.click("partLink=广告管理");
    }

    //用户充值操作
    protected void  recharge(String money){
        //点击全选
        engine.click("partLink=全选");
        //点击预付款管理
        engine.click("partLink=预付款管理");
        //选择充值账户余额100
        WebElement element = driver.findElement(By.tagName("iframe"));
        engine.enterFrame(element);
        engine.click("class=form-control");
        engine.click("tags=option1");
        engine.type("xpath=//input[@type='text']", money);
        engine.leaveFrame();
        driver.findElement(By.tagName("button")).click();
    }

    //进行用户提额操作
    protected void  withdrawal() throws InterruptedException {

        String urls = "http://localhost:8083/";
        //进行登录
        engine.open(urls);
        engine.click("partLink=登录");
        engine.type("name=login_info","test");
        engine.type("name=password","password");
        engine.click("class=input_submit");
        engine.slideDownToCenter();
        Thread.sleep(1000);
        //余额提现
        driver.findElement(By.cssSelector("nav.user_bar:nth-child(5) > ul:nth-child(2) > li:nth-child(1) > a:nth-child(1)")).click();
        engine.click("partLink=提现申请");
        engine.type("name=name","张三");
        engine.type("name=amount","100");
        engine.type("name=note","交易转账");
        engine.click("class=input_submit");

    }
    //添加会员组
    protected void addTeam(String gname,String dis,String min,String max){
        //点击进入会员组列表
        engine.click("partLink=会员组列表");
        //添加用户组
        engine.click("link=添加用户组");
        engine.type("name=group_name",gname);
        engine.type("name=discount",dis);
        engine.type("name=minexp",min);
        engine.type("name=maxexp",max);
        engine.click("xpath=//button[@class='btn btn-primary']");
    }
    //添加广告位
    protected void addAdvertisingSpace(String name){
        loginAdvertisement();
        //点击进入广告位列表
        engine.click("partLink=广告位列表");
        //添加广告位
        engine.click("partLink=添加广告位");
        engine.type("xpath=/html/body/div[2]/div/div[2]/form/table/tbody/tr[2]/td/input",name);
        engine.click("css=.btn");
    }
    //添加会员
    protected void addMember(String username, String password, String recomfirm, String name,String phone,String email){
        //点击添加会员
        engine.click("partLink=添加会员");
        // 填入会员信息
        engine.type("name=username", username);
        engine.type("name=password", password);
        engine.type("name=repassword", recomfirm);
        engine.type("name=true_name",name);
        engine.type("name=mobile",phone);
        engine.type("name=email",email);

        // 点击确定
        engine.click("xpath=//button[@type='submit']");
    }
    //    1.添加会员
    @Test(priority = 1,dataProvider="excel", dataProviderClass = NSDataProvider.class)
    public void testAddMember(String username, String password, String recomfirm, String name,String phone,String email ) {
        loginMember();
        addMember(username,password,recomfirm,name,phone,email);
        // 断言
        boolean displayed = engine.isDisplayed("xpath=//td[@title='test']");
        Assert.assertTrue(displayed);
    }
//2.重复添加会员
    @Test(priority = 2,dataProvider="excel", dataProviderClass = NSDataProvider.class)
    public void testAddMemberAgain(String username, String password, String recomfirm, String name,String phone,String email){
        loginMember();
        addMember(username,password,recomfirm,name,phone,email);
        // 断言
        String str="用户名重复";
        Assert.assertTrue(engine.isTextPresent(str));
    }
//3.成功充值余额为100元
    @Test(priority = 3)
    public void testRecharge(){
        //充值
        loginMember();
        recharge("100");
        //断言
        String str = "操作成功";
        Assert.assertTrue(engine.isTextPresent(str));
    }

    //4.充值余额为-100元
    @Test(priority = 4)
    public void testNegativeRecharge(){
       //充值
        loginMember();
        recharge("-100");

        //断言
        String str = "操作成功";
        Assert.assertTrue(engine.isTextPresent(str));
    }

    //5.成功提现
    @Test(priority = 5)
    public void testWithdrawal(){

        loginMember();

        //点击全选
        engine.click("partLink=全选");
        //点击预付款管理
        engine.click("partLink=预付款管理");
        //选择充值账户余额100
        WebElement element = driver.findElement(By.tagName("iframe"));
        engine.enterFrame(element);
        engine.click("class=form-control");
        engine.click("tags=option2");
        engine.type("xpath=//input[@type='text']", "200");
        engine.leaveFrame();
        driver.findElement(By.tagName("button")).click();

        //断言
        String str = "操作成功";
        Assert.assertTrue(engine.isTextPresent(str));
    }

    //6.超出余额提现
    @Test(priority = 6)
    public void testOverWithdrawal() throws InterruptedException {

        loginMember();

        //点击全选
        engine.click("partLink=全选");
        //点击预付款管理
        engine.click("partLink=预付款管理");
        //选择充值账户余额100
        WebElement element = driver.findElement(By.tagName("iframe"));
        engine.enterFrame(element);
        engine.click("class=form-control");
        engine.click("tags=option2");
        engine.type("xpath=//input[@type='text']", "100");
        engine.leaveFrame();
        driver.findElement(By.tagName("button")).click();

        //断言
        Thread.sleep(500);
        String str = "用户余额不足";
        Assert.assertTrue(engine.isTextPresent(str));
    }
    //7.用户名搜索用户
    @Test(priority = 7)
    public void testSearchUserName(){

        loginMember();

        //输入搜索内容
        engine.type("css=input.form-control","test");
        engine.click("css=button.btn");
        //断言
        boolean displayed = engine.isDisplayed("xpath=//td[@title='test']");
        Assert.assertTrue(displayed);
    }
    //8.姓名搜索用户
    @Test(priority = 8)
    public void testSearchName(){

        loginMember();

        //输入搜索内容
        engine.click("class=form-control");
        engine.click("tags=option1");
        engine.type("css=input.form-control","张三");
        engine.click("css=button.btn");
        //断言
        boolean displayed = engine.isTextPresent("张三");
        Assert.assertTrue(displayed);
    }
    //9.手机号搜索用户
    @Test(priority = 9)
    public void testSearchPhone(){

        loginMember();

        //输入搜索内容
        engine.click("class=form-control");
        engine.click("tags=option2");
        engine.type("css=input.form-control","15227134599");
        engine.click("css=button.btn");
        //断言
        boolean displayed = engine.isTextPresent("15227134599");
        Assert.assertTrue(displayed);
    }
    //10.邮箱搜索用户
    @Test(priority = 10)
    public void testSearchEmail(){

        loginMember();

        //输入搜索内容
        engine.click("class=form-control");
        engine.click("tags=option3");
        engine.type("css=input.form-control","123@qq.com");
        engine.click("css=button.btn");
        //断言
        boolean displayed = engine.isTextPresent("123@qq.com");
        Assert.assertTrue(displayed);
    }
    //11.批量删除成功
    @Test(priority = 11)
    public void testDelete(){

        loginMember();

        //点击全选
        engine.click("partLink=全选");
        //点击批量删除
        engine.click("partLink=批量删除");
        engine.click("class=aui_state_highlight");
        //断言
        boolean displayed = engine.isTextPresent("test");
        Assert.assertTrue(!displayed);
    }
    //12.重复批量删除
    @Test(priority = 12)
    public void testAgainDelete(){

        loginMember();

        //点击全选
        engine.click("partLink=全选");
        //点击批量删除
        engine.click("partLink=批量删除");
        engine.click("class=aui_state_highlight");
        //断言
        boolean displayed = engine.isTextPresent("尚未选中任何记录");
        engine.leaveFrame();
        Assert.assertTrue(displayed);
    }
    //13.还原回收站
    @Test(priority = 13)
    public void testReduction() {

        loginMember();

        //进入回收站
        engine.click("partLink=回收站");
        //点击全选
        engine.click("partLink=全选");
        //点击批量还原
        engine.click("partLink=还原");
        engine.click("class=aui_state_highlight");
        engine.click("partLink=返回列表");
        //断言
        boolean displayed = engine.isTextPresent("test");
        Assert.assertTrue(displayed);
    }
    //14.重复还原回收站
    @Test(priority = 14)
    public void testAgainReduction() throws InterruptedException {

        loginMember();

        //进入回收站
        engine.click("partLink=回收站");
        //点击全选
        engine.click("partLink=全选");
        //点击批量还原
        engine.click("partLink=还原");
        engine.click("class=aui_state_highlight");
        //断言
        Thread.sleep(500);
        boolean displayed = engine.isTextPresent("请选择要操作项");
        Assert.assertTrue(displayed);
    }
    //15.回收站批量删除
    @Test(priority = 15)
    public void testRecycleBin(){

        loginMember();

        //删除全部会员到回收站
        engine.click("partLink=全选");
        engine.click("partLink=批量删除");
        engine.click("css=.aui_state_highlight");
        //进入回收站
        engine.click("partLink=回收站");
        //点击全选
        engine.click("partLink=全选");
        //点击批量删除
        engine.click("partLink=永久删除");
        engine.click("class=aui_state_highlight");
        //断言
        boolean displayed = engine.isTextPresent("test");
        Assert.assertTrue(!displayed);
    }

    //16.回收站重复批量删除
    @Test(priority = 16)
    public void tesAgaintRecycleBin(){

        loginMember();

        //进入回收站
        engine.click("partLink=回收站");
        //点击全选
        engine.click("partLink=全选");
        //点击批量删除
        engine.click("partLink=永久删除");
        //断言
        boolean displayed = engine.isTextPresent("请选择要操作项");
        Assert.assertTrue(displayed);
    }
    //17.成功添加会员组
    @Test(priority = 17)
    public void testAddTeam(){

        loginMember();
        addTeam("111","10","12","555");
        //断言
        boolean displayed = engine.isTextPresent("111");
        Assert.assertTrue(displayed);
    }
    //18.重复添加会员组
    @Test(priority = 18)
    public void testAgainAddTeam(){

        loginMember();
        addTeam("111","10","12","555");
        //断言
        boolean displayed = engine.isTextPresent("111");
        Assert.assertTrue(displayed);
    }
    //19.添加会员组折扣率为负
    @Test(priority = 19)
    public void testAddNegativeTeam(){

        loginMember();
        addTeam("112","-10","12","555");
        //断言
        boolean displayed = engine.isTextPresent("112");
        Assert.assertTrue(displayed);
    }
    //20.添加会员组经验值为负
    @Test(priority = 20)
    public void testAddMin(){

        loginMember();
        addTeam("113","10","-12","555");
        //断言
        boolean displayed = engine.isTextPresent("113");
        Assert.assertTrue(displayed);
    }
    //21.添加会员组最大经验值小于最小经验值
    @Test(priority = 21)
    public void testAddError(){
        loginMember();
        addTeam("111","10","2","1");
        //断言
        boolean displayed = engine.isTextPresent("最大经验值必须比最小经验值大");
        Assert.assertTrue(displayed);
    }
    //22.删除会员组
    @Test(priority = 22)
    public void testDeleteTeam(){

        loginMember();

        //点击进入会员组列表
        engine.click("partLink=会员组列表");
        //删除用户组
        engine.click("link=全选");
        engine.click("link=批量删除");
        engine.click("class=aui_state_highlight");

        boolean displayed = engine.isTextPresent("111");
        Assert.assertTrue(!displayed);
    }
    //23.重复删除会员组
    @Test(priority = 23)
    public void testAgainDeleteTeam(){

        loginMember();

        //点击进入会员组列表
        engine.click("partLink=会员组列表");
        //删除用户组
        engine.click("link=全选");
        engine.click("link=批量删除");
        engine.click("class=aui_state_highlight");

        boolean displayed = engine.isTextPresent("尚未选中任何记录！");
        Assert.assertTrue(displayed);
    }
    //24.删除会员提现记录
    @Test(priority = 24,dataProvider="excel", dataProviderClass = NSDataProvider.class)
    public void testDeleteWithdrawal(String username, String password, String recomfirm, String name,String phone,String email) throws InterruptedException {
        //确保有提现记录
        loginMember();
        addMember(username,password,recomfirm,name,phone,email);
        recharge("100");
        withdrawal();
        loginMember();

        //点击进入会员提现管理
        engine.click("partLink=会员提现管理");
        //删除
        engine.click("link=全选");
        engine.click("link=批量删除");
        engine.click("class=aui_state_highlight");

        boolean displayed = engine.isTextPresent("test");
        Assert.assertTrue(!displayed);
    }
    //25.重复删除提现记录
    @Test(priority = 25)
    public void testAgainDeleteWithdrawal() throws InterruptedException {
        loginMember();

        //点击进入会员提现管理
        engine.click("partLink=会员提现管理");
        //删除
        engine.click("link=全选");
        engine.click("link=批量删除");
        engine.click("class=aui_state_highlight");
        //断言
        Assert.assertTrue(engine.isTextPresent("请选择要删除的数据"));
    }
    //26.恢复提现记录
    @Test(priority = 26)
    public void testRecoveryWithdrawal(){
        loginMember();

        //点击进入会员提现管理
        engine.click("partLink=会员提现管理");
        //恢复
        engine.click("link=回收站");
        engine.click("link=全选");
        engine.click("link=批量恢复");
        engine.click("class=aui_state_highlight");
        //断言
        Assert.assertTrue(engine.isTextPresent("test"));
    }
    //27.重复恢复
    @Test(priority = 27)
    public void testAgainRecoveryWithdrawal(){
        loginMember();

        //点击进入会员提现管理
        engine.click("partLink=会员提现管理");
        //删除用户组
        engine.click("link=回收站");
        engine.click("link=全选");
        engine.click("link=批量恢复");
        engine.click("class=aui_state_highlight");
        //断言
        Assert.assertTrue(engine.isTextPresent("请选择要删除的数据"));
    }
    //28.完全删除提现记录
    @Test(priority = 28)
    public void testDeleteAllWithdrawal(){
        loginMember();

        //点击进入会员提现管理
        engine.click("partLink=会员提现管理");
        //删除
        engine.click("link=全选");
        engine.click("link=批量删除");
        engine.click("class=aui_state_highlight");
        //回收站删除
        engine.click("link=回收站");
        engine.click("link=全选");
        engine.click("link=批量删除");
        engine.click("class=aui_state_highlight");
        //断言
        Assert.assertTrue(!engine.isTextPresent("test"));
    }
    //29.完全删除提现记录
    @Test(priority = 29)
    public void testAgainDeleteAllWithdrawal(){
        loginMember();

        //点击进入会员提现管理
        engine.click("partLink=会员提现管理");
        //回收站删除
        engine.click("link=回收站");
        engine.click("link=全选");
        engine.click("link=批量删除");
        engine.click("class=aui_state_highlight");
        //断言
        Assert.assertTrue(!engine.isTextPresent("请选择要删除的数据"));
    }
    //30.发送短信
    @Test(priority = 30)
    public void testSendMessage() throws InterruptedException {
        loginMember();

        //点击进入会员消息
        engine.click("partLink=会员消息");
        //发送短信
        engine.click("link=发送");
        WebElement element = driver.findElement(By.tagName("iframe"));
        engine.enterFrame(element);
        engine.type("xpath=/html/body/div[2]/form/div[2]/input","淘宝");
        engine.type("xpath=/html/body/div[2]/form/div[3]/div/div[2]/iframe","你好");
        engine.leaveFrame();
        Thread.sleep(1000);
        engine.click("class=aui_state_highlight");
        //断言
        Assert.assertTrue(engine.isTextPresent("发送短信成功"));
    }
    //31.发送空短信
    @Test(priority = 31)
    public void testSendNullMessage(){
        loginMember();

        //点击进入会员消息
        engine.click("partLink=会员消息");
        //发送短信
        engine.click("link=发送");
        engine.click("class=aui_state_highlight");
        //断言
        Assert.assertTrue(engine.isTextPresent("信息内容不能为空"));
    }
    //32.发送短信给存在的用户
    @Test(priority = 32)
    public void testSendToUser() throws InterruptedException {
        loginMember();

        //点击进入会员消息
        engine.click("partLink=会员消息");
        //发送短信
        engine.click("link=发送");
        WebElement element = driver.findElement(By.tagName("iframe"));
        engine.enterFrame(element);
        engine.click("class=form-control");
        engine.click("tags=option1");
        engine.type("css=#conditionBox > input:nth-child(1)","test");
        engine.type("xpath=/html/body/div[2]/form/div[2]/input","淘宝");
        engine.type("xpath=/html/body/div[2]/form/div[3]/div/div[2]/iframe","你好");
        engine.leaveFrame();
        Thread.sleep(1000);
        engine.click("class=aui_state_highlight");
        //断言
        Assert.assertTrue(engine.isTextPresent("发送短信成功"));
    }
    //33.发送短信给不存在的用户
    @Test(priority = 33)
    public void testSendToFailUser() throws InterruptedException {
        loginMember();

        //点击进入会员消息
        engine.click("partLink=会员消息");
        //发送短信
        engine.click("link=发送");
        WebElement element = driver.findElement(By.tagName("iframe"));
        engine.enterFrame(element);
        engine.click("class=form-control");
        engine.click("tags=option1");
        engine.type("css=#conditionBox > input:nth-child(1)","test01");
        engine.type("xpath=/html/body/div[2]/form/div[2]/input","淘宝");
        engine.type("xpath=/html/body/div[2]/form/div[3]/div/div[2]/iframe","你好");
        engine.leaveFrame();
        Thread.sleep(1000);
        engine.click("class=aui_state_highlight");
        //断言
        Assert.assertTrue(engine.isTextPresent("用户不存在"));
    }
    //34.发送短信给存在的用户组
    @Test(priority = 34)
    public void testSendToTeam() throws InterruptedException {
        //确保存在会员组及会员
        loginMember();
        addTeam("111","10","12","555");
        engine.click("partLink=会员列表");
        engine.click("css=.table > tbody:nth-child(5) > tr:nth-child(1) > td:nth-child(12) > a:nth-child(1) > i:nth-child(1)");
        engine.click("class=form-control");
        engine.click("tags=option1");
        engine.click("xpath=//button[@type='submit']");

        //点击进入会员消息
        engine.click("partLink=会员消息");
        //发送短信
        engine.click("link=发送");
        WebElement element = driver.findElement(By.tagName("iframe"));
        engine.enterFrame(element);
        engine.click("class=form-control");
        engine.click("tags=option2");
        engine.click("css=select.form-control:nth-child(1)");
        engine.click("css=select.form-control:nth-child(1) > option:nth-child(2)");
        engine.type("xpath=/html/body/div[2]/form/div[2]/input","淘宝");
        engine.type("xpath=/html/body/div[2]/form/div[3]/div/div[2]/iframe","你好");
        engine.leaveFrame();
        Thread.sleep(1000);
        engine.click("class=aui_state_highlight");
        //断言
        Assert.assertTrue(engine.isTextPresent("发送短信成功"));
    }
    //35.发送短信给空的用户组
    @Test(priority = 35)
    public void testSendToNullTeam() throws InterruptedException {
        //添加一个空会员组
        loginMember();
        addTeam("112","10","12","555");

        //点击进入会员消息
        engine.click("partLink=会员消息");
        //发送短信
        engine.click("link=发送");
        WebElement element = driver.findElement(By.tagName("iframe"));
        engine.enterFrame(element);
        engine.click("class=form-control");
        engine.click("tags=option2");
        engine.click("css=select.form-control:nth-child(1)");
        engine.click("css=select.form-control:nth-child(1) > option:nth-child(3)");
        engine.type("xpath=/html/body/div[2]/form/div[2]/input","淘宝");
        engine.type("xpath=/html/body/div[2]/form/div[3]/div/div[2]/iframe","你好");
        engine.leaveFrame();
        Thread.sleep(1000);
        engine.click("class=aui_state_highlight");
        //断言
        Assert.assertTrue(engine.isTextPresent("用户不存在"));
    }
    //36.发送短信给某手机号
    @Test(priority = 36)
    public void testSendToPhone() throws InterruptedException {
        loginMember();

        //点击进入会员消息
        engine.click("partLink=会员消息");
        //发送短信
        engine.click("link=发送");
        WebElement element = driver.findElement(By.tagName("iframe"));
        engine.enterFrame(element);
        engine.click("class=form-control");
        engine.click("tags=option3");
        engine.type("css=#conditionBox > input:nth-child(1)","15227134599");
        engine.type("xpath=/html/body/div[2]/form/div[2]/input","淘宝");
        engine.type("xpath=/html/body/div[2]/form/div[3]/div/div[2]/iframe","你好");
        engine.leaveFrame();
        Thread.sleep(1000);
        engine.click("class=aui_state_highlight");
        //断言
        Assert.assertTrue(engine.isTextPresent("发送短信成功"));
    }
    //37.发送短信给某空手机号
    @Test(priority = 37)
    public void testSendToNullPhone() throws InterruptedException {
        loginMember();

        //点击进入会员消息
        engine.click("partLink=会员消息");
        //发送短信
        engine.click("link=发送");
        WebElement element = driver.findElement(By.tagName("iframe"));
        engine.enterFrame(element);
        engine.click("class=form-control");
        engine.click("tags=option3");
        engine.type("css=#conditionBox > input:nth-child(1)","1522");
        engine.type("xpath=/html/body/div[2]/form/div[2]/input","淘宝");
        engine.type("xpath=/html/body/div[2]/form/div[3]/div/div[2]/iframe","你好");
        engine.leaveFrame();
        Thread.sleep(1000);
        engine.click("class=aui_state_highlight");
        //断言
        Assert.assertTrue(engine.isTextPresent("用户不存在"));
    }
    //38.发送短信给某积分区间
    @Test(priority = 38)
    public void testSendToIntegral() throws InterruptedException {
        loginMember();
        engine.click("partLink=会员列表");
        engine.click("css=.table > tbody:nth-child(5) > tr:nth-child(1) > td:nth-child(12) > a:nth-child(1) > i:nth-child(1)");
        engine.type("css=.table > tbody:nth-child(2) > tr:nth-child(15) > td:nth-child(2) > input:nth-child(1)","50");
        engine.click("xpath=//button[@type='submit']");
        //点击进入会员消息
        engine.click("partLink=会员消息");
        //发送短信
        engine.click("link=发送");
        WebElement element = driver.findElement(By.tagName("iframe"));
        engine.enterFrame(element);
        engine.click("class=form-control");
        engine.click("tags=option4");
        engine.type("css=#conditionBox > input:nth-child(1)","12");
        engine.type("css=input.form-control:nth-child(2)","555");
        engine.type("xpath=/html/body/div[2]/form/div[2]/input","淘宝");
        engine.type("xpath=/html/body/div[2]/form/div[3]/div/div[2]/iframe","你好");
        engine.leaveFrame();
        Thread.sleep(1000);
        engine.click("class=aui_state_highlight");
        //断言
        Assert.assertTrue(engine.isTextPresent("发送短信成功"));
    }
    //39.发送短信给某空积分区间
    @Test(priority = 39)
    public void testSendToNullIntegral() throws InterruptedException {
        loginMember();
        //点击进入会员消息
        engine.click("partLink=会员消息");
        //发送短信
        engine.click("link=发送");
        WebElement element = driver.findElement(By.tagName("iframe"));
        engine.enterFrame(element);
        engine.click("class=form-control");
        engine.click("tags=option4");
        engine.type("css=#conditionBox > input:nth-child(1)","1");
        engine.type("css=input.form-control:nth-child(2)","12");
        engine.type("xpath=/html/body/div[2]/form/div[2]/input","淘宝");
        engine.type("xpath=/html/body/div[2]/form/div[3]/div/div[2]/iframe","你好");
        engine.leaveFrame();
        Thread.sleep(1000);
        engine.click("class=aui_state_highlight");
        //断言
        Assert.assertTrue(engine.isTextPresent("用户不存在"));
    }
    //40.发送短信给某余额区间-显示用户不存在
    @Test(priority = 40)
    public void testSendToBalance() throws InterruptedException {
        loginMember();
        recharge("100");
        //点击进入会员消息
        engine.click("partLink=会员消息");
        //发送短信
        engine.click("link=发送");
        WebElement element = driver.findElement(By.tagName("iframe"));
        engine.enterFrame(element);
        engine.click("class=form-control");
        engine.click("tags=option5");
        engine.type("css=#conditionBox > input:nth-child(1)","0");
        engine.type("css=input.form-control:nth-child(2)","1000");
        engine.type("xpath=/html/body/div[2]/form/div[2]/input","淘宝");
        engine.type("xpath=/html/body/div[2]/form/div[3]/div/div[2]/iframe","你好");
        engine.leaveFrame();
        Thread.sleep(1000);
        engine.click("class=aui_state_highlight");
        //断言
        Assert.assertTrue(engine.isTextPresent("用户不存在"));
    }
    //41.发送短信给某余额区间-显示用户不存在
    @Test(priority = 41)
    public void testSendToNullBalance() throws InterruptedException {
        loginMember();
        //点击进入会员消息
        engine.click("partLink=会员消息");
        //发送短信
        engine.click("link=发送");
        WebElement element = driver.findElement(By.tagName("iframe"));
        engine.enterFrame(element);
        engine.click("class=form-control");
        engine.click("tags=option5");
        engine.type("css=#conditionBox > input:nth-child(1)","1000");
        engine.type("css=input.form-control:nth-child(2)","2000");
        engine.type("xpath=/html/body/div[2]/form/div[2]/input","淘宝");
        engine.type("xpath=/html/body/div[2]/form/div[3]/div/div[2]/iframe","你好");
        engine.leaveFrame();
        Thread.sleep(1000);
        engine.click("class=aui_state_highlight");
        //断言
        Assert.assertTrue(engine.isTextPresent("用户不存在"));
    }
    //42.删除所有短信
    @Test(priority = 42)
    public void testDeleteMessage(){
        loginMember();
        //点击进入会员消息
        engine.click("partLink=会员消息");
        engine.click("partLink=全选");
        engine.click("partLink=批量删除");
        engine.click("class=aui_state_highlight");
        //断言
        Assert.assertTrue(!engine.isTextPresent("淘宝"));
    }
    //43.删除空短信列表
    @Test(priority = 43)
    public void testDeleteNullMessage(){
        loginMember();
        //点击进入会员消息
        engine.click("partLink=会员消息");
        engine.click("partLink=批量删除");
        engine.click("class=aui_state_highlight");
        //断言
        Assert.assertTrue(engine.isTextPresent("尚未选中任何记录！"));
    }
    //44.添加广告位
    @Test(priority = 44)
    public void testAddAdvertisingSpace (){
        addAdvertisingSpace("111");
        //断言
        Assert.assertTrue(engine.isTextPresent("111"));
    }
    //45.重复添加广告位
    @Test(priority = 45)
    public void testAddAgainAdvertisingSpace(){
        addAdvertisingSpace("111");
        //断言
        Assert.assertTrue(engine.isTextPresent("111"));
    }
    //46.删除广告位
    @Test(priority = 46)
    public void testDeleteAdvertisingSpace() throws InterruptedException {
        loginAdvertisement();
        Thread.sleep(1000);
        //进入广告位管理
        engine.click("partLink=广告位列表");
        //删除
        engine.click("partLink=全选");
        engine.click("partLink=批量删除");
        engine.click("class=aui_state_highlight");

        //断言
        Assert.assertTrue(!engine.isTextPresent("111"));
    }
    //47.重复删除广告位
    @Test(priority = 47)
    public void testDeleteAgainAdvertisingSpace() throws InterruptedException {
        loginAdvertisement();
        Thread.sleep(1000);
        //进入广告位管理
        engine.click("partLink=广告位列表");
        //删除
        engine.click("partLink=批量删除");
        engine.click("class=aui_state_highlight");

        //断言
        Assert.assertTrue(engine.isTextPresent("请选择要删除的广告位"));
    }
    //48.添加广告
    @Test(priority = 48)
    public void testAddAdvertising() throws InterruptedException {
        addAdvertisingSpace("02");
        Thread.sleep(1000);
        //进入广告位管理
        engine.click("partLink=广告列表");
        //删除
        engine.click("partLink=添加广告");
        engine.type("xpath=/html/body/div[2]/div/div[2]/form/table/tbody/tr[2]/td/input","这是一条广告");
        engine.click("css=label.radio-inline:nth-child(3) > input:nth-child(1)");
        engine.click("css=select.form-control");
        engine.click("tags=option1");
        engine.type("xpath=/html/body/div[2]/div/div[2]/form/table/tbody/tr[7]/td/div/div[1]/div/input","2020-11-04");
        engine.type("css=div.col-xs-3:nth-child(2) > div:nth-child(1) > input:nth-child(2)","2020-11-30");
        engine.click("css=button.btn:nth-child(1)");

        //断言
        Assert.assertTrue(engine.isTextPresent("这是一条广告"));
    }
    //49.重复删除广告位
    @Test(priority = 49)
    public void testDeleteAdvertising() throws InterruptedException {
        loginAdvertisement();
        Thread.sleep(1000);
        //进入广告位管理
        engine.click("partLink=广告列表");
        //删除
        engine.click("partLink=全选");
        engine.click("partLink=批量删除");
        engine.click("class=aui_state_highlight");

        //断言
        Assert.assertTrue(!engine.isTextPresent("这是一条广告"));
    }
    //50.重复删除广告位
    @Test(priority = 50)
    public void testDeleteAgainAdvertising() throws InterruptedException {
        loginAdvertisement();
        Thread.sleep(1000);
        //进入广告位管理
        engine.click("partLink=广告列表");
        //删除
        engine.click("partLink=批量删除");
        engine.click("class=aui_state_highlight");

        //断言
        Assert.assertTrue(engine.isTextPresent("请选择要删除的广告"));
    }
}
