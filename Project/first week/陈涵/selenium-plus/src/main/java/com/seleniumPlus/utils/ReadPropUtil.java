package com.seleniumPlus.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取properties文件工具类
 */
public class ReadPropUtil {

    /**
     * 配置文件路径
     */
    private static final String filePath = "conf/config.properties";

    /**
     * properties文件读取对象
     */
    private static Properties prop;

    /**
     * 类加载的时候创建对象，不需要重复创建prop
     */
    static {
        prop = new Properties();
    }

    /**
     * 通过key获取配置文件对应的值
     *
     * @param key 键
     * @return value值
     */
    public static String getPropertyValue(String key) {
        InputStream in = ReadPropUtil.class.getClassLoader().getResourceAsStream(filePath);
        try {
            prop.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(key);
    }

    /**
     * 获取多个key的value值
     *
     * @param key 用","隔开的多个连续的key
     * @return value值数组
     */
    public static String[] getPropSplitValues(String key) {
        InputStream in;
        try {
            in = ReadPropUtil.class.getClassLoader().getResourceAsStream(filePath);
            prop.load(in);
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prop.getProperty(key).split(",");
    }
}
