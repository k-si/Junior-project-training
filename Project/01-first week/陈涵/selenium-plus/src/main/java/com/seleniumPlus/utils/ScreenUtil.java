package com.seleniumPlus.utils;

import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 屏幕记录工具类
 */
public class ScreenUtil {

    /**
     * 浏览器驱动
     */
    private WebDriver driver;

    /**
     * 构造方法，实例化浏览器驱动
     *
     * @param driver 浏览器驱动
     */
    public ScreenUtil(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * 获取失败用例的截屏，并输出到执行位置
     */
    public void screenShot() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String nowDateTime = sdf.format(new Date());
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(file, new File(ReadPropUtil.getPropertyValue("screen_shot_path") + nowDateTime + ".jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
