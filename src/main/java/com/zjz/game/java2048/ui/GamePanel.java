package com.zjz.game.java2048.ui;

import com.zjz.game.java2048.core.Board;
import com.zjz.game.java2048.util.ColorManager;
import com.zjz.game.java2048.util.Constants;

import javax.swing.*;
import java.awt.*;

/**
 * 游戏面板类
 * 负责绘制游戏界面
 */
public class GamePanel extends JPanel {
    private Board board;

    public GamePanel(Board board) {
        this.board = board;
        int size = Constants.BOARD_SIZE * Constants.CELL_SIZE +
                (Constants.BOARD_SIZE + 1) * Constants.CELL_MARGIN;
        setPreferredSize(new Dimension(size, size));
        setBackground(new Color(187, 173, 160));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
    }

    /**
     * 设置新的游戏板
     *
     * @param board 新的游戏板
     */
    public void setBoard(Board board) {
        this.board = board;
    }

    /**
     * 绘制游戏板
     */
    private void drawBoard(Graphics g) {
        for (int row = 0; row < Constants.BOARD_SIZE; row++) {
            for (int col = 0; col < Constants.BOARD_SIZE; col++) {
                drawTile(g, row, col);
            }
        }
    }

    /**
     * 绘制单个方块
     */
    private void drawTile(Graphics g, int row, int col) {
        int value = board.getCells()[row][col];
        int x = col * (Constants.CELL_SIZE + Constants.CELL_MARGIN) + Constants.CELL_MARGIN;
        int y = row * (Constants.CELL_SIZE + Constants.CELL_MARGIN) + Constants.CELL_MARGIN;

        // 绘制方块背景
        g.setColor(ColorManager.getColor(value));
        g.fillRoundRect(x, y, Constants.CELL_SIZE, Constants.CELL_SIZE, 10, 10);

        // 如果数字不为0，绘制数字
        if (value != 0) {
            g.setColor(value <= 4 ? new Color(119, 110, 101) : Color.WHITE);
            g.setFont(new Font("Arial", Font.BOLD, getFontSize(value)));
            String s = String.valueOf(value);
            FontMetrics fm = g.getFontMetrics();
            int asc = fm.getAscent();
            int dec = fm.getDescent();

            int xx = x + (Constants.CELL_SIZE - fm.stringWidth(s)) / 2;
            int yy = y + (asc + (Constants.CELL_SIZE - (asc + dec)) / 2);
            g.drawString(s, xx, yy);
        }
    }

    /**
     * 根据数字大小获取合适的字体大小
     */
    private int getFontSize(int value) {
        int digits = String.valueOf(value).length();
        if (digits <= 2) {
            return 36;
        } else if (digits == 3) {
            return 32;
        } else {
            return 24;
        }
    }
} 