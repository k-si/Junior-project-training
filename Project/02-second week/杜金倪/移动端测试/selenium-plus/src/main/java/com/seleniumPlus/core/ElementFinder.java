package com.seleniumPlus.core;

import com.seleniumPlus.utils.LogUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * 该类用来查找元素，只需要对该类的findElement方法
 * 传入定位路径，如：id=""
 */
public class ElementFinder {

    /**
     * 浏览器驱动
     */
    private WebDriver driver;

    /**
     * ElementFinder构造方法
     *
     * @param driver 驱动
     */
    public ElementFinder(WebDriver driver) {
        this.driver = driver;
    }

    /**
     * 通过findElementByPrefix进一步解析target
     *
     * @param target 元素定位字符串
     * @return 找到的元素
     */
    public WebElement findElement(String target) {
        WebElement element = null;
        try {
            element = findElementByPrefix(target);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.error(e.toString());
        }
        return element;
    }

    /**
     * 通过前缀查找元素
     *
     * @param locator 通过前缀找到对应的查找方法
     * @return 找到的元素
     */
    public WebElement findElementByPrefix(String locator) {
        String target = locator.trim();
        if (target.startsWith("id=")) {
            locator = locator.substring("id=".length());
            return driver.findElement(By.id(locator));
        } else if (target.startsWith("class=")) {
            locator = locator.substring("class=".length());
            return driver.findElement(By.className(locator));
        } else if (target.startsWith("name=")) {
            locator = locator.substring("name=".length());
            return driver.findElement(By.name(locator));
        } else if (target.startsWith("link=")) {
            locator = locator.substring("link=".length());
            return driver.findElement(By.linkText(locator));
        } else if (target.startsWith("partLink=")) {
            locator = locator.substring("partLink=".length());
            return driver.findElement(By.partialLinkText(locator));
        } else if (target.startsWith("css=")) {
            locator = locator.substring("css=".length());
            return driver.findElement(By.cssSelector(locator));
        } else if (target.startsWith("xpath=")) {
            locator = locator.substring("xpath=".length());
            return driver.findElement(By.xpath(locator));
        } else if (target.startsWith("tag=")) {
            locator = locator.substring("tag=".length());
            return driver.findElement(By.tagName(locator));
        }else if (target.startsWith("tags=")) {
            Integer num= Integer.valueOf(locator.substring(locator.length()-1));
            locator = locator.substring("tags=".length(),locator.length()-1);
            WebElement element = driver.findElements(By.tagName(locator)).get(num);
            return element;
        }
         else {
            LogUtil.warn(locator + "can't find element by prefix.");
            return null;
        }
    }
}
