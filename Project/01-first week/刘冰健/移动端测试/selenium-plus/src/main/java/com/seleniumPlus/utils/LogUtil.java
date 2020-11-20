package com.seleniumPlus.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 日志工具类
 * 优先级：ERROR 大于 WARN 大于 INFO 大于 DEBUG
 * 程序会打印高于或等于所设置级别的日志，
 * 设置的日志等级越高，打印出来的日志就越少
 */
public class LogUtil {

    /**
     * 获取logger对象
     */
    private static Logger logger = LogManager.getLogger(LogUtil.class);

    /**
     * 开始执行日志输出
     */
    public static void startTestCase() {
        logger.info("------------start------------");
    }

    /**
     * 结束日志输出
     */
    public static void endTestCase() {
        logger.info("------------end------------");
    }

    /**
     * error等级
     *
     * @param msg 要打印的内容
     */
    public static void error(String msg) {
        logger.error(msg);
    }

    /**
     * warn等级
     *
     * @param msg 要打印的内容
     */
    public static void warn(String msg) {
        logger.warn(msg);
    }

    /**
     * info等级
     *
     * @param msg 要打印的内容
     */
    public static void info(String msg) {
        logger.info(msg);
    }

    /**
     * debug等级
     *
     * @param msg 要打印的内容
     */
    public static void debug(String msg) {
        logger.debug(msg);
    }
}
