package com.seleniumPlus.reporter;

import org.testng.*;

import java.util.*;

/**
 * 获取测试结果信息类
 */
public class ReporterData {

    /**
     * 测试结果Set<ITestResult>转为list，再按执行时间排序 ，返回list
     *
     * @param res 测试结果集set
     * @return 返回排序后的集合list
     */
    private List<ITestResult> sortByTime(Set<ITestResult> res) {
        List<ITestResult> list = new ArrayList<>(res);
        Collections.sort(list);
        return list;
    }

    /**
     * 测试结果概览：
     * 所有用例数
     * 未执行的
     * 成功的
     * 失败的
     * 跳过的
     * 执行时间
     * 通过率
     *
     * @param context 测试过程中的上下文
     * @return 测试报告实体
     */
    public DataBean testContext(ITestContext context) {
        DataBean data = new DataBean();

        // 测试结果汇总数据
        IResultMap passedTests = context.getPassedTests();
        IResultMap failedTests = context.getFailedTests();
        IResultMap skipedTests = context.getSkippedTests();
        //全部测试周期方法，包括beforetest,beforeclass,beforemethod,aftertest,afterclass,aftermethod
        //IResultMap passedConfigurations =context.getPassedConfigurations();
        //IResultMap failedConfigurations =context.getFailedConfigurations();
        //IResultMap skipedConfigurations =context.getSkippedConfigurations();
        Collection<ITestNGMethod> excludeTests = context.getExcludedMethods();

        // 成功、失败、跳过、全部的测试方法数量
        int passedTestsSize = passedTests.size();
        int failedTestsSize = failedTests.size();
        int skipedTestsSize = skipedTests.size();
        int excludeTestsSize = excludeTests.size();

        // 所有测试结果的数量 = pass + fail + skip
        // 因为数据驱动一个测试方法有多次执行的可能，导致方法总数并不等于测试总数
        int allTestsSize = passedTestsSize + failedTestsSize + skipedTestsSize;
        data.setAllTestsSize(allTestsSize);
        data.setPassedTestsSize(passedTestsSize);
        data.setFailedTestsSize(failedTestsSize);
        data.setSkippedTestsSize(skipedTestsSize);
        data.setExcludeTestsSize(excludeTestsSize);
        data.setTestsTime(ReportUnits.getTestDuration(context)); // 设置测试执行时长
        data.setPassPercent(ReportUnits.formatPercentage(passedTestsSize, allTestsSize)); // 设置测试通过率
        data.setAllTestsMethod(context.getAllTestMethods()); // 设置全部执行的测试方法
        data.setExcludeTestsMethod(context.getExcludedMethods()); // 设置包含的全部测试方法
        return data;
    }


    /**
     * 测试结果详细数据：
     * 类名
     * 方法名
     * 用例描述
     * 执行结果
     * 执行时间
     * 报错信息
     *
     * @param map 测试结果集
     * @param status 测试结果状态
     * @return 测试报告实体集合，多行数据
     */
    public List<DataBean> testResults(IResultMap map, int status) {
        List<DataBean> list = new ArrayList<>();
        List<ITestResult> testResults = sortByTime(map.getAllResults());

        for (ITestResult result : testResults) {
            DataBean data = new DataBean();
            data.setTestName(result.getName());
            data.setClassName(result.getTestClass().getName());
            data.setDuration(ReportUnits.formatDuration(result.getEndMillis() - result.getStartMillis()));
            data.setParams(ReportUnits.getParams(result));
            data.setDescription(result.getMethod().getDescription());
            data.setOutput(Reporter.getOutput(result));
            data.setDependMethod(ReportUnits.getDependMethods(result));
            data.setThrowable(result.getThrowable());
            if (result.getThrowable() != null) {
                data.setStackTrace(result.getThrowable().getStackTrace());
            }
            list.add(data);
        }
        return list;
    }
}

