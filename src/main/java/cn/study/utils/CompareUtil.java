package cn.study.utils;

import java.util.Arrays;

public class CompareUtil {
    //字符串类型的数组站字符串数组
    public static String[] toStringArray(String source) {
        return source.substring(1,source.length()-1).split(",");
    }


    //判断两个字符串数组是否相等
    public static boolean isEqual(String[] arr1, String[] arr2) {
        if (arr1 == null || arr2 == null) {
            return false;
        }
        int count1 = arr1.length;
        int count2 = arr2.length;
        if (count1 != count2) {
            return false;
        }
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        for (int i = 0; i < count1; i++) {
            if (!arr1[i].contentEquals(arr2[i])) {
                return false;
            }
        }
        return true;
    }
}
