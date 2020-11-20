package com.seleniumPlus.core;

import com.seleniumPlus.utils.MailUtil;
import com.seleniumPlus.utils.ReadPropUtil;
import com.seleniumPlus.utils.ScreenUtil;
import com.seleniumPlus.utils.ZipUtil;
import org.testng.*;


/**
 * 测试方法执行时进行监听，对不同结果的测试用例做出响应
 */
public class WebTestListener extends TestListenerAdapter {

    /**
     * 测试完成后发送测试报告
     *
     * @param testContext 测试过程中上下文
     */
    @Override
    public void onFinish(ITestContext testContext) {
//        ZipUtil.compress(ReadPropUtil.getPropertyValue("output_path"));
//        MailUtil.send163Mail();
    }

    /**
     * 测试方法失败时触发，进行截屏
     *
     * @param tr 测试结果集
     */
    @Override
    public void onTestFailure(ITestResult tr) {
        // 失败的测试用例截屏
        BaseTest test = (BaseTest) tr.getInstance();
        new ScreenUtil(test.getDriver()).screenShot();
    }
}
