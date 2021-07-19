/**
 * Tydic.com.cn Inc.
 * Copyright (c) 2011-2020 All Rights Reserved.
 */
package com.laifeiyang.dev.micro.gateway.filter;

/**
 * @author laifeiyang
 * @version Id: XssFilterUnit.java, v 0.1 2020/1/19 17:57 laifeiyang Exp $$
 */
public class XssFilterUnit {

    /**
     * 将容易引起xss漏洞的半角字符直接替换成全角字符
     *
     * @param s
     * @return
     */
    public static String xssEncode(String s) {
        if (s == null || "".equals(s)) {
            return s;
        }
        StringBuilder sb = new StringBuilder(s.length() + 100);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '>':
                    sb.append(' ');// 全角大于号
                    break;
                case '<':
                    sb.append(' ');// 全角小于号
                    break;
                case '(':
                    sb.append(' ');// 全角大于号
                    break;
                case ')':
                    sb.append(' ');// 全角小于号
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }
        return sb.toString();
    }

    /**
     * 将容易引起xss漏洞的半角字，这是url出现了特殊字符，需要将特殊字符进行替换
     *
     * @param s
     * @return
     */
    public static String xssEncodes(String s) {
        if (s == null || "".equals(s)) {
            return s;
        }
        StringBuilder sb = new StringBuilder(s.length() + 16);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '>':
                    sb.append("》");// 全角大于号
                    break;
                case '<':
                    sb.append('《');// 全角小于号
                    break;
                case '(':
                    sb.append('（');// 全角大于号
                    break;
                case ')':
                    sb.append('）');// 全角小于号
                    break;
                default:
                    sb.append(c);
                    break;
            }
        }

        return sb.toString();
    }

}
