package com.zjz.game.java2048.util;

/**
 * 游戏常量定义
 */
public class Constants {
    // 游戏板大小
    public static final int BOARD_SIZE = 4;
    // 初始数字
    public static final int INITIAL_NUMBERS = 2;
    // 获胜数字
    public static final int WIN_NUMBER = 2048;
    // 新生成数字的可能值
    public static final int[] NEW_NUMBERS = {2, 4};
    // 新生成4的概率 (20%)
    public static final double PROBABILITY_OF_FOUR = 0.2;
    
    // UI常量
    public static final int CELL_SIZE = 100;
    public static final int CELL_MARGIN = 10;
    public static final int BOARD_MARGIN = 20;
} 