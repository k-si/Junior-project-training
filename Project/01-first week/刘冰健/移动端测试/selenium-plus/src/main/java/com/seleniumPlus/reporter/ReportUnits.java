package com.seleniumPlus.reporter;

import org.testng.ITestContext;
import org.testng.ITestResult;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

/**
 * 测试结果数据处理工具类
 */
public class ReportUnits {

    /**
     * 测试耗时格式
     */
    private static final NumberFormat DURATION_FORMAT = new DecimalFormat("#0.000");

    /**
     * 通过率百分比格式
     */
    private static final NumberFormat PERCENTAGE_FORMAT = new DecimalFormat("#0.00%");

    /**
     * 测试消耗时长
     *
     * @param context 测试方法集
     * @return 秒, 保留3位小数
     */
    public static String getTestDuration(ITestContext context) {
        long duration = context.getEndDate().getTime() - context.getStartDate().getTime();
        return formatDuration(duration);
    }

    /**
     * 对测试耗时结果格式化，保留三位小数
     *
     * @param elapsed 实际执行时间
     * @return 0.000
     */
    public static String formatDuration(long elapsed) {
        double seconds = (double) elapsed / 1000;
        return DURATION_FORMAT.format(seconds);
    }

    /**
     * 计算测试通过率并格式化
     *
     * @param numerator   分子
     * @param denominator 分母
     * @return 0.00%
     */
    public static String formatPercentage(int numerator, int denominator) {
        return PERCENTAGE_FORMAT.format(numerator / (double) denominator);
    }

    /**
     * 获取方法参数，以逗号分隔
     *
     * @param result 测试结果集
     * @return 测试方法的参数，以逗号分隔
     */
    public static String getParams(ITestResult result) {
        Object[] params = result.getParameters();
        List<String> list = new ArrayList<>(params.length);
        for (Object o : params) {
            list.add(renderArgument(o));
        }
        return commaSeparate(list);
    }

    /**
     * 获取依赖的方法
     *
     * @param result 测试结果集
     * @return 依赖的方法以逗号分隔
     */
    public static String getDependMethods(ITestResult result) {
        String[] methods = result.getMethod().getMethodsDependedUpon();
        return commaSeparate(Arrays.asList(methods));
    }

    /**
     * 堆栈轨迹
     *
     * @param throwable 抛出的异常
     * @return 所有异常信息，逗号分隔
     */
//    public static String getCause(Throwable throwable) {
//        StackTraceElement[] stackTrace = throwable.getStackTrace(); // 堆栈轨迹
//        List<String> list = new ArrayList<>(stackTrace.length);
//        for (Object o : stackTrace) {
//            list.add(renderArgument(o));
//        }
//        return commaSeparate(list);
//    }

    /**
     * 获取全部日志输出信息
     *
     * @return
     */
//    public static List<String> getAllOutput() {
//        return Reporter.getOutput();
//    }

    /**
     * 按test result获取日志输出信息
     *
     * @param result
     * @return
     */
//    public static List<String> getTestOutput(ITestResult result) {
//        return Reporter.getOutput(result);
//    }


    /**
     * 将object转换为String
     *
     * @param argument Obj对象
     * @return String对象
     */
    public static String renderArgument(Object argument) {
        if (argument == null) {
            return "null";
        } else if (argument instanceof String) {
            return "\"" + argument + "\"";
        } else if (argument instanceof Character) {
            return "\'" + argument + "\'";
        } else {
            return argument.toString();
        }
    }


    /**
     * 将集合转换为以逗号分隔的字符串
     *
     * @param strings 多个字符串的集合
     * @return 多个字符串被逗号分隔
     */
    public static String commaSeparate(Collection<String> strings) {
        StringBuilder buffer = new StringBuilder();
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) {
            String string = iterator.next();
            buffer.append(string);
            if (iterator.hasNext()) {
                buffer.append(", ");
            }
        }
        return buffer.toString();
    }
}
