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
	 * ͨ��ҳ��Ԫ�ػ���ҳ��
	 * @param locator
	 */
	public void slideByElement(String locator){
		WebElement element = finder.findElement(locator);

		if(element != null){
			runJs("arguements[0].scrollIntoView();" , element);
		}

	}

	/**
	 * �����ӵ�ǰλ�û�������ײ�
	 */
	public void slideDownToBottom() throws InterruptedException {

		//��ǰ�߶ȣ��൱�ڵ�ǰ������
		long currentHeight = (Long) runJs("return document.documentElement.scrollTop");
		//ҳ���ܸ߶ȣ��൱���յ�������
		long finalHeight = (Long) runJs("return document.body.scrollHeight");
		//��ǰ��ȣ��൱�ڵ�ǰ������
		long currentWidth = (Long) runJs("return document.documentElement.scrollLeft");


		while(currentHeight < finalHeight){

			//��Ҫ�ƶ���ƫ����
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
	 * ������ҳ�����
	 * @throws InterruptedException
	 */
	public void slideUpToTop() throws InterruptedException {
		//��ǰ�߶�
		long currentHeight = (Long) runJs("return document.documentElement.scrollTop");
		//�յ�������
		long finalHeight = 0L;
		//��ǰ��ȣ��൱�ڵ�ǰ������
		long currentWidth = (Long) runJs("return document.documentElement.scrollLeft");


		while(currentHeight > finalHeight){

			//��Ҫ�ƶ���ƫ����
			long offset = Math.min(25L , currentHeight - finalHeight);

			//ִ��JS�ű�
			runJs("window.scrollTo("
					+ currentWidth
					+ ","
					+ (currentHeight - offset)
					+ ")");

			//�ȴ�25����
			Thread.sleep(25);

			//���µ�ǰλ��
			currentHeight -= offset;
		}
	}

	/**
	 * ���������Ҷ�
	 * @throws InterruptedException
	 */
	public void slideToTheRight() throws InterruptedException {
		//��ǰ�߶�
		long currentHeight = (Long) runJs("return document.documentElement.scrollTop");
		//��ǰ��ȣ��൱�ڵ�ǰ������
		long currentWidth = (Long) runJs("return document.documentElement.scrollLeft");
		//ҳ���ܿ�ȣ��൱�����տ��
		long finalWidth = (Long) runJs("return document.body.scrollWidth");

		while(currentWidth < finalWidth){

			//��Ҫ�ƶ���ƫ����
			long offset = Math.min(25L , finalWidth - currentHeight);

			//ִ��JS�ű�
			runJs("window.scrollTo("
					+ (currentWidth + offset)
					+ ","
					+ currentHeight
					+ ")");

			//�ȴ�25����
			Thread.sleep(25);

			//���µ�ǰλ��
			currentWidth += offset;
		}
	}

	/**
	 * �����������
	 * @throws InterruptedException
	 */
	public void slideToTheLeft() throws InterruptedException {
		//��ǰ�߶�
		long currentHeight = (Long) runJs("return document.documentElement.scrollTop");
		//��ǰ��ȣ��൱�ڵ�ǰ������
		long currentWidth = (Long) runJs("return document.documentElement.scrollLeft");
		//ҳ���ܿ�ȣ��൱�����տ��
		long finalWidth = 0L;

		while(currentWidth > finalWidth){

			//��Ҫ�ƶ���ƫ����
			long offset = Math.min(25L , currentHeight - finalWidth);

			//ִ��JS�ű�
			runJs("window.scrollTo("
					+ (currentWidth - offset)
					+ ","
					+ currentHeight
					+ ")");

			//�ȴ�25����
			Thread.sleep(25);

			//���µ�ǰλ��
			currentWidth -= offset;
		}
	}


	/**
	 * ��ȡ���д��ڵı��⣬�����ص�����Ĵ���
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
	 * ͨ��frameId����һ��frame
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
	 * ͨ��frame��Ԫ�ؽ���frame
	 * @param locator
	 */
	public void enterFrame1(String locator) {
		WebElement element = finder.findElement(locator);
		this.pause(3000);
		driver.switchTo().frame(element);
		Log.info("Entered iframe " + element);
	}

	/**
	 * �ص�Ĭ�ϵ�frame
	 */
	public void leaveFrame() {
		driver.switchTo().defaultContent();
		Log.info("Left the iframe");
	}

	/**
	 * ���ض�������
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
	 * ��ȡ��ǰҳ��ı���
	 * @return
	 */
	public String getTitle() {
		return driver.getTitle();
	}

	/**
	 * Ӳ�Եȴ�������
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
	 * ȷ����ҳ���Ƿ����ĳ�������ַ���
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
	 * ����س�
	 */
	public void enter() {
		action.sendKeys(Keys.ENTER);
	}

	/**
	 * ������ݲ�����ָ��������
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
	 * ֱ����������
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
	 * �ж��Ƿ�ѡ��
	 * @param locator
	 * @return
	 */
	public boolean isChecked(String locator) {
		WebElement element = finder.findElement(locator);
		return element.isSelected();
	}

	/**
	 * ���Ԫ��
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
	 * ����
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
	 * ˫��
	 * @param locator
	 * @throws InterruptedException
	 */
	public void doubleClick(String locator) throws InterruptedException {
		WebElement element = finder.findElement(locator);

		action.doubleClick(element).build().perform();
	}

	/**
	 * �ж��Ƿ���ʾ��ҳ����
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
	 * ��ȡ�ı�����
	 * @param locator
	 * @return
	 */
	public String getText(String locator) {

		return finder.findElement(locator).getText().trim();
	}

	/**
	 * �ж�Ԫ���Ƿ����
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
	 * ��ȡvalue���Ե�ֵ
	 * @param locator
	 * @return
	 */
	public String getValue(String locator) {

		return finder.findElement(locator).getAttribute("value");
	}

	/**
	 * ��ȡ��ǰURL
	 * @return
	 */
	public String getUrl() {
		return driver.getCurrentUrl();
	}

	/**
	 * ������һ������
	 */
	public void goBack() {
		driver.navigate().back();
	}

	/**
	 * ǰ��һ��ҳ��
	 */
	public void goForward() {

		driver.navigate().forward();
	}

	/**
	 * ��ȡ��ǰҳ���ϵĵ�������
	 * @return
	 */
	public Alert getAlert() {
		Alert alert = driver.switchTo().alert();
		return alert;
	}

	/**
	 * ��ȡ�������ı�����
	 * @return
	 */
	public String getAlertTest() {

		return getAlert().getText();
	}

	/**
	 * ���������ȷ����ť
	 */
	public void alertAccept() {

		getAlert().accept();
	}

	/**
	 * ��ȡ�����б����
	 * @param locator
	 * @return
	 */
	public Select getSelect(String locator) {
		Select inputSelect = new Select(finder.findElement(locator));
		return inputSelect;
	}

	/**
	 * ͨ��valueֵ��ѡ�������б�ѡ��
	 * @param locator
	 * @param value
	 */
	public void selectByValue(String locator, String value) {
		getSelect(locator).selectByValue(value);
		this.pause(5000);
	}

	/**
	 * ͨ���ı�����ѡ�������б�
	 * @param locator
	 * @param value
	 */
	public void selectByVisibleText(String locator, String value) {
		getSelect(locator).selectByVisibleText(value);
	}

	/**
	 * ͨ���±���ѡ�������б��±��0��ʼ
	 * @param locator
	 * @param index
	 */
	public void selectByIndex(String locator, int index) {
		getSelect(locator).selectByIndex(index);
	}

	/**
	 * ��ȡҳ��Դ����
	 * @return
	 */
	public String getHtmlSource() {

		return driver.getPageSource();
	}

	/**
	 * ִ��JS�ű�
	 * @param js
	 */
	public Object runJs(String js) {
		JavascriptExecutor j = (JavascriptExecutor) driver;
		Object o = j.executeScript(js);
		return o;
	}

	/**
	 * ���ط�����������
	 * @param js
	 * @param var
	 */
	public Object runJs(String js , Object...var){
		JavascriptExecutor j = (JavascriptExecutor) driver;
		Object o = j.executeScript(js, var);

		return o;
	}

	/**
	 * ������ƶ���ָ��Ԫ����
	 * @param locator
	 * @throws InterruptedException
	 */
	public void mouseToElement(String locator) throws InterruptedException {
		action.moveToElement(finder.findElement(locator)).perform();
	}

	/**
	 * ������ƶ���ָ��Ԫ���ϲ����
	 * @param locator
	 * @throws InterruptedException
	 */
	public void mouseToElementandClick(String locator) throws InterruptedException {
		action.moveToElement(finder.findElement(locator)).click().perform();
	}

	/**
	 * �л�����
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
	 * ��ָ��Ԫ�����Ҽ�
	 * @param locator
	 * @throws InterruptedException
	 */
	public void rightClickMouse(String locator) throws InterruptedException {
		action.contextClick(finder.findElement(locator)).perform();
	}

	/**
	 * ����tab��
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
			// System.out.println(handle); //杩涘叆鍒扮浜屼釜椤甸潰
			windows.add(handle);
		}
		driver.switchTo().window(windows.get(i));
	}

}
