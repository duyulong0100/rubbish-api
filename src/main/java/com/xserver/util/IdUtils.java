package com.xserver.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class IdUtils {

    /***
     * 获取32位UUID
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 生成文件名(格式：3位随机字母+12位时间戳)
     */
    public static String generateCode() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmm");
        Random r = new Random();
        StringBuffer xyz = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            xyz.append((char) (r.nextInt(26) + 65));// 大写字母
            // xyz.append((char)(r.nextInt(26)+97));//小写字母
        }
        return xyz + sdf.format(new Date());
    }

}