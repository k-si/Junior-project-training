package com.seleniumPlus.dataprovider;


import com.seleniumPlus.utils.ReadPropUtil;
import org.testng.annotations.DataProvider;

/**
 * 提供多样的数据来源
 */
public class NSDataProvider {

	/**
	 * excel数据提供者
	 *
	 * @return excel数据
	 */
	@DataProvider(name="excel")
	public Object[][] getExcelData() {
		return ExcelDataProvider.getExcelData(ReadPropUtil.getPropertyValue("testcase_excel_path"));
	}

	/**
	 * mysql数据提供者
	 *
	 * @return 数据库数据
	 */
	@DataProvider(name="mysql")
	public Object[][] getDBData() {
		// ...
		return null;
	}
}
