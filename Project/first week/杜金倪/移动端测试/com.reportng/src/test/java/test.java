import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class test{
    @BeforeClass
    public void beforeClass() {
        System.out.println("用例前执行打印本句！");
    }

    @AfterClass
    public void afterClass(){
        System.out.println("用例结束后运行");
    }

    @Test
    public  void case1() throws InterruptedException {
        //设置驱动所在位置
        System.setProperty("webdriver.gecko.driver", "D:/python all/python38/geckodriver.exe");
        System.setProperty("webdriver.firefox.bin", "D:/firefox/firefox.exe");        //引用谷歌浏览器驱动
        WebDriver driver = new FirefoxDriver();
        //窗口最大化
        driver.manage().window().maximize();
        //打开百度界面
        driver.get("http://www.baidu.com");
        //以下元素使用id
        //输入搜索关键字
        driver.findElement(By.id("kw")).sendKeys("123");
        //点击搜索按钮
        Thread.sleep(300);
        driver.findElement(By.id("su")).click();
        Thread.sleep(300);
        String a = null;
        try {
            a = driver.findElement(By.className("nums_text")).getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        //断言-校验是否登录成功
        Thread.sleep(300);
        Assert.assertEquals(a,"百度为您找到相关结果约100,000,000个");
        //关闭浏览器进程及驱动
        driver.close();
    }
}
