package com.seleniumPlus.core;

import com.seleniumPlus.utils.LogUtil;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * 对selenium原生方法的二次封装，包含了常用的ui操作
 */
public class WebDriverEngine {

    /**
     * 浏览器驱动com.doctorsAdvic
     */
    private WebDriver driver;

    /**
     * 用来定位元素
     */
    private ElementFinder finder;

    /**
     * 用来模拟键盘操作
     */
    private Actions action;

    /**
     * WebDriverEngine构造方法，同时实例化finder和action
     *
     * @param driver 浏览器驱动
     */
    public WebDriverEngine(WebDriver driver) {
        this.driver = driver;
        finder = new ElementFinder(driver);
        action = new Actions(driver);
    }

    /**
     * 通过页面元素滑动页面
     *
     * @param locator 定位的元素
     */
    public void slideByElement(String locator) {
        WebElement element = finder.findElement(locator);
        if (element != null) {
            runJs("arguements[0].scrollIntoView();", element);
        }
    }

    /**
     * 缓慢从当前位置滑动到最底部
     */
    public void slideDownToBottom() {
        //当前高度，相当于当前纵坐标
        long currentHeight = (Long) runJs("return document.documentElement.scrollTop");
        //页面总高度，相当于终点纵坐标
        long finalHeight = (Long) runJs("return document.body.scrollHeight");
        //当前宽度，相当于当前横坐标
        long currentWidth = (Long) runJs("return document.documentElement.scrollLeft");
        while (currentHeight < finalHeight) {
            //需要移动的偏移量
            long offset = Math.min(25L, finalHeight - currentHeight);
            runJs("window.scrollTo("
                    + currentWidth
                    + ","
                    + (currentHeight + offset)
                    + ")");
            pause(25);
            currentHeight += offset;
        }
    }
    /**
     * 缓慢从当前位置滑动到中上端
     */
    public void slideDownToCenter() {
        //当前高度，相当于当前纵坐标
        long currentHeight = (Long) runJs("return document.documentElement.scrollTop");
        //页面总高度，相当于终点纵坐标
        long finalHeight = (Long) runJs("return document.body.scrollHeight")/4;
        //当前宽度，相当于当前横坐标
        long currentWidth = (Long) runJs("return document.documentElement.scrollLeft");
        while (currentHeight < finalHeight) {
            //需要移动的偏移量
            long offset = Math.min(25L, finalHeight - currentHeight);
            runJs("window.scrollTo("
                    + currentWidth
                    + ","
                    + (currentHeight + offset)
                    + ")");
            pause(25);
            currentHeight += offset;
        }
    }


    /**
     * 滑动到页面最顶部
     */
    public void slideUpToTop() {
        //当前高度
        long currentHeight = (Long) runJs("return document.documentElement.scrollTop");
        //终点纵坐标
        long finalHeight = 0L;
        //当前宽度，相当于当前横坐标
        long currentWidth = (Long) runJs("return document.documentElement.scrollLeft");
        while (currentHeight > finalHeight) {
            //需要移动的偏移量
            long offset = Math.min(25L, currentHeight - finalHeight);
            //执行JS脚本
            runJs("window.scrollTo("
                    + currentWidth
                    + ","
                    + (currentHeight - offset)
                    + ")");
            //等待25毫秒
            pause(25);
            //更新当前位置
            currentHeight -= offset;
        }
    }

    /**
     * 华东道最右端
     */
    public void slideToTheRight() {
        //当前高度
        long currentHeight = (Long) runJs("return document.documentElement.scrollTop");
        //当前宽度，相当于当前横坐标
        long currentWidth = (Long) runJs("return document.documentElement.scrollLeft");
        //页面总宽度，相当于最终宽度
        long finalWidth = (Long) runJs("return document.body.scrollWidth");
        while (currentWidth < finalWidth) {
            //需要移动的偏移量
            long offset = Math.min(25L, finalWidth - currentHeight);
            //执行JS脚本
            runJs("window.scrollTo("
                    + (currentWidth + offset)
                    + ","
                    + currentHeight
                    + ")");
            //等待25毫秒
            pause(25);
            //更新当前位置
            currentWidth += offset;
        }
    }

    /**
     * 滑动到最左端
     */
    public void slideToTheLeft() {
        //当前高度
        long currentHeight = (Long) runJs("return document.documentElement.scrollTop");
        //当前宽度，相当于当前横坐标
        long currentWidth = (Long) runJs("return document.documentElement.scrollLeft");
        //页面总宽度，相当于最终宽度
        long finalWidth = 0L;
        while (currentWidth > finalWidth) {
            //需要移动的偏移量
            long offset = Math.min(25L, currentHeight - finalWidth);
            //执行JS脚本
            runJs("window.scrollTo("
                    + (currentWidth - offset)
                    + ","
                    + currentHeight
                    + ")");
            //等待25毫秒
            pause(25);
            //更新当前位置
            currentWidth -= offset;
        }
    }

    /**
     * 得到所有窗口的title
     *
     * @return 包含有所有title的数组
     */
    public String[] getAllWindowTitles() {
        String current = driver.getWindowHandle();
        List<String> attributes = new ArrayList<>();
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
            attributes.add(driver.getTitle());
        }
        driver.switchTo().window(current);
        return attributes.toArray(new String[0]);
    }

    /**
     * 进入网页中内含的iframe
     *
     * @param frameID frame的id，String类型
     */
    public void enterFrame(String frameID) {
        this.pause(3000);
        driver.switchTo().frame(frameID);
        LogUtil.info("Entered iframe " + frameID);
    }

    /**
     * 进入网页中内含的iframe
     *
     * @param frameID frame的id，int类型
     */
    public void enterFrame(int frameID) {
        this.pause(3000);
        driver.switchTo().frame(frameID);
        LogUtil.info("Entered iframe " + frameID);
    }

    /**
     * 进入网页中内含的iframe
     *
     * @param element frame元素，WebElement类型
     */
    public void enterFrame(WebElement element) {
        this.pause(3000);
        driver.switchTo().frame(element);
        LogUtil.info("Entered iframe " + element);
    }

    /**
     * 跳出iframe
     */
    public void leaveFrame() {
        driver.switchTo().defaultContent();
        LogUtil.info("Left the iframe");
    }

    /**
     * 打开一个url
     *
     * @param url url地址
     */
    public void open(String url) {
        try {
            driver.get(url);
            pause(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        LogUtil.info("Opened url " + url);
    }

    /**
     * 得到当前页面的title
     *
     * @return 一个title
     */
    public String getTitle() {
        return driver.getTitle();
    }

    /**
     * 线程等待几秒
     *
     * @param time 秒
     */
    public void pause(long time) {
        if (time <= 0) {
            return;
        }
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 查找网页中是否存在目标字符串
     *
     * @param pattern 目标字符串
     * @return boolean为true表示存在
     */
    public boolean isTextPresent(String pattern) {
        String text = driver.getPageSource();
        text = text.trim();
        if (text.contains(pattern)) {
            return true;
        }
        return false;
    }

    /**
     * 模拟点击回车健
     */
    public void enter() {
        action.sendKeys(Keys.ENTER);
    }

    /**
     * 找到文本框，清空文本框内容，输入执行内容
     *
     * @param locator 元素查找路径
     * @param value   要输入的内容
     */
    public void clearAndType(String locator, String value) {
        WebElement element = finder.findElement(locator);
        if (element != null) {
            element.clear();
            element.sendKeys(value);
        }
    }

    /**
     * 查找文本框，输入内容
     *
     * @param locator 元素查找路径
     * @param value   要输入的值
     */
    public void type(String locator, String value) {
        WebElement element = finder.findElement(locator);
        if (element != null) {
            element.sendKeys(value);
        }
    }

    /**
     * 判断要查找的元素是否被选中
     *
     * @param locator 元素查找路径
     * @return boolean为true表示被选中
     */
    public boolean isChecked(String locator) {
        WebElement element = finder.findElement(locator);
        return element.isSelected();
    }

    /**
     * 点击要查找的元素
     *
     * @param locator 元素查找路径
     */
    public void click(String locator) {
        WebElement element = finder.findElement(locator);
        if (element != null) {
            element.click();
            this.pause(3000);
        }
    }

    /**
     * 长按要查找的元素
     *
     * @param locator 元素查找路径
     */
    public void clickLonger(String locator) {
        WebElement element = finder.findElement(locator);
        if (element != null) {
            runJs("window.scrollTo(0," + element.getLocation().x + ")");
            element.click();
            this.pause(3000);
        }
    }

    /**
     * 双击两次要查找的元素
     *
     * @param locator 元素查找路径
     */
    public void doubleClick(String locator) {
        WebElement element = finder.findElement(locator);
        action.doubleClick(element).build().perform();
    }

    /**
     * 判断要查找的元素是否显示
     *
     * @param locator 元素查找路径
     * @return boolean为true表示已显示
     */
    public boolean isDisplayed(String locator) {
        WebElement element = finder.findElement(locator);
        if (element != null) {
            return element.isDisplayed();
        }
        return false;
    }

    /**
     * 获取要查找的元素的文本（去除前后括号）
     *
     * @param locator 元素查找路径
     * @return 元素text
     */
    public String getText(String locator) {
        return finder.findElement(locator).getText().trim();
    }

    /**
     * 判断元素是否存在
     *
     * @param locator 元素查找路径
     * @return boolean为true表示元素存在
     */
    public boolean isElementPresent(String locator) {
        WebElement element = null;
        try {
            element = finder.findElement(locator);
        } catch (Exception e) {
            LogUtil.info(e.getMessage());
        }
        return element != null;
    }

    /**
     * 获取元素的value值
     *
     * @param locator 元素查找路径
     * @return value值，String类型
     */
    public String getValue(String locator) {
        return finder.findElement(locator).getAttribute("value");
    }

    /**
     * 获取当前网页的url
     *
     * @return url地址
     */
    public String getUrl() {
        return driver.getCurrentUrl();
    }

    /**
     * 浏览器返回上一个页面
     */
    public void goBack() {
        driver.navigate().back();
    }

    /**
     * 浏览器前进下一个页面
     */
    public void goForward() {
        driver.navigate().forward();
    }

    /**
     * 获取alert弹窗组件
     *
     * @return 一个alert对象
     */
    public Alert getAlert() {
        return driver.switchTo().alert();
    }

    /**
     * 获取alert文本
     *
     * @return alert文本
     */
    public String getAlertTest() {
        return getAlert().getText();
    }

    /**
     * 点击alert的确定按钮
     */
    public void alertAccept() {
        getAlert().accept();
    }

    /**
     * 返回要查找的下拉列表
     *
     * @param locator 元素查找路径
     * @return 一个下拉列表对象
     */
    public Select getSelect(String locator) {
        return new Select(finder.findElement(locator));
    }

    /**
     * 通过value值，选中下拉列表的子项
     *
     * @param locator 元素查找路径
     * @param value   元素的value
     */
    public void selectByValue(String locator, String value) {
        getSelect(locator).selectByValue(value);
        this.pause(5000);
    }

    /**
     * 通过可见的值，选中下拉列表的子项
     *
     * @param locator 元素查找路径
     * @param value   元素的可见值
     */
    public void selectByVisibleText(String locator, String value) {
        getSelect(locator).selectByVisibleText(value);
    }

    /**
     * 通过下标，选中下拉列表子项
     *
     * @param locator 元素查找路径
     * @param index   元素下标
     */
    public void selectByIndex(String locator, int index) {
        getSelect(locator).selectByIndex(index);
    }

    /**
     * 获取页面源html代码
     *
     * @return html字符串
     */
    public String getHtmlSource() {
        return driver.getPageSource();
    }

    /**
     * 执行js语句
     *
     * @param js js字符串语句
     * @return js语句返回结果
     */
    public Object runJs(String js) {
        JavascriptExecutor j = (JavascriptExecutor) driver;
        return j.executeScript(js);
    }

    /**
     * 重载方法，带参数
     *
     * @param js  js语句
     * @param var 多个web element
     * @return js语句返回结果
     */
    public Object runJs(String js, Object... var) {
        JavascriptExecutor j = (JavascriptExecutor) driver;
        return j.executeScript(js, var);
    }

    /**
     * 鼠标移动的要查找的元素上
     *
     * @param locator 元素查找路径
     */
    public void mouseToElement(String locator) {
        action.moveToElement(finder.findElement(locator)).perform();
    }

    /**
     * 鼠标移动的要查找的元素上并且点击
     *
     * @param locator 元素查找路径
     */
    public void mouseToElementandClick(String locator) {
        action.moveToElement(finder.findElement(locator)).click().perform();
    }

    /**
     * 切换到指定的窗口
     *
     * @param i 浏览器窗口下标
     */
    public void switchWidow(int i) {
        List<String> windows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(i));
    }

    /**
     * 在要查找的元素上点击右键
     *
     * @param locator 元素查找下标
     */
    public void rightClickMouse(String locator) {
        action.contextClick(finder.findElement(locator)).perform();
    }

    /**
     * 点击Tab健
     */
    public void tapClick() {
        action.sendKeys(Keys.TAB).perform();
    }

    /**
     * 在当前光标所在处输入指定内容
     *
     * @param content 要输入的内容
     */
    public void tapType(String content) {
        action.sendKeys(content).perform();
    }
}
