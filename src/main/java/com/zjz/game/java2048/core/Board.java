package com.zjz.game.java2048.core;

import com.zjz.game.java2048.util.Constants;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 游戏板类
 * 负责维护游戏板的状态和核心操作
 */
@Getter
public class Board {
    private final int[][] cells;
    private int score;
    private final Random random;

    public Board() {
        cells = new int[Constants.BOARD_SIZE][Constants.BOARD_SIZE];
        random = new Random();
        score = 0;
        initBoard();
    }

    /**
     * 初始化游戏板
     */
    private void initBoard() {
        // 初始化时添加两个数字
        for (int i = 0; i < Constants.INITIAL_NUMBERS; i++) {
            addRandomNumber();
        }
    }

    /**
     * 在随机空位置添加新数字
     * @return 是否成功添加
     */
    public boolean addRandomNumber() {
        List<int[]> emptyCells = getEmptyCells();
        if (emptyCells.isEmpty()) {
            return false;
        }

        int[] cell = emptyCells.get(random.nextInt(emptyCells.size()));
        cells[cell[0]][cell[1]] = random.nextDouble() < Constants.PROBABILITY_OF_FOUR ? 4 : 2;
        return true;
    }

    /**
     * 获取所有空单元格位置
     */
    private List<int[]> getEmptyCells() {
        List<int[]> emptyCells = new ArrayList<>();
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            for (int j = 0; j < Constants.BOARD_SIZE; j++) {
                if (cells[i][j] == 0) {
                    emptyCells.add(new int[]{i, j});
                }
            }
        }
        return emptyCells;
    }

    /**
     * 移动并合并数字
     * @param direction 移动方向
     * @return 是否发生移动或合并
     */
    public boolean move(Direction direction) {
        boolean moved = false;
        switch (direction) {
            case UP -> moved = moveUp();
            case DOWN -> moved = moveDown();
            case LEFT -> moved = moveLeft();
            case RIGHT -> moved = moveRight();
        }
        return moved;
    }

    /**
     * 向左移动
     */
    private boolean moveLeft() {
        boolean moved = false;
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            moved |= mergeLine(cells[i]);
        }
        return moved;
    }

    /**
     * 向右移动
     */
    private boolean moveRight() {
        boolean moved = false;
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            // 反转数组，使用左移逻辑，再反转回来
            reverseArray(cells[i]);
            moved |= mergeLine(cells[i]);
            reverseArray(cells[i]);
        }
        return moved;
    }

    /**
     * 向上移动
     */
    private boolean moveUp() {
        boolean moved = false;
        for (int j = 0; j < Constants.BOARD_SIZE; j++) {
            int[] column = getColumn(j);
            moved |= mergeLine(column);
            setColumn(j, column);
        }
        return moved;
    }

    /**
     * 向下移动
     */
    private boolean moveDown() {
        boolean moved = false;
        for (int j = 0; j < Constants.BOARD_SIZE; j++) {
            int[] column = getColumn(j);
            reverseArray(column);
            moved |= mergeLine(column);
            reverseArray(column);
            setColumn(j, column);
        }
        return moved;
    }

    /**
     * 合并一行或一列
     */
    private boolean mergeLine(int[] line) {
        boolean moved = false;
        int[] newLine = new int[Constants.BOARD_SIZE];
        int index = 0;
        
        // 移动非零数字
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            if (line[i] != 0) {
                newLine[index++] = line[i];
            }
        }
        
        // 合并相同数字
        for (int i = 0; i < index - 1; i++) {
            if (newLine[i] == newLine[i + 1]) {
                newLine[i] *= 2;
                score += newLine[i];
                newLine[i + 1] = 0;
                i++;
            }
        }
        
        // 重新整理数组
        int[] result = new int[Constants.BOARD_SIZE];
        index = 0;
        for (int value : newLine) {
            if (value != 0) {
                result[index++] = value;
            }
        }
        
        // 检查是否发生移动
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            if (line[i] != result[i]) {
                moved = true;
            }
            line[i] = result[i];
        }
        
        return moved;
    }

    /**
     * 获取指定列
     */
    private int[] getColumn(int col) {
        int[] column = new int[Constants.BOARD_SIZE];
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            column[i] = cells[i][col];
        }
        return column;
    }

    /**
     * 设置指定列
     */
    private void setColumn(int col, int[] column) {
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            cells[i][col] = column[i];
        }
    }

    /**
     * 反转数组
     */
    private void reverseArray(int[] arr) {
        int left = 0, right = arr.length - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }

    /**
     * 检查游戏是否结束
     */
    public boolean isGameOver() {
        // 检查是否有空格
        if (!getEmptyCells().isEmpty()) {
            return false;
        }

        // 检查是否有相邻的相同数字
        for (int i = 0; i < Constants.BOARD_SIZE; i++) {
            for (int j = 0; j < Constants.BOARD_SIZE; j++) {
                if (j < Constants.BOARD_SIZE - 1 && cells[i][j] == cells[i][j + 1]) {
                    return false;
                }
                if (i < Constants.BOARD_SIZE - 1 && cells[i][j] == cells[i + 1][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 检查是否胜利
     */
    public boolean hasWon() {
        for (int[] row : cells) {
            for (int cell : row) {
                if (cell >= Constants.WIN_NUMBER) {
                    return true;
                }
            }
        }
        return false;
    }
}