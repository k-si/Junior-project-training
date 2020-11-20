package com.seleniumPlus.core;

import com.seleniumPlus.utils.LogUtil;
import com.seleniumPlus.utils.ReadPropUtil;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

/**
 * 测试基类，其他的测试类继承此类之后，可以通过engine进行页面操作
 */
public class BaseTest {

    /**
     * firefox driver系统配置参数 (System.setProperty)
     */
    private static final String FIREFOX_DRIVER_NAME = "webdriver.gecko.driver";

    /**
     * firefox driver系统配置参数 (System.setProperty)
     */
    private static final String FIREFOX_DRIVER_BIN = "webdriver.firefox.bin";

    /**
     * chrome driver系统配置参数 (System.setProperty)
     */
    private static final String CHROME_DRIVER_NAME = "webdriver.chrome.driver";

    /**
     * 封装了许多selenium原生方法，用来执行各种页面操作
     */
    protected WebDriverEngine engine;

    /**
     * 浏览器驱动
     */
    protected WebDriver driver;

    /**
     * 在所有测试类执行之前执行，实例化web driver
     * 创建WebDriverEngine对象
     */
    @BeforeClass
    public void doBeforeClass() {
        String driverType = ReadPropUtil.getPropertyValue("driverType");
        driver = this.newWebDriver(driverType);
        // 浏览器窗口最大化
        driver.manage().window().maximize();
        engine = new WebDriverEngine(driver);
    }

    /**
     * 在测试类执行结束之后执行，回收driver资源
     */
    @AfterClass
    public void doAfterClass() {
        if (this.driver != null) {
            this.driver.quit();
        }
        LogUtil.info("Quitted Browser");
    }

    /**
     * 通过配置文件获取想要的驱动（Firefox、chrome）
     *
     * @param driverType 驱动类型，String
     * @return 浏览器驱动对象
     */
    private WebDriver newWebDriver(String driverType) {
        if (driverType.equalsIgnoreCase("firefox")) {
            System.setProperty(FIREFOX_DRIVER_NAME, ReadPropUtil.getPropertyValue("gecko_driver"));
            System.setProperty(FIREFOX_DRIVER_BIN, ReadPropUtil.getPropertyValue("firefox_path"));
            driver = new FirefoxDriver();
            LogUtil.info("Using Firefox");
        } else if (driverType.equalsIgnoreCase("chrome")) {
            System.setProperty(CHROME_DRIVER_NAME, ReadPropUtil.getPropertyValue("chrome_path"));
            driver = new ChromeDriver();
            LogUtil.info("Using Chrome");
        }
        return driver;
    }

    /**
     * 获取该类或子类的web driver
     *
     * @return
     */
    protected WebDriver getDriver() {
        return driver;
    }
}
