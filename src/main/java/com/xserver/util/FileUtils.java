package com.xserver.util;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class FileUtils {

    /**
     * 获取文件名(包含后缀名)
     */
    public static String getFileName(String filePath) {
        if (filePath == null) {
            return "";
        }
        filePath = filePath.replace("\\", "/");
        int index = filePath.lastIndexOf("/");
        if (index > -1) {
            return filePath.substring(index + 1);
        }
        return filePath;
    }

    /**
     * 获取文件后缀名(包含".")
     */
    public static String getFileSuffix(String fileName) {
        if (fileName == null) {
            return "";
        }
        int index = fileName.lastIndexOf(".");
        if (index > -1) {
            return fileName.substring(index).toLowerCase();
        }
        return "";
    }

    /**
     * 创建文件目录
     */
    public static boolean makeDirs(String path) {
        if (path == null || "".equals(path)) {
            return false;
        }
        File folder = new File(path);
        if (!folder.exists()) {
            return folder.mkdirs();
        }
        return true;
    }

    /**
     * 生成文件名(15位时间戳+5位随机字母)
     */
    public static String generateFileName() {
        SimpleDateFormat sdf = new SimpleDateFormat("SSSyyMMddHHmmss");
        Random r = new Random();
        StringBuffer xyz = new StringBuffer();
        for (int i = 0; i < 5; i++) {
            if (r.nextBoolean()) {
                xyz.append((char) (r.nextInt(26) + 65));// 大写字母
            } else {
                xyz.append((char) (r.nextInt(26) + 97));// 小写字母
            }
        }
        return sdf.format(new Date()) + xyz;
    }

    /**
     * 生成日期目录
     */
    public static String generateDateDir() {
        return new SimpleDateFormat("yyyyMM").format(new Date());
    }

    /**
     * 判断文件类型是否在指定的类型中
     */
    public static boolean checkFileType(String[] allowTypes, String fileSuffix) {
        if (allowTypes == null || allowTypes.length == 0) {
            // 未声明任何类型时，直接返回true
            return true;
        }
        for (String type : allowTypes) {
            if (type.equalsIgnoreCase(fileSuffix)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 保存字符串到文件中
     */
    public static void saveAsFile(String path, String content, String encoding) {
        if (path == null || content == null) {
            return;
        }
        if (encoding == null) {
            encoding = "UTF-8";// GBK
        }
        BufferedWriter bw = null;
        try {
            File file = new File(path);
            if (!file.exists()) {
                if (FileUtils.makeDirs(file.getParent())) {
                    file.createNewFile();
                }
            }
            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file), encoding);
            bw = new BufferedWriter(write);
            bw.write(content);
            System.out.println("save as file success. " + path);
        } catch (IOException e) {
            System.out.println("save as file error. " + path);
        } finally {
            try {
                if (bw != null) {
                    bw.flush();
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取文件内容
     */
    public static String readFileContent(String filePath, String encoding) {
        if (filePath == null) {
            return null;
        }
        if (encoding == null) {
            encoding = "UTF-8";// GBK
        }
        FileInputStream fis = null;
        InputStreamReader is = null;
        BufferedReader br = null;
        try {
            File file = new File(filePath);
            if (!file.exists() || !file.isFile()) {
                return null;
            }
            StringBuffer content = new StringBuffer();
            fis = new FileInputStream(file);
            is = new InputStreamReader(fis, encoding);
            br = new BufferedReader(is);
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
            return content.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (is != null) {
                    is.close();
                }
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}