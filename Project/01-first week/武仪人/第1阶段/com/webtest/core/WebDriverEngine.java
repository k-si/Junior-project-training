package com.webtest.core;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.webtest.utils.Log;
import com.webtest.utils.ReadProperties;

/**
 * author:lihuanzhen
 */
public class WebDriverEngine {

	WebDriver driver = null;
	ElementFinder finder = null;
	Actions action = null;

	public WebDriverEngine(WebDriver driver) {
		this.driver = driver;
		driver.manage().window().maximize();
		finder = new ElementFinder(driver);
		action = new Actions(driver);
	}

	/**
	 * 通过页面元素滑动页面
	 * @param locator
	 */
	public void slideByElement(String locator){
		WebElement element = finder.findElement(locator);

		if(element != null){
			runJs("arguements[0].scrollIntoView();" , element);
		}

	}

	/**
	 * 缓慢从当前位置滑动到最底部
	 */
	public void slideDownToBottom() throws InterruptedException {

		//当前高度，相当于当前纵坐标
		long currentHeight = (Long) runJs("return document.documentElement.scrollTop");
		//页面总高度，相当于终点纵坐标
		long finalHeight = (Long) runJs("return document.body.scrollHeight");
		//当前宽度，相当于当前横坐标
		long currentWidth = (Long) runJs("return document.documentElement.scrollLeft");


		while(currentHeight < finalHeight){

			//需要移动的偏移量
			long offset = Math.min(25L , finalHeight - currentHeight);

			runJs("window.scrollTo("
					+ currentWidth
					+ ","
					+ (currentHeight + offset)
					+ ")");

			Thread.sleep(25);
			currentHeight += offset;
		}
	}

	/**
	 * 滑动到页面最顶部
	 * @throws InterruptedException
	 */
	public void slideUpToTop() throws InterruptedException {
		//当前高度
		long currentHeight = (Long) runJs("return document.documentElement.scrollTop");
		//终点纵坐标
		long finalHeight = 0L;
		//当前宽度，相当于当前横坐标
		long currentWidth = (Long) runJs("return document.documentElement.scrollLeft");


		while(currentHeight > finalHeight){

			//需要移动的偏移量
			long offset = Math.min(25L , currentHeight - finalHeight);

			//执行JS脚本
			runJs("window.scrollTo("
					+ currentWidth
					+ ","
					+ (currentHeight - offset)
					+ ")");

			//等待25毫秒
			Thread.sleep(25);

			//更新当前位置
			currentHeight -= offset;
		}
	}

	/**
	 * 华东道最右端
	 * @throws InterruptedException
	 */
	public void slideToTheRight() throws InterruptedException {
		//当前高度
		long currentHeight = (Long) runJs("return document.documentElement.scrollTop");
		//当前宽度，相当于当前横坐标
		long currentWidth = (Long) runJs("return document.documentElement.scrollLeft");
		//页面总宽度，相当于最终宽度
		long finalWidth = (Long) runJs("return document.body.scrollWidth");

		while(currentWidth < finalWidth){

			//需要移动的偏移量
			long offset = Math.min(25L , finalWidth - currentHeight);

			//执行JS脚本
			runJs("window.scrollTo("
					+ (currentWidth + offset)
					+ ","
					+ currentHeight
					+ ")");

			//等待25毫秒
			Thread.sleep(25);

			//更新当前位置
			currentWidth += offset;
		}
	}

	/**
	 * 滑动到最左端
	 * @throws InterruptedException
	 */
	public void slideToTheLeft() throws InterruptedException {
		//当前高度
		long currentHeight = (Long) runJs("return document.documentElement.scrollTop");
		//当前宽度，相当于当前横坐标
		long currentWidth = (Long) runJs("return document.documentElement.scrollLeft");
		//页面总宽度，相当于最终宽度
		long finalWidth = 0L;

		while(currentWidth > finalWidth){

			//需要移动的偏移量
			long offset = Math.min(25L , currentHeight - finalWidth);

			//执行JS脚本
			runJs("window.scrollTo("
					+ (currentWidth - offset)
					+ ","
					+ currentHeight
					+ ")");

			//等待25毫秒
			Thread.sleep(25);

			//更新当前位置
			currentWidth -= offset;
		}
	}


	/**
	 * 获取所有窗口的标题，并返回到最初的窗口
	 * @return
	 */
	public String[] getAllWindowTitles() {
		String current = driver.getWindowHandle();

		List<String> attributes = new ArrayList<String>();
		for (String handle : driver.getWindowHandles()) {
			driver.switchTo().window(handle);
			attributes.add(driver.getTitle());
		}

		driver.switchTo().window(current);

		return attributes.toArray(new String[attributes.size()]);
	}

	/**
	 * 通过frameId进入一个frame
	 * @param frameID
	 */
	public void enterFrame(String frameID) {
		this.pause(3000);
		driver.switchTo().frame(frameID);
		Log.info("Entered iframe " + frameID);
	}


	public void enterFrame(int frameID) {
		this.pause(3000);
		driver.switchTo().frame(frameID);
		Log.info("Entered iframe " + frameID);
	}

	/**
	 * 通过frame的元素进入frame
	 * @param locator
	 */
	public void enterFrame1(String locator) {
		WebElement element = finder.findElement(locator);
		this.pause(3000);
		driver.switchTo().frame(element);
		Log.info("Entered iframe " + element);
	}

	/**
	 * 回到默认的frame
	 */
	public void leaveFrame() {
		driver.switchTo().defaultContent();
		Log.info("Left the iframe");
	}

	/**
	 * 打开特定的链接
	 * @param url
	 */
	public void open(String url) {

		try {
			driver.get(url);
			pause(5000);
		} catch (Exception e) {
			e.printStackTrace();

		}
		Log.info("Opened url " + url);
	}

	/**
	 * 获取当前页面的标题
	 * @return
	 */
	public String getTitle() {
		return driver.getTitle();
	}

	/**
	 * 硬性等待若干秒
	 * @param time
	 */
	private void pause(int time) {
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
	 * 确定网页中是否包含某个特殊字符串
	 * @param pattern
	 * @return
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
	 * 输入回车
	 */
	public void enter() {
		action.sendKeys(Keys.ENTER);
	}

	/**
	 * 清空内容并输入指定的内容
	 * @param locator
	 * @param value
	 */
	public void typeAndClear(String locator, String value) {
		WebElement element = finder.findElement(locator);
		if (element != null) {
			element.clear();
			element.sendKeys(value);
		}
	}

	/**
	 * 直接输入内容
	 * @param locator
	 * @param value
	 */
	public void type(String locator, String value) {
		WebElement element = finder.findElement(locator);
		if (element != null) {
			element.sendKeys(value);
		}
	}

	/**
	 * 判断是否被选中
	 * @param locator
	 * @return
	 */
	public boolean isChecked(String locator) {
		WebElement element = finder.findElement(locator);
		return element.isSelected();
	}

	/**
	 * 点击元素
	 * @param locator
	 */
	public void click(String locator) {

		WebElement element = finder.findElement(locator);
		if (element != null) {
			element.click();
			this.pause(3000);
		}
	}

	/**
	 * 长按
	 * @param locator
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
	 * 双击
	 * @param locator
	 * @throws InterruptedException
	 */
	public void doubleClick(String locator) throws InterruptedException {
		WebElement element = finder.findElement(locator);

		action.doubleClick(element).build().perform();
	}

	/**
	 * 判断是否显示在页面上
	 * @param locator
	 * @return
	 */
	public boolean isDisplayed(String locator) {

		WebElement element = finder.findElement(locator);
		if (element != null) {
			return element.isDisplayed();
		}
		return false;
	}

	/**
	 * 获取文本内容
	 * @param locator
	 * @return
	 */
	public String getText(String locator) {

		return finder.findElement(locator).getText().trim();
	}

	/**
	 * 判断元素是否存在
	 * @param locator
	 * @return
	 */
	public boolean isElementPresent(String locator) {

		WebElement element = null;
		try {
			element = finder.findElement(locator);
		} catch (Exception e) {

			Log.info(e.getMessage());
		}
		if (element != null) {
			return true;
		}
		{
			return false;
		}
	}

	/**
	 * 获取value属性的值
	 * @param locator
	 * @return
	 */
	public String getValue(String locator) {

		return finder.findElement(locator).getAttribute("value");
	}

	/**
	 * 获取当前URL
	 * @return
	 */
	public String getUrl() {
		return driver.getCurrentUrl();
	}

	/**
	 * 返回上一个界面
	 */
	public void goBack() {
		driver.navigate().back();
	}

	/**
	 * 前进一个页面
	 */
	public void goForward() {

		driver.navigate().forward();
	}

	/**
	 * 获取当前页面上的弹窗对象
	 * @return
	 */
	public Alert getAlert() {
		Alert alert = driver.switchTo().alert();
		return alert;
	}

	/**
	 * 获取弹窗的文本内容
	 * @return
	 */
	public String getAlertTest() {

		return getAlert().getText();
	}

	/**
	 * 点击弹窗的确定按钮
	 */
	public void alertAccept() {

		getAlert().accept();
	}

	/**
	 * 获取下拉列表对象
	 * @param locator
	 * @return
	 */
	public Select getSelect(String locator) {
		Select inputSelect = new Select(finder.findElement(locator));
		return inputSelect;
	}

	/**
	 * 通过value值来选择下拉列表选项
	 * @param locator
	 * @param value
	 */
	public void selectByValue(String locator, String value) {
		getSelect(locator).selectByValue(value);
		this.pause(5000);
	}

	/**
	 * 通过文本内容选择下拉列表
	 * @param locator
	 * @param value
	 */
	public void selectByVisibleText(String locator, String value) {
		getSelect(locator).selectByVisibleText(value);
	}

	/**
	 * 通过下标来选择下拉列表，下标从0开始
	 * @param locator
	 * @param index
	 */
	public void selectByIndex(String locator, int index) {
		getSelect(locator).selectByIndex(index);
	}

	/**
	 * 获取页面源代码
	 * @return
	 */
	public String getHtmlSource() {

		return driver.getPageSource();
	}

	/**
	 * 执行JS脚本
	 * @param js
	 */
	public Object runJs(String js) {
		JavascriptExecutor j = (JavascriptExecutor) driver;
		Object o = j.executeScript(js);
		return o;
	}

	/**
	 * 重载方法，带参数
	 * @param js
	 * @param var
	 */
	public Object runJs(String js , Object...var){
		JavascriptExecutor j = (JavascriptExecutor) driver;
		Object o = j.executeScript(js, var);

		return o;
	}

	/**
	 * 让鼠标移动到指定元素上
	 * @param locator
	 * @throws InterruptedException
	 */
	public void mouseToElement(String locator) throws InterruptedException {
		action.moveToElement(finder.findElement(locator)).perform();
	}

	/**
	 * 让鼠标移动到指定元素上并点击
	 * @param locator
	 * @throws InterruptedException
	 */
	public void mouseToElementandClick(String locator) throws InterruptedException {
		action.moveToElement(finder.findElement(locator)).click().perform();
	}

	/**
	 * 切换窗口
	 * @param i
	 */
	public void switchWidow(int i) {
		List<String> windows = new ArrayList<String>();
		for (String handle : driver.getWindowHandles()) {

			windows.add(handle);
		}
		driver.switchTo().window(windows.get(i));
	}

	/**
	 * 在指定元素上右键
	 * @param locator
	 * @throws InterruptedException
	 */
	public void rightClickMouse(String locator) throws InterruptedException {
		action.contextClick(finder.findElement(locator)).perform();
	}

	/**
	 * 按下tab键
	 */
	public void tapClick() {

		action.sendKeys(Keys.TAB).perform();
	}


	public void tapType(String content) {

		action.sendKeys(content).perform();
	}

	public void getWindow(int i) {
		List<String> windows = new ArrayList<String>();
		for (String handle : driver.getWindowHandles()) {
			// System.out.println(handle); //鏉╂稑鍙嗛崚鎵儑娴滃奔閲滄い鐢告桨
			windows.add(handle);
		}
		driver.switchTo().window(windows.get(i));
	}

}
