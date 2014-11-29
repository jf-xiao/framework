package com.bananac.framework.core.util;


/**
 * 驼峰规则字符串转换工具类
 * @author xiaojf 294825811@qq.com 
 * 2014-11-27
 */
public class CamelCaseUtil {
    private static final char SEPARATOR = '_';

    /**
     * 下滑线，如 CamelCaseUtil.toUnderlineName("ISOCertifiedStaff")==
     * iso_certified_staff
     * 
     * @param s
     * @return 2014-11-27
     */
    public static String toUnderlineName(String s) {
        if (s == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            boolean nextUpperCase = true;

            if (i < (s.length() - 1)) {
                nextUpperCase = Character.isUpperCase(s.charAt(i + 1));
            }

            if ((i >= 0) && Character.isUpperCase(c)) {
                if (!upperCase || !nextUpperCase) {
                    if (i > 0)
                        sb.append(SEPARATOR);
                }
                upperCase = true;
            } else {
                upperCase = false;
            }

            sb.append(Character.toLowerCase(c));
        }

        return sb.toString();
    }

    /**
     * 驼峰，如 CamelCaseUtil.toCamelCase("iso_certified_staff") ==
     * isoCertifiedStaff
     * 
     * @param s
     * @return 2014-11-27
     */
    public static String toCamelCase(String column) {
        if (column == null || "".equals(column)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        char[] chars = column.toLowerCase().toCharArray();
        int len = chars.length;
        int len2 = len - 1;
        for (int i = 0; i < len; i++) {
            if (chars[i] == '_') {
                if (i != len2) {
                    if(i != 0){
                        chars[i + 1] = Character.toUpperCase(chars[i + 1]);
                    }
                }
                continue;
            }
            sb.append(chars[i]);
        }
        return sb.toString();
    }

    /**
     * 驼峰且第一个字母大写，如 CamelCaseUtil.toCapitalizeCamelCase("hello_world") ==
     * HelloWorld
     * @param s
     * @return 2014-11-27
     */
    public static String toCapitalizeCamelCase(String s) {
        if (s == null) {
            return null;
        }
        s = toCamelCase(s);
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}
