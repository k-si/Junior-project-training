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
	@DataProvider(name="addAdvice")
	public Object[][] getExcelData() {
		return ExcelDataProvider.getExcelData(ReadPropUtil.getPropertyValue("testcase_excel_path"));
	}

	@DataProvider(name="Withdraw")
	public Object[][] getExcelData1() {
		return ExcelDataProvider.getExcelData(ReadPropUtil.getPropertyValue("testcase_excel_path1"));
	}

	@DataProvider(name="AddAddressFail")
	public Object[][] getExcelData2() {
		return ExcelDataProvider.getExcelData(ReadPropUtil.getPropertyValue("testcase_excel_path2"));
	}
	@DataProvider(name="addAddressSuccess")
	public Object[][] getExcelData3() {
		return ExcelDataProvider.getExcelData(ReadPropUtil.getPropertyValue("testcase_excel_path3"));
	}
	@DataProvider(name="addReceiptFail")
	public Object[][] getExcelData4() {
		return ExcelDataProvider.getExcelData(ReadPropUtil.getPropertyValue("testcase_excel_path4"));
	}
	@DataProvider(name="addReceiptSuccess")
	public Object[][] getExcelData5() {
		return ExcelDataProvider.getExcelData(ReadPropUtil.getPropertyValue("testcase_excel_path5"));
	}
	@DataProvider(name="addComment")
	public Object[][] getExcelData6() {
		return ExcelDataProvider.getExcelData(ReadPropUtil.getPropertyValue("testcase_excel_path6"));
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
