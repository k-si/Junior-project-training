package com.seleniumPlus.reporter;

import lombok.Data;
import org.testng.ITestNGMethod;

import java.util.Collection;
import java.util.List;

/**
 * 测试报告实体类
 */
@Data
public class DataBean {
    private int excludeTestsSize; // 未执行的test数量
    private int passedTestsSize; // 测试通过的数量
    private int failedTestsSize; // 测试失败的数量
    private int skippedTestsSize; // 测试跳过的数量
    private int allTestsSize; // 全部执行的测试的数量
    private ITestNGMethod[] allTestsMethod; // 全部执行的测试方法
    private Collection<ITestNGMethod> excludeTestsMethod; // 未执行的测试方法
    private String testsTime; // 测试耗时
    private String passPercent; // 测试通过率
    private String testName; // 测试方法名
    private String className; // 测试类名
    private String duration; // 单个测试周期
    private String params; // 测试用参数
    private String description; // 测试描述
    private List<String> output; // Reporter Output
    private String dependMethod; // 测试依赖方法
    private Throwable throwable; // 测试异常原因
    private StackTraceElement[] stackTrace; // 异常堆栈信息
}
