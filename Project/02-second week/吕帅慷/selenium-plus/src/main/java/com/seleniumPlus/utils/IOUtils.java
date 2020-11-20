package com.seleniumPlus.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class IOUtils {

    public static String read(String filepath)  {
        try {
            BufferedReader in = new BufferedReader(new FileReader(filepath));
            StringBuilder builder = new StringBuilder();
            String str;
            while ((str = in.readLine()) != null) {
                builder.append(str);
            }
            in.close();
            return builder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
