package com.seleniumPlus.reporter;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 测试报告生成类
 */
public class GenerateReporter implements IReporter {

    /**
     * 生成测试报告
     *
     * @param xmlSuites xml中的suite标签
     * @param suites suite测试套件，集合了测试的结果
     * @param outputDirectory 输出目录
     */
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        try {
            // freemaker的配置
            Configuration cfg = new Configuration();
            cfg.setClassForTemplateLoading(this.getClass(), "/template");
            cfg.setDefaultEncoding("UTF-8");
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

            //freemaker的模板文件
            Template temp = cfg.getTemplate("overview.ftl");

            // 所有测试结果信息集合
            Map context = new HashMap();

            for (ISuite suite : suites) {
                Map<String, ISuiteResult> suiteResults = suite.getResults();
                for (ISuiteResult suiteResult : suiteResults.values()) {
                    ReporterData data = new ReporterData();
                    ITestContext testContext = suiteResult.getTestContext();

                    // 把数据填入上下文map
                    // overview作为执行结果的总览
                    context.put("overView", data.testContext(testContext)); // 测试结果汇总信息

                    // 详细的测试结果
                    //ITestNGMethod[] allTests = testContext.getAllTestMethods(); // 所有的测试方法
                    //Collection<ITestNGMethod> excludeTests = testContext.getExcludedMethods(); // 未执行的测试方法
                    IResultMap passedTests = testContext.getPassedTests(); // 测试通过的测试方法
                    IResultMap failedTests = testContext.getFailedTests(); // 测试失败的测试方法
                    IResultMap skippedTests = testContext.getSkippedTests(); // 测试跳过的测试方法
                    context.put("pass", data.testResults(passedTests, ITestResult.SUCCESS));
                    context.put("fail", data.testResults(failedTests, ITestResult.FAILURE));
                    context.put("skip", data.testResults(skippedTests, ITestResult.FAILURE));
                }
            }

            // 输出流
            // D:\Java\idea_Projects\iwebshop\report.html
            OutputStream out = new FileOutputStream("report.html");
            Writer writer = new BufferedWriter(new OutputStreamWriter(out, "utf-8")); // 解决乱码问题

            // 转换输出
            temp.process(context, writer);
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
