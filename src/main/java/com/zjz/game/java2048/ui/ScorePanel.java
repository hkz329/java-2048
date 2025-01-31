package com.zjz.game.java2048.ui;

import javax.swing.*;
import java.awt.*;

/**
 * 分数面板类
 * 显示当前分数和最高分
 */
public class ScorePanel extends JPanel {
    private final JLabel scoreLabel;
    private final JLabel bestScoreLabel;
    private int currentScore;
    private int bestScore;

    public ScorePanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 5));
        setBackground(new Color(187, 173, 160));

        // 当前分数面板
        JPanel currentScorePanel = createScoreContainer("SCORE");
        scoreLabel = new JLabel("0");
        currentScorePanel.add(scoreLabel);

        // 最高分面板
        JPanel bestScorePanel = createScoreContainer("BEST");
        bestScoreLabel = new JLabel("0");
        bestScorePanel.add(bestScoreLabel);

        add(currentScorePanel);
        add(bestScorePanel);
    }

    /**
     * 创建分数容器
     */
    private JPanel createScoreContainer(String title) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(187, 173, 160));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setForeground(new Color(238, 228, 218));
        panel.add(titleLabel);

        return panel;
    }

    /**
     * 更新分数
     */
    public void updateScore(int score) {
        this.currentScore = score;
        scoreLabel.setText(String.valueOf(score));
        if (score > bestScore) {
            bestScore = score;
            bestScoreLabel.setText(String.valueOf(bestScore));
        }
    }
} 