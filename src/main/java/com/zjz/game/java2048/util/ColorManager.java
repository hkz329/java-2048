package com.zjz.game.java2048.util;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 颜色管理类
 * 为不同数字块提供对应的背景色
 */
public class ColorManager {
    private static final Map<Integer, Color> colorMap = new HashMap<>();

    static {
        colorMap.put(0, new Color(205, 193, 180));  // 空格子颜色
        colorMap.put(2, new Color(238, 228, 218));
        colorMap.put(4, new Color(237, 224, 200));
        colorMap.put(8, new Color(242, 177, 121));
        colorMap.put(16, new Color(245, 149, 99));
        colorMap.put(32, new Color(246, 124, 95));
        colorMap.put(64, new Color(246, 94, 59));
        colorMap.put(128, new Color(237, 207, 114));
        colorMap.put(256, new Color(237, 204, 97));
        colorMap.put(512, new Color(237, 200, 80));
        colorMap.put(1024, new Color(237, 197, 63));
        colorMap.put(2048, new Color(237, 194, 46));
    }

    /**
     * 获取数字对应的颜色
     * @param number 数字
     * @return 对应的颜色
     */
    public static Color getColor(int number) {
        return colorMap.getOrDefault(number, new Color(238, 228, 218));
    }
}