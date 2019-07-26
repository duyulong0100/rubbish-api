package com.xserver.util;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class CheckUtils {

    /**
     * 校验字符串是否为数字
     */
    public static boolean isNumber(String str) {
        return check(str, "\\d*");
    }

    /**
     * 校验是否为汉字
     */
    public static boolean isChinese(String str) {
        return check(str, "^[\\u4e00-\\u9fa5]{2,5}$");
    }

    /**
     * 校验Email格式
     */
    public static boolean isEmail(String str) {
        return check(str, "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$");
    }

    /**
     * 校验手机号码格式
     */
    public static boolean isMobile(String str) {
        return check(str, "^[1][3,4,5,7,8][0-9]{9}$");
    }

    /**
     * 校验URL格式
     */
    public static boolean isUrl(String str) {
        String reg = "^"
                +
                // protocol identifier
                "(?:(?:https?|ftp)://)"
                +
                // rubbish:pass authentication
                "(?:\\S+(?::\\S*)?@)?"
                + "(?:"
                +
                // IP address exclusion
                // private & local networks
                "(?!(?:10|127)(?:\\.\\d{1,3}){3})"
                + "(?!(?:169\\.254|192\\.168)(?:\\.\\d{1,3}){2})"
                + "(?!172\\.(?:1[6-9]|2\\d|3[0-1])(?:\\.\\d{1,3}){2})"
                +
                // IP address dotted notation octets
                // excludes loopback network 0.0.0.0
                // excludes reserved space >= 224.0.0.0
                // excludes network & broadcast addresses
                // (first & last IP address of each class)
                "(?:[1-9]\\d?|1\\d\\d|2[01]\\d|22[0-3])" + "(?:\\.(?:1?\\d{1,2}|2[0-4]\\d|25[0-5])){2}"
                + "(?:\\.(?:[1-9]\\d?|1\\d\\d|2[0-4]\\d|25[0-4]))" + "|" +
                // host name
                "(?:(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)" +
                // domain name
                "(?:\\.(?:[a-z\\u00a1-\\uffff0-9]-*)*[a-z\\u00a1-\\uffff0-9]+)*" +
                // TLD identifier
                "(?:\\.(?:[a-z\\u00a1-\\uffff]{2,}))" +
                // TLD may end with dot
                "\\.?" + ")" +
                // port number
                "(?::\\d{2,5})?" +
                // resource path
                "(?:[/?#]\\S*)?" + "$";
        return check(str, reg);
    }

    public static boolean check(String str, String reg) {
        if (str != null && str.matches(reg)) {
            return true;
        }
        return false;
    }

    /**
     * 检查字符串中是否含有某些关键字
     */
    public static boolean checkKeywords(String input, String[] keywords) {
        if (input == null || keywords == null) {
            return false;
        }
        for (String value : keywords) {
            if (input.indexOf(value) > -1) {
                return true;
            }
        }
        return false;
    }

    public static String filterUTF8MB4(String text) {
        try {
            if (text == null) {
                return null;
            }
            byte[] bytes = text.getBytes("utf-8");
            if (!hasUTF8MB4(bytes)) {
                return text;
            }
            ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
            int i = 0;
            while (i < bytes.length) {
                short b = bytes[i];
                if (b > 0) {
                    buffer.put(bytes[i++]);
                    continue;
                }
                b += 256;// 去掉符号位
                if (((b >> 5) ^ 0x6) == 0) {
                    buffer.put(bytes, i, 2);
                    i += 2;
                } else if (((b >> 4) ^ 0xE) == 0) {
                    buffer.put(bytes, i, 3);
                    i += 3;
                } else if (((b >> 3) ^ 0x1E) == 0) {
                    i += 4;
                } else if (((b >> 2) ^ 0x3E) == 0) {
                    i += 5;
                } else if (((b >> 1) ^ 0x7E) == 0) {
                    i += 6;
                } else {
                    buffer.put(bytes[i++]);
                }
            }
            buffer.flip();
            return new String(buffer.array(), 0, buffer.limit(), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return text;
        }
    }

    public static boolean hasUTF8MB4(byte[] bytes) {
        int i = 0;
        while (i < bytes.length) {
            short b = bytes[i];
            if (b > 0) {
                i++;
                continue;
            }
            b += 256;// 去掉符号位
            if (((b >> 5) ^ 0x6) == 0) {
                i += 2;
            } else if (((b >> 4) ^ 0xE) == 0) {
                i += 3;
            } else if (((b >> 3) ^ 0x1E) == 0) {
                return true;
            } else if (((b >> 2) ^ 0x3E) == 0) {
                return true;
            } else if (((b >> 1) ^ 0x7E) == 0) {
                return true;
            } else {
                i++;
            }
        }
        return false;
    }

}