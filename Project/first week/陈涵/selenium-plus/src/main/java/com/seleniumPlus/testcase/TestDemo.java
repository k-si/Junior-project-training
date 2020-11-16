package com.seleniumPlus.testcase;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class TestDemo {

    @Test(description = "1等于1")
    public void testDemo1() {
        Assert.assertEquals(1, 1);
    }

    @Test
    public void testDemo2() {
        Assert.assertEquals(1, 2);
    }

    @Test
    public void testDemo3() {
        Assert.assertEquals(2, 2);
    }

    @Test
    public void testDemo4() {
        throw new SkipException("skip the test");
    }

    @Test
    public void testDemo5() {
        Assert.assertEquals(4, 4);
    }
}
