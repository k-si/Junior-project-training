package com.seleniumPlus.dataprovider;


import com.seleniumPlus.utils.LogUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 提供excel读取功能
 */
public class ExcelDataProvider {

    /**
     * 读取excel表格，将结果保存在object二维数组里
     *
     * @param filePath excel文件路径
     * @return obj数据
     */
    public static Object[][] getExcelData(String filePath) {
        File file = new File(filePath);
        String fileName = file.getName();
        String extension = fileName.substring(fileName.indexOf('.') + 1);
        Workbook wb = null;
        try {
            // 新版本的xlsx对应XSFF，旧版本是xls对应HSSF
            switch (extension) {
                case "xlsx":
                    wb = new XSSFWorkbook(new FileInputStream(file));
                    break;
                case "xls":
                    wb = new HSSFWorkbook(new FileInputStream(file));
                    break;
                default:
                    LogUtil.error("测试用例文件不是excel！");
                    throw new RuntimeException("文件不是excel！");
            }
            Sheet sheet = wb.getSheet("sheet1");
            int rowCount = sheet.getLastRowNum();
            // 逐行读取放入list
            List<Object[]> list = new ArrayList<>();
            for (int i = 1; i <= rowCount; i++) {
                Row row = sheet.getRow(i);
                // 提取每行数据存进数组
                String[] record = new String[row.getLastCellNum()];
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    row.getCell(j).setCellType(CellType.STRING);
                    record[j] = row.getCell(j).getStringCellValue();
                }
                list.add(record);
            }
            // 每行数据放入Object数组
            Object[][] data = new Object[list.size()][];
            for (int i = 0; i < list.size(); i++) {
                data[i] = list.get(i);
            }
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}	
