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
    @DataProvider(name = "excel_administrator")
    public Object[][] getExcelData() {
        return ExcelDataProvider.getExcelData(ReadPropUtil.getPropertyValue("administrator_excel_path"));
    }

    /**
     * excel数据提供者
     *
     * @return excel数据
     */
    @DataProvider(name = "excel_administrator_delete")
    public Object[][] getExcelData2() {
        return ExcelDataProvider.getExcelData(ReadPropUtil.getPropertyValue("administrator_delete_excel_path"));
    }

    /**
     * excel数据提供者
     *
     * @return excel数据
     */
    @DataProvider(name = "excel_merchant")
    public Object[][] getExcelData3() {
        return ExcelDataProvider.getExcelData(ReadPropUtil.getPropertyValue("merchant_excel_path"));
    }

    /**
     * excel数据提供者
     *
     * @return excel数据
     */
    @DataProvider(name = "excel_permission")
    public Object[][] getExcelData4() {
        return ExcelDataProvider.getExcelData(ReadPropUtil.getPropertyValue("permission_excel_path"));
    }

    /**
     * excel数据提供者
     *
     * @return excel数据
     */
    @DataProvider(name = "excel_role")
    public Object[][] getExcelData5() {
        return ExcelDataProvider.getExcelData(ReadPropUtil.getPropertyValue("role_excel_path"));
    }

    /**
     * mysql数据提供者
     *
     * @return 数据库数据
     */
    @DataProvider(name = "mysql")
    public Object[][] getDBData() {
        // ...
        return null;
    }
}
