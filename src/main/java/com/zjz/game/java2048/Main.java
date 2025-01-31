package com.zjz.game.java2048;

import com.zjz.game.java2048.ui.GameFrame;

import javax.swing.*;

/**
 * 程序入口类
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameFrame game = new GameFrame();
            game.setVisible(true);
        });
    }
} 