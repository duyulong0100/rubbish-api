package com.xserver.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class CommonUtils {

    public static Map<String, Integer> sortSizeCountMap = new LinkedHashMap<>();

    static {
        sortSizeCountMap.put("S", 0);
        sortSizeCountMap.put("M", 0);
        sortSizeCountMap.put("L", 0);
        sortSizeCountMap.put("XL", 0);
        sortSizeCountMap.put("XXL", 0);
        sortSizeCountMap.put("XXXL", 0);
        sortSizeCountMap.put("XXXXL", 0);
    }

    public static String getSortSizeCount(String sizeCount) {
        StringBuffer sb = new StringBuffer();
        if (StringUtils.isNotBlank(sizeCount)) {
            Map<String, Integer> sizeCountMap = new HashMap<>();
            String[] sizeCountArray = sizeCount.split(";");
            for (String s : sizeCountArray) {
                String[] size_count = s.split(":");
                if (size_count.length == 2) {
                    sizeCountMap.put(size_count[0], Integer.parseInt(size_count[1]));
                }
            }

            if (!CollectionUtils.isEmpty(sizeCountMap)) {
                for (Map.Entry<String, Integer> entry : sortSizeCountMap.entrySet()) {
                    if (sizeCountMap.containsKey(entry.getKey())) {
                        sb.append(entry.getKey()).append(":").append(sizeCountMap.get(entry.getKey())).append(";");
                    }
                }
            }
        }
        String sbString = sb.toString();
        return sbString.endsWith(";") ? sbString.substring(0, sbString.length() - 1) : sbString;
    }

}
