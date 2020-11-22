package com.seleniumPlus.testcase;

import com.seleniumPlus.core.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestSelenium extends BaseTest {

    private String url = "https://www.baidu.com";

    @Test
    public void testOpenSuccess() {
        engine.open(url);
        Assert.assertEquals("百度一下，你就知道", engine.getTitle());
    }

    @Test
    public void testOpenFail() {
        engine.open(url);
        Assert.assertEquals("谷歌浏览器", engine.getTitle());
    }
}
